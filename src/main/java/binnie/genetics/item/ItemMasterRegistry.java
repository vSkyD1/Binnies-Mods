package binnie.genetics.item;

import binnie.genetics.CreativeTabGenetics;
import binnie.genetics.Genetics;
import binnie.genetics.core.GeneticsGUI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMasterRegistry extends Item {
    public ItemMasterRegistry() {
        this.setCreativeTab(CreativeTabGenetics.instance);
        this.setUnlocalizedName("masterRegistry");
        this.setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister register) {
        this.itemIcon = Genetics.proxy.getIcon(register, "masterRegistry");
    }

    public ItemStack onItemRightClick(final ItemStack itemstack, final World world, final EntityPlayer player) {
        Genetics.proxy.openGui(GeneticsGUI.MasterRegistry, player, (int) player.posX, (int) player.posY, (int) player.posZ);
        return itemstack;
    }

    public String getItemStackDisplayName(final ItemStack i) {
        return "Master Registry";
    }
}
