package binnie.core.liquid;

import binnie.core.BinnieCore;
import binnie.core.Mods;
import binnie.core.liquid.IFluidType;
import binnie.core.liquid.ItemFluidContainer;
import binnie.genetics.item.GeneticsItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

public enum FluidContainer {
   Bucket,
   Capsule,
   Refractory,
   Can,
   Glass,
   Cylinder;

   IIcon bottle;
   IIcon contents;
   ItemFluidContainer item;

   private FluidContainer() {
   }

   public int getMaxStackSize() {
      return this == Bucket?1:16;
   }

   @SideOnly(Side.CLIENT)
   public void updateIcons(IIconRegister register) {
      this.bottle = BinnieCore.proxy.getIcon(register, this == Cylinder?"binniecore":"forestry", "liquids/" + this.toString().toLowerCase() + ".bottle");
      this.contents = BinnieCore.proxy.getIcon(register, this == Cylinder?"binniecore":"forestry", "liquids/" + this.toString().toLowerCase() + ".contents");
   }

   public IIcon getBottleIcon() {
      return this.bottle;
   }

   public IIcon getContentsIcon() {
      return this.contents;
   }

   public String getName() {
      return BinnieCore.proxy.localise("item.container." + this.name().toLowerCase());
   }

   public boolean isActive() {
      return this.getEmpty() != null;
   }

   public ItemStack getEmpty() {
      switch(this) {
      case Bucket:
         return new ItemStack(Items.bucket, 1, 0);
      case Can:
         return Mods.Forestry.stack("canEmpty");
      case Capsule:
         return Mods.Forestry.stack("waxCapsule");
      case Glass:
         return new ItemStack(Items.glass_bottle, 1, 0);
      case Refractory:
         return Mods.Forestry.stack("refractoryEmpty");
      case Cylinder:
         return GeneticsItems.Cylinder.get(1);
      default:
         return null;
      }
   }

   public void registerContainerData(IFluidType fluid) {
      if(this.isActive()) {
         ItemStack filled = this.item.getContainer(fluid);
         ItemStack empty = this.getEmpty();
         if(filled != null && empty != null && fluid.get(1000) != null) {
            FluidContainerData data = new FluidContainerData(fluid.get(1000), filled, empty);
            FluidContainerRegistry.registerFluidContainer(data);
         }
      }
   }
}
