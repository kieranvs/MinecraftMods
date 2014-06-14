package kieranvs.footpaths;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class Utils {
	
	public static EntityPlayer getPlayerByName(String name){
		int numDimensions = MinecraftServer.getServer().worldServers.length;
		for(int x = 0; x < numDimensions; x++){
			if(MinecraftServer.getServer().worldServers[x].getPlayerEntityByName(name) != null){
				return MinecraftServer.getServer().worldServers[x].getPlayerEntityByName(name);
			}
		}

		return null;
	}

}
