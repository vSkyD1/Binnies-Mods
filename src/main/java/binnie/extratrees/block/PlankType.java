package binnie.extratrees.block;

import binnie.core.Mods;
import binnie.core.block.TileEntityMetadata;
import binnie.extratrees.ExtraTrees;
import binnie.extratrees.api.CarpentryManager;
import binnie.extratrees.block.IFenceProvider;
import binnie.extratrees.block.IPlankType;
import binnie.extratrees.block.WoodManager;
import com.google.common.base.Optional;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class PlankType {
   public static final int MAX_PLANKS = 256;

   public PlankType() {
      super();
   }

   public static void setup() {
      for(PlankType.VanillaPlanks plank : PlankType.VanillaPlanks.values()) {
         CarpentryManager.carpentryInterface.registerCarpentryWood(plank.ordinal(), plank);
      }

      for(PlankType.ExtraTreePlanks plank : PlankType.ExtraTreePlanks.values()) {
         CarpentryManager.carpentryInterface.registerCarpentryWood(plank.ordinal() + 32, plank);
      }

      for(PlankType.ForestryPlanks plank : PlankType.ForestryPlanks.values()) {
         CarpentryManager.carpentryInterface.registerCarpentryWood(plank.ordinal() + 128, plank);
      }

      for(PlankType.ExtraBiomesPlank plank : PlankType.ExtraBiomesPlank.values()) {
         CarpentryManager.carpentryInterface.registerCarpentryWood(plank.ordinal() + 192, plank);
      }

   }

   public static enum ExtraBiomesPlank implements IPlankType {
      Redwood(10185538),
      Fir(8288074),
      Acacia(12561022);

      int color;

      private ExtraBiomesPlank(int color) {
         this.color = color;
      }

      public String getName() {
         return ExtraTrees.proxy.localise("block.planks.ebxl." + this.toString().toLowerCase());
      }

      public String getDescription() {
         return ExtraTrees.proxy.localise("block.planks.ebxl." + this.toString().toLowerCase() + ".desc");
      }

      public int getColour() {
         return this.color;
      }

      public ItemStack getStack() {
         try {
            Class clss = Class.forName("extrabiomes.api.Stuff");
            Block block = (Block)((Optional)clss.getField("planks").get((Object)null)).get();
            return new ItemStack(block, 1, this.ordinal());
         } catch (Exception var3) {
            return null;
         }
      }

      public IIcon getIcon() {
         if(this.getStack() != null) {
            int meta = this.getStack().getItemDamage();
            Block block = ((ItemBlock)this.getStack().getItem()).field_150939_a;
            return block.getIcon(2, meta);
         } else {
            return null;
         }
      }
   }

   public static enum ExtraTreePlanks implements IPlankType, IFenceProvider {
      Fir(12815444),
      Cedar(14181940),
      Hemlock(13088108),
      Cypress(16169052),
      Fig(13142058),
      Beech(14784849),
      Alder(12092755),
      Hazel(13480341),
      Hornbeam(12818528),
      Box(16511430),
      Butternut(15510138),
      Hickory(14333070),
      Whitebeam(13222585),
      Elm(15772004),
      Apple(6305064),
      Yew(14722426),
      Pear(12093805),
      Hawthorn(13402978),
      Rowan(13610394),
      Elder(12489337),
      Maclura(15970862),
      Syzgium(15123393),
      Brazilwood(7487063),
      Logwood(10762028),
      Iroko(7681024),
      Locust(12816736),
      Eucalyptus(16165771),
      Purpleheart(5970991),
      Ash(16107368),
      Holly(16512743),
      Olive(11578760),
      Sweetgum(13997656),
      Rosewood(7738624),
      Gingko(16050106),
      PinkIvory(15502496);

      int color;
      IIcon icon;

      private ExtraTreePlanks(int color) {
         this.color = color;
      }

      public String getName() {
         return ExtraTrees.proxy.localise("block.planks." + this.toString().toLowerCase());
      }

      public String getDescription() {
         return ExtraTrees.proxy.localise("block.planks." + this.toString().toLowerCase() + ".desc");
      }

      public int getColour() {
         return this.color;
      }

      public ItemStack getStack() {
         return TileEntityMetadata.getItemStack(ExtraTrees.blockPlanks, this.ordinal());
      }

      public IIcon loadIcon(IIconRegister register) {
         this.icon = ExtraTrees.proxy.getIcon(register, "planks/" + this.toString());
         return this.icon;
      }

      public IIcon getIcon() {
         return this.icon;
      }

      public ItemStack getFence() {
         return TileEntityMetadata.getItemStack(ExtraTrees.blockFence, WoodManager.getPlankTypeIndex(this));
      }
   }

   public static enum ForestryPlanks implements IPlankType, IFenceProvider {
      LARCH(14131085),
      TEAK(8223075),
      ACACIA(9745287),
      LIME(13544048),
      CHESTNUT(12298845),
      WENGE(6182474),
      BAOBAB(9608290),
      SEQUOIA(10050135),
      KAPOK(8156212),
      EBONY(3946288),
      MAHOGANY(7749432),
      BALSA(11117209),
      WILLOW(11710818),
      WALNUT(6836802),
      GREENHEART(5144156),
      CHERRY(11895348),
      MAHOE(8362154),
      POPLAR(13619074),
      PALM(13271115),
      PAPAYA(14470005),
      PINE(12885585),
      PLUM(11364479),
      MAPLE(11431211),
      CITRUS(10266653),
      GIGANTEUM(5186590),
      IPE(5057822),
      PADAUK(11756341),
      COCOBOLO(7541506),
      ZEBRAWOOD(10912334);

      int color;

      private ForestryPlanks(int color) {
         this.color = color;
      }

      public String getName() {
         return ExtraTrees.proxy.localise("block.planks.forestry." + this.toString().toLowerCase());
      }

      public String getDescription() {
         return ExtraTrees.proxy.localise("block.planks.forestry." + this.toString().toLowerCase() + ".desc");
      }

      public int getColour() {
         return this.color;
      }

      public ItemStack getStack() {
         int n = this.ordinal() / 16 + 1;
         Item stack = Mods.Forestry.item("planks" + (n == 1?"":Integer.valueOf(n)));
         return new ItemStack(stack, 1, this.ordinal() % 16);
      }

      public IIcon getIcon() {
         if(this.getStack() != null) {
            int meta = this.getStack().getItemDamage();
            Block block = ((ItemBlock)this.getStack().getItem()).field_150939_a;
            return block.getIcon(2, meta);
         } else {
            return null;
         }
      }

      public ItemStack getFence() {
         int ordinal;
         for(ordinal = this.ordinal(); ordinal >= 16; ordinal -= 16) {
            ;
         }

         int n = this.ordinal() / 16 + 1;
         ItemStack fence = Mods.Forestry.stack("fences" + (n == 1?"":Integer.valueOf(n)));
         fence.setItemDamage(ordinal);
         return fence;
      }
   }

   public static enum VanillaPlanks implements IPlankType {
      OAK(11833434),
      SPRUCE(8412726),
      BIRCH(14139781),
      JUNGLE(11632732),
      ACACIA(12215095),
      DARKOAK(4599061);

      int color;

      private VanillaPlanks(int color) {
         this.color = color;
      }

      public String getName() {
         return ExtraTrees.proxy.localise("block.planks.vanilla." + this.toString().toLowerCase());
      }

      public String getDescription() {
         return ExtraTrees.proxy.localise("block.planks.vanilla." + this.toString().toLowerCase() + ".desc");
      }

      public int getColour() {
         return this.color;
      }

      public ItemStack getStack() {
         return new ItemStack(Blocks.planks, 1, this.ordinal());
      }

      public IIcon getIcon() {
         if(this.getStack() != null) {
            int meta = this.getStack().getItemDamage();
            Block block = Blocks.planks;
            return block.getIcon(2, meta);
         } else {
            return null;
         }
      }
   }
}
