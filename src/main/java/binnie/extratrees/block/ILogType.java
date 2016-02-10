package binnie.extratrees.block;

import binnie.core.Mods;
import binnie.core.block.TileEntityMetadata;
import binnie.extratrees.ExtraTrees;
import binnie.extratrees.api.IDesignMaterial;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public interface ILogType {
    void placeBlock(final World p0, final int p1, final int p2, final int p3);

    ItemStack getItemStack();

    int getColour();

    public enum ExtraTreeLog implements ILogType {
        Apple("Apple", 8092283, (IDesignMaterial) PlankType.ExtraTreePlanks.Apple),
        Fig("Fig", 8418135, (IDesignMaterial) PlankType.ExtraTreePlanks.Fig),
        Butternut("Butternut", 12037536, (IDesignMaterial) PlankType.ExtraTreePlanks.Butternut),
        Cherry("Cherry", 7432272, (IDesignMaterial) PlankType.ForestryPlanks.CHERRY),
        Whitebeam("Whitebeam", 7891565, (IDesignMaterial) PlankType.ExtraTreePlanks.Whitebeam),
        Rowan("Rowan", 11972763, (IDesignMaterial) PlankType.ExtraTreePlanks.Rowan),
        Hemlock("Hemlock", 11379611, (IDesignMaterial) PlankType.ExtraTreePlanks.Hemlock),
        Ash("Ash", 9013634, (IDesignMaterial) PlankType.ExtraTreePlanks.Ash),
        Alder("Alder", 13025464, (IDesignMaterial) PlankType.ExtraTreePlanks.Alder),
        Beech("Beech", 11702654, (IDesignMaterial) PlankType.ExtraTreePlanks.Beech),
        Hawthorn("Hawthorn", 6248261, (IDesignMaterial) PlankType.ExtraTreePlanks.Hawthorn),
        Banana("Banana", 8753743),
        Yew("Yew", 13745089, (IDesignMaterial) PlankType.ExtraTreePlanks.Yew),
        Cypress("Cypress", 10126467, (IDesignMaterial) PlankType.ExtraTreePlanks.Cypress),
        Fir("Fir", 8553346, (IDesignMaterial) PlankType.ExtraTreePlanks.Fir),
        Hazel("Hazel", 11180143, (IDesignMaterial) PlankType.ExtraTreePlanks.Hazel),
        Hickory("Hickory", 4076848, (IDesignMaterial) PlankType.ExtraTreePlanks.Hickory),
        Elm("Elm", 8684422, (IDesignMaterial) PlankType.ExtraTreePlanks.Elm),
        Elder("Elder", 14202996, (IDesignMaterial) PlankType.ExtraTreePlanks.Elder),
        Holly("Holly", 11905669, (IDesignMaterial) PlankType.ExtraTreePlanks.Holly),
        Hornbeam("Hornbeam", 10719862, (IDesignMaterial) PlankType.ExtraTreePlanks.Hornbeam),
        Cedar("Cedar", 11368015, (IDesignMaterial) PlankType.ExtraTreePlanks.Cedar),
        Olive("Olive", 8089706, (IDesignMaterial) PlankType.ExtraTreePlanks.Olive),
        Sweetgum("Sweetgum", 10592668, (IDesignMaterial) PlankType.ExtraTreePlanks.Sweetgum),
        Locust("Locust", 11381948, (IDesignMaterial) PlankType.ExtraTreePlanks.Locust),
        Pear("Pear", 11048825, (IDesignMaterial) PlankType.ExtraTreePlanks.Pear),
        Maclura("Maclura", 9131828, (IDesignMaterial) PlankType.ExtraTreePlanks.Maclura),
        Brazilwood("Brazilwood", 10387560, (IDesignMaterial) PlankType.ExtraTreePlanks.Brazilwood),
        Logwood("Logwood", 16376530, (IDesignMaterial) PlankType.ExtraTreePlanks.Logwood),
        Rosewood("Rosewood", 10061414, (IDesignMaterial) PlankType.ExtraTreePlanks.Rosewood),
        Purpleheart("Purpleheart", 9671330, (IDesignMaterial) PlankType.ExtraTreePlanks.Purpleheart),
        Iroko("Iroko", 6315099, (IDesignMaterial) PlankType.ExtraTreePlanks.Iroko),
        Gingko("Gingko", 11382428, (IDesignMaterial) PlankType.ExtraTreePlanks.Gingko),
        Eucalyptus("Eucalyptus", 15392474, (IDesignMaterial) PlankType.ExtraTreePlanks.Eucalyptus),
        Eucalyptus2("Eucalyptus", 8814181, (IDesignMaterial) PlankType.ExtraTreePlanks.Eucalyptus),
        Box("Box", 11235159, (IDesignMaterial) PlankType.ExtraTreePlanks.Box),
        Syzgium("Syzgium", 11235159, (IDesignMaterial) PlankType.ExtraTreePlanks.Syzgium),
        Eucalyptus3("Eucalyptus", 7123007, (IDesignMaterial) PlankType.ExtraTreePlanks.Eucalyptus),
        Cinnamon("Cinnamon", 8804412, (IDesignMaterial) PlankType.VanillaPlanks.JUNGLE),
        PinkIvory("Pink Ivory", 8349012, (IDesignMaterial) PlankType.ExtraTreePlanks.PinkIvory);

        String name;
        int color;
        IDesignMaterial plank;
        IIcon trunk;
        IIcon bark;

        private ExtraTreeLog(final String name, final int color) {
            this.plank = null;
            this.name = name;
            this.color = color;
        }

        private ExtraTreeLog(final String name, final int color, final IDesignMaterial plank) {
            this.plank = null;
            this.name = name;
            this.color = color;
            this.plank = plank;
        }

        public static void registerIcons(final IIconRegister register) {
            for (final ExtraTreeLog log : values()) {
                log.trunk = ExtraTrees.proxy.getIcon(register, "logs/" + log.toString().toLowerCase() + "Trunk");
                log.bark = ExtraTrees.proxy.getIcon(register, "logs/" + log.toString().toLowerCase() + "Bark");
            }
        }

        public String getName() {
            return this.name;
        }

        public void addRecipe() {
            if (this.plank == null) {
                return;
            }
            final ItemStack log = this.getItemStack();
            final ItemStack result = this.plank.getStack();
            result.stackSize = 4;
            GameRegistry.addShapelessRecipe(result, new Object[]{log});
        }

        @Override
        public void placeBlock(final World world, final int x, final int y, final int z) {
            world.setBlock(x, y, z, ExtraTrees.blockLog, 0, 2);
            if (world.getTileEntity(x, y, z) != null) {
                ((TileEntityMetadata) world.getTileEntity(x, y, z)).setTileMetadata(this.ordinal(), false);
            }
        }

        public IIcon getTrunk() {
            return this.trunk;
        }

        public IIcon getBark() {
            return this.bark;
        }

        @Override
        public ItemStack getItemStack() {
            return TileEntityMetadata.getItemStack(ExtraTrees.blockLog, this.ordinal()).copy();
        }

        @Override
        public int getColour() {
            return this.color;
        }
    }

    public enum ForestryLog implements ILogType {
        LARCH(1, 0, 6376529),
        TEAK(1, 1, 3486249),
        ACACIA(1, 2, 7565906),
        LIME(1, 3, 7431512),
        CHESTNUT(2, 0, 6183484),
        WENGE(2, 1, 6444875),
        BAOBAB(2, 2, 14326376),
        SEQUOIA(2, 3, 11563861),
        KAPOK(3, 0, 5396559),
        EBONY(3, 1, 10453073),
        MAHOGANY(3, 2, 9403501),
        BALSA(3, 3, 8551285),
        WILLOW(4, 0, 10590869),
        WALNUT(4, 1, 9474682),
        GREENHEART(4, 2, 7956050),
        CHERRY(4, 3, 6296064),
        MAHOE(5, 0, 6382152),
        POPLAR(5, 1, 9217671),
        PALM(5, 2, 8941379),
        PAPAYA(5, 3, 9069862),
        PINE(6, 0, 7558729),
        PLUM(6, 1, 11961953),
        MAPLE(6, 2, 9078657),
        CITRUS(6, 3, 5983033);

        int block;
        int metadata;
        int colour;

        private ForestryLog(final int blockOffset, final int meta, final int colour) {
            this.block = blockOffset;
            this.metadata = meta;
            this.colour = colour;
        }

        @Override
        public void placeBlock(final World world, final int x, final int y, final int z) {
            final Block block = Mods.Forestry.block("log" + this.block);
            world.setBlock(x, y, z, block, this.metadata, 2);
        }

        @Override
        public ItemStack getItemStack() {
            return new ItemStack(Mods.Forestry.item("log" + this.block), 1, this.metadata);
        }

        @Override
        public int getColour() {
            return this.colour;
        }
    }

    public enum VanillaLog implements ILogType {
        Oak(6376752),
        Spruce(2759179),
        Birch(6376752),
        Jungle(5456154);

        int colour;

        private VanillaLog(final int colour) {
            this.colour = colour;
        }

        @Override
        public void placeBlock(final World world, final int x, final int y, final int z) {
            world.setBlock(x, y, z, Blocks.log, this.ordinal(), 2);
        }

        @Override
        public ItemStack getItemStack() {
            return new ItemStack(Blocks.log, 1, this.ordinal());
        }

        @Override
        public int getColour() {
            return this.colour;
        }
    }
}
