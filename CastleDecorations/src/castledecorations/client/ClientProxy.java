package castledecorations.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import castledecorations.mod_Castle;
import castledecorations.block.TileEntityFlag;
import castledecorations.block.TileEntityFlagRenderer;
import castledecorations.server.ServerProxy;

public class ClientProxy extends ServerProxy {

	@Override
	public void registerRenderers() {
		mod_Castle.renderId = RenderingRegistry.getNextAvailableRenderId();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFlag.class, new TileEntityFlagRenderer());
	}
}
