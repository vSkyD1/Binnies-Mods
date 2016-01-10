package binnie.craftgui.minecraft.control;

import binnie.craftgui.controls.core.Control;
import binnie.craftgui.core.Attribute;
import binnie.craftgui.core.CraftGUI;
import binnie.craftgui.core.ITooltip;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.Tooltip;
import binnie.craftgui.core.geometry.IArea;
import binnie.craftgui.resource.minecraft.CraftGUITexture;

public class ControlHelp extends Control implements ITooltip {
   public ControlHelp(IWidget parent, float x, float y) {
      super(parent, x, y, 16.0F, 16.0F);
      this.addAttribute(Attribute.MouseOver);
   }

   public void onRenderBackground() {
      CraftGUI.Render.texture((Object)CraftGUITexture.HelpButton, (IArea)this.getArea());
   }

   public void getTooltip(Tooltip tooltip) {
      tooltip.setType(Tooltip.Type.Help);
      tooltip.add("Help");
      tooltip.add("To activate help tooltips,");
      tooltip.add("hold down the tab key and");
      tooltip.add("mouse over controls.");
   }

   public void getHelpTooltip(Tooltip tooltip) {
      this.getTooltip(tooltip);
   }
}
