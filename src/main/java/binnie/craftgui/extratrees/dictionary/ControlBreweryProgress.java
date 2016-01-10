package binnie.craftgui.extratrees.dictionary;

import binnie.core.machines.Machine;
import binnie.core.util.ItemStackSet;
import binnie.craftgui.core.Attribute;
import binnie.craftgui.core.CraftGUI;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.geometry.IArea;
import binnie.craftgui.core.geometry.IPoint;
import binnie.craftgui.minecraft.Window;
import binnie.craftgui.minecraft.control.ControlProgressBase;
import binnie.craftgui.resource.Texture;
import binnie.craftgui.resource.minecraft.StandardTexture;
import binnie.extratrees.core.ExtraTreeTexture;
import binnie.extratrees.machines.Brewery;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

public class ControlBreweryProgress extends ControlProgressBase {
   static Texture Brewery = new StandardTexture(0, 69, 34, 39, ExtraTreeTexture.Gui);
   static Texture BreweryOverlay = new StandardTexture(34, 69, 34, 39, ExtraTreeTexture.Gui);

   public void onRenderBackground() {
      CraftGUI.Render.texture(Brewery, new IPoint(0.0F, 0.0F));
      Brewery.ComponentBreweryLogic logic = (Brewery.ComponentBreweryLogic)Machine.getInterface(Brewery.ComponentBreweryLogic.class, Window.get(this).getInventory());
      if(logic.currentCrafting != null) {
         if(logic.currentCrafting.currentInput != null) {
            int fermentedHeight = (int)(32.0F * logic.getProgress() / 100.0F);
            CraftGUI.Render.limitArea(new IArea((new IPoint(1.0F, 6.0F)).add(this.getAbsolutePosition()), new IPoint(32.0F, (float)(32 - fermentedHeight))));
            GL11.glEnable(3089);
            this.renderFluid(logic.currentCrafting.currentInput, new IPoint(1.0F, 6.0F));
            this.renderFluid(logic.currentCrafting.currentInput, new IPoint(17.0F, 6.0F));
            this.renderFluid(logic.currentCrafting.currentInput, new IPoint(1.0F, 22.0F));
            this.renderFluid(logic.currentCrafting.currentInput, new IPoint(17.0F, 22.0F));
            GL11.glDisable(3089);
            CraftGUI.Render.limitArea(new IArea((new IPoint(1.0F, (float)(38 - fermentedHeight))).add(this.getAbsolutePosition()), new IPoint(32.0F, (float)fermentedHeight)));
            GL11.glEnable(3089);
            this.renderFluid(Brewery.getOutput(logic.currentCrafting), new IPoint(1.0F, 6.0F));
            this.renderFluid(Brewery.getOutput(logic.currentCrafting), new IPoint(17.0F, 6.0F));
            this.renderFluid(Brewery.getOutput(logic.currentCrafting), new IPoint(1.0F, 22.0F));
            this.renderFluid(Brewery.getOutput(logic.currentCrafting), new IPoint(17.0F, 22.0F));
            GL11.glDisable(3089);
            ItemStackSet stacks = new ItemStackSet();

            for(ItemStack stack : logic.currentCrafting.inputs) {
               stacks.add(stack);
            }

            stacks.add(logic.currentCrafting.ingr);
            int x = 1;
            int y = 6;

            for(ItemStack stack : stacks) {
               CraftGUI.Render.item(new IPoint((float)x, (float)y), stack);
               x += 16;
               if(x > 18) {
                  x = 1;
                  y += 16;
               }
            }

         }
      }
   }

   public void onRenderForeground() {
   }

   protected ControlBreweryProgress(IWidget parent, float x, float y) {
      super(parent, x, y, 34.0F, 39.0F);
      this.addAttribute(Attribute.MouseOver);
   }

   public void renderFluid(FluidStack fluid, IPoint pos) {
      int hex = fluid.getFluid().getColor(fluid);
      int r = (hex & 16711680) >> 16;
      int g = (hex & '\uff00') >> 8;
      int b = hex & 255;
      IIcon icon = fluid.getFluid().getIcon();
      GL11.glColor4f((float)r / 255.0F, (float)g / 255.0F, (float)b / 255.0F, 1.0F);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      CraftGUI.Render.iconBlock(pos, fluid.getFluid().getIcon());
      GL11.glDisable(3042);
   }
}
