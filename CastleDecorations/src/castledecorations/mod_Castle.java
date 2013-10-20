package castledecorations;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import castledecorations.client.CastleDecorationsTab;
import castledecorations.server.ServerProxy;
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

@Mod(modid = mod_Castle.modid, name = "CastleDecorations", version = mod_Castle.version)
public class mod_Castle {

	public static final String modid = "castledecorations";
	public static final String version = "0.0.1";
	
	public static int renderId;
	
	public static CreativeTabs tabCastleDecorations = new CastleDecorationsTab(CreativeTabs.getNextID(), "tabCastleDecorations");

	public static BlockFlag flagBlock;
	
	@Instance("CastleDecorations")
	public static mod_Castle instance;

	@SidedProxy(clientSide = "castledecorations.client.ClientProxy", serverSide = "castledecorations.server.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		instance = this;
		
		proxy.load();
		
		flagBlock = (BlockFlag) new BlockFlag(450, Material.rock).setUnlocalizedName("flagBlock");
		GameRegistry.registerBlock(flagBlock, "flagBlock");
		LanguageRegistry.addName(flagBlock, "Flag");


	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

}
