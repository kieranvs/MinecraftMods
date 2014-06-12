package kieranvs.footpaths.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import kieranvs.footpaths.mod_Footpaths;
import kieranvs.footpaths.server.ServerProxy;

public class ClientProxy extends ServerProxy {
	
	@Override
	public void registerRenderers() {
		mod_Footpaths.renderId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mod_Footpaths.renderId, new BlockRenderer());
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFlag.class, new TileEntityFlagRenderer());
	}

}
