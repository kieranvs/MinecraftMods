package kieranvs.footpaths;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraftforge.event.entity.living.LivingEvent;
import scala.util.Random;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ForgeListener {

	//this solution only works for one player though...
	public static boolean isPlayerMoving;
	private Random rand;

	public ForgeListener(){
		rand = new Random();
		isPlayerMoving = false;
	}

	@SubscribeEvent
	public void MobUpdate(LivingEvent.LivingUpdateEvent evt){
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
			if(evt.entity instanceof EntityPlayer){
				sendPacket((int)evt.entity.prevPosX, (int)evt.entity.posX, (int)evt.entity.prevPosY, (int)evt.entity.posY, (int)evt.entity.prevPosZ, (int)evt.entity.posZ);
			} 
		}
		else{
			if(evt.entity instanceof EntitySheep){
				return; //So sheepies don't trample all their grass in farms?
				//do cows need that too?
			}
			if(evt.entity instanceof EntityCow || evt.entity instanceof EntityHorse){
				doTheThing(5, evt.entity, 0, -1, 0);
			}
			if(evt.entity instanceof EntityChicken || evt.entity instanceof EntityWolf){
				doTheThing(1, evt.entity, 0, -1, 0);
			} 
			//I got rid of else here because we can't have monster mobs trampling all the grass at night everywhere
		}
	}

	public void doTheThing(int fatty, Entity ent, int offsetX, int offsetY, int offsetZ){
		if(ent.onGround && ent.worldObj.getBlock((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ) == Blocks.grass){
			ent.worldObj.setBlock((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ, mod_Footpaths.dirtPathBlock);
		}
		if(ent.onGround && ent.worldObj.getBlock((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ) == mod_Footpaths.dirtPathBlock){
			if(ent instanceof EntityPlayer){
				if(isPlayerMoving){
					int meta = ent.worldObj.getBlockMetadata((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ);
					if(meta == 15){
						return;
					}
					if(rand.nextInt(20/fatty) == 0){ 
						ent.worldObj.setBlockMetadataWithNotify((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ, meta + 1, 0x02);				
					}
				}
			}else{
				if(ent.prevPosX != ent.posX || ent.prevPosY != ent.posY|| ent.prevPosZ != ent.posZ){
					int meta = ent.worldObj.getBlockMetadata((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ);
					if(meta == 15){
						return;
					}
					if(rand.nextInt(100/fatty) == 0){ 
						ent.worldObj.setBlockMetadataWithNotify((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ, meta + 1, 0x02);				
					}
				}
			}
		}

	}

	//why are you sending all this bs to the server every tick, then waiting for the server to work out whether the player moved?
	//we need a solution which only sends packets when the player is actually moving
	//and we're going to need to have an arraylist of all the players with booleans about whether they're moving or something
	private static void sendPacket(int prevX, int x, int prevY, int y, int prevZ, int z){
		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt(prevX);
			pb.writeInt(x);
			pb.writeInt(prevY);
			pb.writeInt(y);
			pb.writeInt(prevZ);
			pb.writeInt(z);
			pb.writeInt(Minecraft.getMinecraft().thePlayer.getDisplayName().getBytes().length);
			pb.writeBytes(Minecraft.getMinecraft().thePlayer.getDisplayName().getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		C17PacketCustomPayload packet = new C17PacketCustomPayload("FootpathsGeneral", pb);

		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(packet);		
	}


}
