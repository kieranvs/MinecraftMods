package kieranvs.footpaths;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import kieranvs.footpaths.client.FootpathsTab;
import kieranvs.footpaths.server.ServerProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = mod_Footpaths.modid, name = "Footpaths", version = mod_Footpaths.version)
public class mod_Footpaths {

	public static final String modid = "footpaths";
	public static final String version = "0.0.1";
	
	public static int renderId;
	public static int renderPass;
	
	public static CreativeTabs tabFootpaths = new FootpathsTab(CreativeTabs.getNextID(), "tabFootpaths");

	public static BlockDirtPath dirtPathBlock;
	
	@Instance("Footpaths")
	public static mod_Footpaths instance;

	@SidedProxy(clientSide = "kieranvs.footpaths.client.ClientProxy", serverSide = "kieranvs.footpaths.server.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ForgeListener());
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		instance = this;
		proxy.load();
		proxy.registerRenderers();
		
		dirtPathBlock = (BlockDirtPath) new BlockDirtPath().setBlockName("dirtPathBlock");
		GameRegistry.registerBlock(dirtPathBlock, "dirtPathBlock");
		
//		GameRegistry.registerTileEntity(TileEntityFlag.class, "Flag");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

}
