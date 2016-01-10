package binnie.craftgui.resource.minecraft;

import binnie.core.resource.BinnieResource;
import binnie.core.resource.IBinnieTexture;
import binnie.craftgui.core.geometry.IArea;
import binnie.craftgui.core.geometry.IBorder;
import binnie.craftgui.resource.Texture;

public class PaddedTexture extends Texture {
   public PaddedTexture(int u, int v, int w, int h, int offset, IBinnieTexture textureFile, int leftPadding, int rightPadding, int topPadding, int bottomPadding) {
      this(u, v, w, h, offset, textureFile.getTexture(), leftPadding, rightPadding, topPadding, bottomPadding);
   }

   public PaddedTexture(int u, int v, int w, int h, int offset, BinnieResource textureFile, int leftPadding, int rightPadding, int topPadding, int bottomPadding) {
      super(new IArea((float)u, (float)v, (float)w, (float)h), new IBorder((float)topPadding, (float)rightPadding, (float)bottomPadding, (float)leftPadding), new IBorder((float)offset), textureFile);
   }
}
