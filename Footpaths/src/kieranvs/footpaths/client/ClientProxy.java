package kieranvs.footpaths.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import kieranvs.footpaths.mod_Footpaths;
import kieranvs.footpaths.server.ServerProxy;

public class ClientProxy extends ServerProxy {
	
	@Override
	public void registerRenderers() {
		mod_Footpaths.renderId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mod_Footpaths.renderId, new BlockRenderer());
	}

}
