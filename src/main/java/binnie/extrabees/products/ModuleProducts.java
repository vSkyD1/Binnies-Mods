package binnie.extrabees.products;

import binnie.core.IInitializable;
import binnie.core.Mods;
import binnie.extrabees.ExtraBees;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModuleProducts implements IInitializable {
    @Override
    public void preInit() {
        ExtraBees.honeyCrystal = new ItemHoneyCrystal();
        ExtraBees.honeyCrystalEmpty = new ItemHoneyCrystalEmpty();
        ExtraBees.honeyDrop = new ItemHoneyDrop();
        ExtraBees.comb = new ItemHoneyComb();
        ExtraBees.propolis = new ItemPropolis();
        OreDictionary.registerOre("ingotIron", Items.iron_ingot);
        OreDictionary.registerOre("ingotGold", Items.gold_ingot);
        OreDictionary.registerOre("gemDiamond", Items.diamond);
    }

    @Override
    public void init() {
        ItemHoneyComb.addSubtypes();
    }

    @Override
    public void postInit() {
        GameRegistry.addRecipe(new ItemStack(ExtraBees.honeyCrystalEmpty), "#@#", "@#@", "#@#", '@', Mods.Forestry.stack("honeyDrop"), '#', EnumHoneyDrop.ENERGY.get(1));
        for (final EnumHoneyComb info : EnumHoneyComb.values()) {
            info.addRecipe();
        }
        for (final EnumHoneyDrop info2 : EnumHoneyDrop.values()) {
            info2.addRecipe();
        }
        for (final EnumPropolis info3 : EnumPropolis.values()) {
            info3.addRecipe();
        }
    }
}
