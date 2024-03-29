package kieranvs.footpaths;

import java.util.EnumMap;

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
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = mod_Footpaths.modid, name = "Footpaths", version = mod_Footpaths.version)
public class mod_Footpaths {

	public static final String modid = "footpaths";
	public static final String version = "1.0";
	
	public static int renderId;
	public static int renderPass;
	
	public static CreativeTabs tabFootpaths = new FootpathsTab(CreativeTabs.getNextID(), "tabFootpaths");

	public static BlockDirtPath dirtPathBlock;
	
	public static EnumMap<Side, FMLEmbeddedChannel> ChannelFootpathsGeneral;
	
	@Instance("Footpaths")
	public static mod_Footpaths instance;

	@SidedProxy(clientSide = "kieranvs.footpaths.client.ClientProxy", serverSide = "kieranvs.footpaths.server.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		NetworkHandler handler = new NetworkHandler();
		mod_Footpaths.ChannelFootpathsGeneral = NetworkRegistry.INSTANCE.newChannel("FootpathsGeneral", handler);
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
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

}
