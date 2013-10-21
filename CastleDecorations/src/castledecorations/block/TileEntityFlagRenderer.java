package castledecorations.block;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import castledecorations.mod_Castle;
import castledecorations.models.ModelFlagSmall;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityFlagRenderer extends TileEntitySpecialRenderer{

	private ModelFlagSmall model;

	public TileEntityFlagRenderer() {
		model = new ModelFlagSmall();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		renderModelAt(tileentity, d0, d1, d2, f);
	}
	
	public void renderModelAt(TileEntity tile, double var2, double var4, double var6, double var8) {
		int meta = tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) var2, (float) var4 + 1.0F, (float) var6 + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F - 1F, 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(mod_Castle.modid + ":" + "BlockFlagSmall.png"));

		model.render(0.0625F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}


}
