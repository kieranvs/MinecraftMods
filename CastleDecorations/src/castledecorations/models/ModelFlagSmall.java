package castledecorations.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFlagSmall extends ModelBase {

	ModelRenderer FlagPole;
	ModelRenderer Flag;

	public ModelFlagSmall(){
		textureWidth = 64;
		textureHeight = 64;

		FlagPole = new ModelRenderer(this, 0, 0);
		FlagPole.addBox(0F, 0F, 0F, 4, 32, 4);
		FlagPole.setRotationPoint(-2F, -8F, -2F);
		FlagPole.setTextureSize(64, 64);
		FlagPole.mirror = true;
		setRotation(FlagPole, 0F, 0F, 0F);
		Flag = new ModelRenderer(this, 0, 39);
		Flag.addBox(0F, 0F, 0F, 19, 14, 2);
		Flag.setRotationPoint(2F, -7F, -1F);
		Flag.setTextureSize(64, 64);
		Flag.mirror = true;
		setRotation(Flag, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		render(f5);
	}


	public void render(float f5){
		FlagPole.render(f5);
		Flag.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
