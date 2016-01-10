package binnie.extratrees.block;

import binnie.Binnie;
import binnie.core.block.BlockMetadata;
import binnie.core.block.IBlockMetadata;
import binnie.core.block.TileEntityMetadata;
import binnie.extratrees.ExtraTrees;
import binnie.extratrees.block.PlankType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.core.Tabs;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWood;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockETPlanks extends BlockWood implements IBlockMetadata {
   public BlockETPlanks() {
      super();
      this.setBlockName("planks");
      this.setCreativeTab(Tabs.tabArboriculture);
      this.setResistance(5.0F);
      this.setHardness(2.0F);
      this.setStepSound(soundTypeWood);
   }

   public ArrayList getDrops(World world, int x, int y, int z, int blockMeta, int fortune) {
      return BlockMetadata.getBlockDropped(this, world, x, y, z, blockMeta);
   }

   public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
      return BlockMetadata.breakBlock(this, player, world, x, y, z);
   }

   public TileEntity createNewTileEntity(World var1, int i) {
      return new TileEntityMetadata();
   }

   public boolean hasTileEntity(int meta) {
      return true;
   }

   public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6) {
      super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
      TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
      return tileentity != null?tileentity.receiveClientEvent(par5, par6):false;
   }

   public int getPlacedMeta(ItemStack stack, World world, int x, int y, int z, ForgeDirection clickedBlock) {
      return TileEntityMetadata.getItemDamage(stack);
   }

   public int getDroppedMeta(int blockMeta, int tileMeta) {
      return tileMeta;
   }

   public String getBlockName(ItemStack par1ItemStack) {
      int meta = TileEntityMetadata.getItemDamage(par1ItemStack);
      return Binnie.Language.localise(ExtraTrees.instance, "block.plank.name", new Object[]{PlankType.ExtraTreePlanks.values()[meta].getName()});
   }

   public void getBlockTooltip(ItemStack par1ItemStack, List par3List) {
   }

   public void dropAsStack(World world, int x, int y, int z, ItemStack drop) {
      this.dropBlockAsItem(world, x, y, z, drop);
   }

   public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List itemList) {
      for(int i = 0; i < PlankType.ExtraTreePlanks.values().length; ++i) {
         itemList.add(TileEntityMetadata.getItemStack(this, i));
      }

   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
      TileEntityMetadata tile = TileEntityMetadata.getTile(world, x, y, z);
      return tile != null?this.getIcon(side, tile.getTileMetadata()):super.getIcon(world, x, y, z, side);
   }

   public IIcon getIcon(int side, int meta) {
      return PlankType.ExtraTreePlanks.values()[meta].getIcon();
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister register) {
      for(PlankType.ExtraTreePlanks plank : PlankType.ExtraTreePlanks.values()) {
         plank.loadIcon(register);
      }

   }

   public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
      super.breakBlock(par1World, par2, par3, par4, par5, par6);
      par1World.removeTileEntity(par2, par3, par4);
   }

   public boolean isWood(IBlockAccess world, int x, int y, int z) {
      return true;
   }

   public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
      return 20;
   }

   public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
      return true;
   }

   public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
      return 5;
   }

   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
      return BlockMetadata.getPickBlock(world, x, y, z);
   }
}
