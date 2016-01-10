package binnie.botany.farm;

import binnie.botany.Botany;
import binnie.botany.api.EnumFlowerStage;
import binnie.botany.api.IFlower;
import binnie.botany.core.BotanyCore;
import binnie.botany.gardening.Gardening;
import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FarmableFlower implements IFarmable {
   public FarmableFlower() {
      super();
   }

   public boolean isSaplingAt(World world, int x, int y, int z) {
      return world.getBlock(x, y, z) == Botany.flower;
   }

   public ICrop getCropAt(World world, int x, int y, int z) {
      return null;
   }

   public boolean isGermling(ItemStack itemstack) {
      EnumFlowerStage stage = BotanyCore.speciesRoot.getType(itemstack);
      return stage == EnumFlowerStage.FLOWER || stage == EnumFlowerStage.SEED;
   }

   public boolean isWindfall(ItemStack itemstack) {
      return false;
   }

   public boolean plantSaplingAt(EntityPlayer player, ItemStack germling, World world, int x, int y, int z) {
      IFlower flower = BotanyCore.getFlowerRoot().getMember(germling);
      Gardening.plant(world, x, y, z, flower, player.getGameProfile());
      return true;
   }
}
