package kieranvs.footpaths.client;

import kieranvs.footpaths.mod_Footpaths;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

		if(mod_Footpaths.renderPass == 0){
			renderer.renderStandardBlock(Blocks.grass, x, y, z);
		}
		else {
			renderer.renderFaceYPos(null, x, y, z, mod_Footpaths.dirtPathBlock.tex);
		}

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return 0;
	}

}
