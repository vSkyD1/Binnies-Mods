package binnie.botany.farm;

import binnie.Binnie;
import binnie.botany.Botany;
import binnie.botany.api.EnumAcidity;
import binnie.botany.api.EnumMoisture;
import binnie.botany.api.EnumSoilType;
import binnie.botany.api.gardening.IBlockSoil;
import binnie.botany.farm.FarmLogic;
import binnie.botany.farm.FarmableFlower;
import binnie.botany.farm.FarmableVanillaFlower;
import binnie.botany.farm.Vect;
import binnie.botany.flower.TileEntityFlower;
import binnie.botany.gardening.Gardening;
import binnie.core.Mods;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.farming.IFarmHousing;
import forestry.api.farming.IFarmable;
import forestry.core.interfaces.IOwnable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class GardenLogic extends FarmLogic {
   EnumMoisture moisture;
   EnumAcidity acidity;
   boolean fertilised;
   String name;
   ArrayList produce = new ArrayList();
   private ItemStack icon;
   List farmables = new ArrayList();

   public GardenLogic(IFarmHousing housing) {
      super(housing);
      this.farmables.add(new FarmableFlower());
      this.farmables.add(new FarmableVanillaFlower());
   }

   public int getFertilizerConsumption() {
      return this.fertilised?8:2;
   }

   public int getWaterConsumption(float hydrationModifier) {
      return (int)((float)(this.moisture.ordinal() * 40) * hydrationModifier);
   }

   public boolean isAcceptedResource(ItemStack itemstack) {
      return !Gardening.isSoil(itemstack.getItem()) && itemstack.getItem() != Item.getItemFromBlock(Blocks.sand) && itemstack.getItem() != Item.getItemFromBlock(Blocks.dirt)?Gardening.isAcidFertiliser(itemstack) || Gardening.isAlkalineFertiliser(itemstack):true;
   }

   public Collection collect() {
      Collection<ItemStack> products = this.produce;
      this.produce = new ArrayList();
      return products;
   }

   public boolean cultivate(int x, int y, int z, ForgeDirection direction, int extent) {
      this.world = this.housing.getWorld();
      return this.maintainSoil(x, y, z, direction, extent)?true:(!this.isManual && this.maintainWater(x, y, z, direction, extent)?true:this.maintainCrops(x, y + 1, z, direction, extent));
   }

   private boolean isWaste(ItemStack stack) {
      return stack.getItem() == Item.getItemFromBlock(Blocks.dirt);
   }

   private boolean maintainSoil(int x2, int y2, int z2, ForgeDirection direction, int extent) {
      for(int i = 0; i < extent; ++i) {
         Vect position = this.translateWithOffset(x2, y2, z2, direction, i);
         if(this.fertilised && Gardening.isSoil(this.world.getBlock(position.x, position.y, position.z))) {
            IBlockSoil soil = (IBlockSoil)this.world.getBlock(position.x, position.y, position.z);
            if(soil.fertilise(this.world, position.x, position.y, position.z, EnumSoilType.FLOWERBED)) {
               continue;
            }
         }

         if(this.world.getBlock(position.x, position.y + 1, position.z) == Botany.plant) {
            this.world.setBlockToAir(position.x, position.y + 1, position.z);
         } else {
            if(this.acidity != null && Gardening.isSoil(this.world.getBlock(position.x, position.y, position.z))) {
               IBlockSoil soil = (IBlockSoil)this.world.getBlock(position.x, position.y, position.z);
               EnumAcidity pH = soil.getPH(this.world, position.x, position.y, position.z);
               if(pH.ordinal() < this.acidity.ordinal()) {
                  ItemStack stack = this.getAvailableAlkaline();
                  if(stack != null && soil.setPH(this.world, position.x, position.y, position.z, EnumAcidity.values()[pH.ordinal() + 1])) {
                     this.housing.removeResources(new ItemStack[]{stack});
                     continue;
                  }
               }

               if(pH.ordinal() > this.acidity.ordinal()) {
                  ItemStack stack = this.getAvailableAcid();
                  if(stack != null && soil.setPH(this.world, position.x, position.y, position.z, EnumAcidity.values()[pH.ordinal() - 1])) {
                     this.housing.removeResources(new ItemStack[]{stack});
                     continue;
                  }
               }
            }

            if(!this.isAirBlock(position) && !this.world.getBlock(position.x, position.y, position.z).isReplaceable(this.world, position.x, position.y, position.z)) {
               ItemStack block = this.getAsItemStack(position);
               ItemStack loam = this.getAvailableLoam();
               if(this.isWaste(block) && loam != null) {
                  this.produce.addAll(Blocks.dirt.getDrops(this.world, position.x, position.y, position.z, block.getItemDamage(), 0));
                  this.setBlock(position, Blocks.air, 0);
                  return this.trySetSoil(position, loam);
               }
            } else if(!this.isManual && !this.isWaterBlock(position)) {
               if(i % 2 == 0) {
                  return this.trySetSoil(position);
               }

               ForgeDirection cclock = ForgeDirection.EAST;
               if(direction == ForgeDirection.EAST) {
                  cclock = ForgeDirection.SOUTH;
               } else if(direction == ForgeDirection.SOUTH) {
                  cclock = ForgeDirection.EAST;
               } else if(direction == ForgeDirection.WEST) {
                  cclock = ForgeDirection.SOUTH;
               }

               Vect previous = this.translateWithOffset(position.x, position.y, position.z, cclock, 1);
               ItemStack soil = this.getAsItemStack(previous);
               if(!Gardening.isSoil(soil.getItem())) {
                  this.trySetSoil(position);
               }
            }
         }
      }

      return false;
   }

   private boolean maintainWater(int x, int y, int z, ForgeDirection direction, int extent) {
      for(int i = 0; i < extent; ++i) {
         Vect position = this.translateWithOffset(x, y, z, direction, i);
         if((this.isAirBlock(position) || this.world.getBlock(x, y, z).isReplaceable(this.world, position.x, position.y, position.z)) && !this.isWaterBlock(position)) {
            boolean isEnclosed = true;
            if(this.world.isAirBlock(position.x + 1, position.y, position.z)) {
               isEnclosed = false;
            } else if(this.world.isAirBlock(position.x - 1, position.y, position.z)) {
               isEnclosed = false;
            } else if(this.world.isAirBlock(position.x, position.y, position.z + 1)) {
               isEnclosed = false;
            } else if(this.world.isAirBlock(position.x, position.y, position.z - 1)) {
               isEnclosed = false;
            }

            isEnclosed = isEnclosed || this.moisture != EnumMoisture.Damp;
            if(isEnclosed) {
               return this.trySetWater(position);
            }
         }
      }

      return false;
   }

   private ItemStack getAvailableLoam() {
      EnumMoisture[] moistures;
      if(this.moisture == EnumMoisture.Damp) {
         moistures = new EnumMoisture[]{EnumMoisture.Damp, EnumMoisture.Normal, EnumMoisture.Dry};
      } else if(this.moisture == EnumMoisture.Damp) {
         moistures = new EnumMoisture[]{EnumMoisture.Dry, EnumMoisture.Damp, EnumMoisture.Dry};
      } else {
         moistures = new EnumMoisture[]{EnumMoisture.Dry, EnumMoisture.Normal, EnumMoisture.Damp};
      }

      EnumAcidity[] acidities = new EnumAcidity[]{EnumAcidity.Neutral, EnumAcidity.Acid, EnumAcidity.Alkaline};

      for(EnumMoisture moist : moistures) {
         for(EnumAcidity acid : acidities) {
            for(Block type : new Block[]{Botany.flowerbed, Botany.loam, Botany.soil}) {
               int meta = acid.ordinal() * 3 + moist.ordinal();
               ItemStack[] resource = new ItemStack[]{new ItemStack(type, 1, meta)};
               if(this.housing.hasResources(resource)) {
                  return resource[0];
               }
            }
         }
      }

      if(this.housing.hasResources(new ItemStack[]{new ItemStack(Blocks.dirt)})) {
         return new ItemStack(Blocks.dirt);
      } else {
         return null;
      }
   }

   private boolean trySetSoil(Vect position) {
      return this.trySetSoil(position, this.getAvailableLoam());
   }

   private boolean trySetSoil(Vect position, ItemStack loam) {
      if(loam != null) {
         if(loam.getItem() == Item.getItemFromBlock(Blocks.dirt)) {
            loam = new ItemStack(Botany.soil, 0, 4);
         }

         this.setBlock(position, ((ItemBlock)loam.getItem()).field_150939_a, loam.getItemDamage());
         this.housing.removeResources(new ItemStack[]{loam});
         return true;
      } else {
         return false;
      }
   }

   private boolean trySetWater(Vect position) {
      FluidStack water = Binnie.Liquid.getLiquidStack("water", 1000);
      if(this.moisture == EnumMoisture.Damp) {
         if(!this.housing.hasLiquid(water)) {
            return false;
         } else {
            this.setBlock(position, Blocks.water, 0);
            this.housing.removeLiquid(water);
            return true;
         }
      } else if(this.moisture == EnumMoisture.Dry) {
         ItemStack[] sand = new ItemStack[]{new ItemStack(Blocks.sand, 1)};
         if(!this.housing.hasResources(sand)) {
            return false;
         } else {
            this.setBlock(position, Blocks.sand, 0);
            this.housing.removeResources(sand);
            return true;
         }
      } else {
         return this.trySetSoil(position);
      }
   }

   public void setIcon(ItemStack icon) {
      this.icon = icon;
   }

   public boolean isAcceptedGermling(ItemStack itemstack) {
      for(IFarmable farmable : this.farmables) {
         if(farmable.isGermling(itemstack)) {
            return true;
         }
      }

      return false;
   }

   public Collection harvest(int x, int y, int z, ForgeDirection direction, int extent) {
      return null;
   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon() {
      return this.icon.getIconIndex();
   }

   public ResourceLocation getSpriteSheet() {
      return TextureMap.locationBlocksTexture;
   }

   public String getName() {
      return this.name;
   }

   protected boolean maintainCrops(int x, int y, int z, ForgeDirection direction, int extent) {
      for(int i = 0; i < extent; ++i) {
         Vect position = this.translateWithOffset(x, y, z, direction, i);
         if(this.isAirBlock(position) || this.world.getBlock(x, y, z).isReplaceable(this.world, position.x, position.y, position.z)) {
            ItemStack below = this.getAsItemStack(position.add(new Vect(0, -1, 0)));
            if(Gardening.isSoil(below.getItem())) {
               return this.trySetCrop(position);
            }
         }
      }

      return false;
   }

   private boolean trySetCrop(Vect position) {
      for(IFarmable farmable : this.farmables) {
         boolean success = this.housing.plantGermling(farmable, this.world, position.x, position.y, position.z);
         if(success && this.housing instanceof IOwnable) {
            TileEntity tile = this.housing.getWorld().getTileEntity(position.x, position.y, position.z);
            if(tile instanceof TileEntityFlower) {
               try {
                  IOwnable housing2 = (IOwnable)this.housing;
                  GameProfile prof = null;
                  if(Mods.Forestry.dev()) {
                     prof = (GameProfile)IOwnable.class.getMethod("getOwner", new Class[0]).invoke(housing2, new Object[0]);
                  } else {
                     prof = (GameProfile)IOwnable.class.getMethod("getOwnerProfile", new Class[0]).invoke(housing2, new Object[0]);
                  }

                  ((TileEntityFlower)tile).setOwner(prof);
               } catch (Exception var8) {
                  ;
               }
            }

            return true;
         }
      }

      return false;
   }

   public ItemStack getAvailableAcid() {
      for(ItemStack stack : Gardening.getAcidFertilisers()) {
         if(stack != null && stack.getItem() != null && this.housing.hasResources(new ItemStack[]{stack})) {
            return stack;
         }
      }

      return null;
   }

   public ItemStack getAvailableAlkaline() {
      for(ItemStack stack : Gardening.getAlkalineFertilisers()) {
         if(stack != null && stack.getItem() != null && this.housing.hasResources(new ItemStack[]{stack})) {
            return stack;
         }
      }

      return null;
   }

   public void setData(EnumMoisture moisture2, EnumAcidity acidity2, boolean isManual, boolean isFertilised, ItemStack icon2, String name2) {
      this.moisture = moisture2;
      this.acidity = acidity2;
      this.isManual = isManual;
      this.fertilised = isFertilised;
      this.icon = icon2;
      this.name = name2;
   }
}
