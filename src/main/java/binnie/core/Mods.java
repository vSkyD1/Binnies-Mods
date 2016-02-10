package binnie.core;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;
import cpw.mods.fml.common.versioning.Restriction;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Mods {
    public static Mod Forestry;
    public static Mod IC2;
    public static Mod Botania;
    private static boolean WARN;

    static {
        Mods.Forestry = new Mod("Forestry") {
            @Override
            public boolean dev() {
                final String forVersion = Loader.instance().getIndexedModList().get("Forestry").getVersion();
                final Restriction rest = new Restriction((ArtifactVersion) new DefaultArtifactVersion("3.6"), true, (ArtifactVersion) null, false);
                return rest.containsVersion((ArtifactVersion) new DefaultArtifactVersion(forVersion));
            }
        };
        Mods.IC2 = new Mod("IC2");
        Mods.Botania = new Mod("Botania");
        Mods.WARN = true;
    }

    private static Item findItem(final String modId, final String name) {
        final Item stack = GameRegistry.findItem(modId, name);
        if (stack == null && Mods.WARN && modId == "Forestry") {
            throw new RuntimeException("Item not found: " + modId + ":" + name);
        }
        return stack;
    }

    private static ItemStack findItemStack(final String modId, final String name, final int stackSize) {
        final ItemStack stack = GameRegistry.findItemStack(modId, name, stackSize);
        if (stack == null && Mods.WARN && modId == "Forestry") {
            throw new RuntimeException("Stack not found: " + modId + ":" + name);
        }
        return stack;
    }

    private static Block findBlock(final String modId, final String name) {
        final Block stack = GameRegistry.findBlock(modId, name);
        if (stack == null && Mods.WARN && modId == "Forestry") {
            throw new RuntimeException("Block not found: " + modId + ":" + name);
        }
        return stack;
    }

    public static class Mod {
        private String id;

        private Mod(final String id) {
            this.id = id;
        }

        public Item item(final String name) {
            return findItem(this.id, name);
        }

        public Block block(final String name) {
            return findBlock(this.id, name);
        }

        public ItemStack stack(final String name, final int stackSize) {
            return findItemStack(this.id, name, stackSize);
        }

        public ItemStack stack(final String name) {
            return this.stack(name, 1);
        }

        public ItemStack stack(final String string, final int i, final int j) {
            return new ItemStack(this.item(string), i, j);
        }

        public boolean active() {
            return Loader.isModLoaded(this.id);
        }

        public boolean dev() {
            return false;
        }
    }
}
