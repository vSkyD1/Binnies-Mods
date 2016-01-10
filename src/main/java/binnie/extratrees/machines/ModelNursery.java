package binnie.extratrees.machines;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNursery extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape13;

   public ModelNursery() {
      super();
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(-5.0F, -5.0F, -5.0F, 10, 10, 10);
      this.Shape1.setRotationPoint(0.0F, 16.0F, 0.0F);
      this.Shape1.setTextureSize(64, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 20);
      this.Shape2.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape2.setRotationPoint(-5.0F, 16.0F, -5.0F);
      this.Shape2.setTextureSize(64, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 20);
      this.Shape3.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape3.setRotationPoint(-5.0F, 16.0F, 5.0F);
      this.Shape3.setTextureSize(64, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 20);
      this.Shape4.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape4.setRotationPoint(5.0F, 16.0F, -5.0F);
      this.Shape4.setTextureSize(64, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 20);
      this.Shape5.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape5.setRotationPoint(5.0F, 16.0F, 5.0F);
      this.Shape5.setTextureSize(64, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 20);
      this.Shape6.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape6.setRotationPoint(5.0F, 21.0F, 0.0F);
      this.Shape6.setTextureSize(64, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 1.570796F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 20);
      this.Shape7.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape7.setRotationPoint(5.0F, 11.0F, 0.0F);
      this.Shape7.setTextureSize(64, 64);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 1.570796F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 0, 20);
      this.Shape8.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape8.setRotationPoint(-5.0F, 21.0F, 0.0F);
      this.Shape8.setTextureSize(64, 64);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 1.570796F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 0, 20);
      this.Shape9.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape9.setRotationPoint(-5.0F, 11.0F, 0.0F);
      this.Shape9.setTextureSize(64, 64);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 1.570796F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 0, 20);
      this.Shape10.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape10.setRotationPoint(0.0F, 11.0F, 5.0F);
      this.Shape10.setTextureSize(64, 64);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 1.570796F);
      this.Shape11 = new ModelRenderer(this, 0, 20);
      this.Shape11.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape11.setRotationPoint(0.0F, 21.0F, -5.0F);
      this.Shape11.setTextureSize(64, 64);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 1.570796F);
      this.Shape12 = new ModelRenderer(this, 0, 20);
      this.Shape12.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape12.setRotationPoint(0.0F, 21.0F, 5.0F);
      this.Shape12.setTextureSize(64, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 1.570796F);
      this.Shape13 = new ModelRenderer(this, 0, 20);
      this.Shape13.addBox(-1.0F, -8.0F, -1.0F, 2, 16, 2);
      this.Shape13.setRotationPoint(0.0F, 11.0F, -5.0F);
      this.Shape13.setTextureSize(64, 64);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 1.570796F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape11.render(f5);
      this.Shape12.render(f5);
      this.Shape13.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
