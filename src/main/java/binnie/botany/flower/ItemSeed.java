package binnie.botany.flower;

import binnie.botany.api.EnumFlowerStage;
import binnie.botany.flower.ItemBotany;

public class ItemSeed extends ItemBotany {
   public ItemSeed() {
      super("seed");
   }

   public EnumFlowerStage getStage() {
      return EnumFlowerStage.SEED;
   }

   public String getTag() {
      return " Germling";
   }
}
