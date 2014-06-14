package kieranvs.footpaths;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
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

	public static ConcurrentHashMap<String, Boolean> isPlayerMoving = new ConcurrentHashMap<String, Boolean>();
	private Random rand;

	public ForgeListener(){
		rand = new Random();
		//isPlayerMoving = false;
	}

	@SubscribeEvent
	public void MobUpdate(LivingEvent.LivingUpdateEvent evt){
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
			if(evt.entity instanceof EntityPlayer){
				//if(evt.entity.prevPosX != evt.entity.posX || evt.entity.prevPosY != evt.entity.posY || evt.entity.prevPosZ != evt.entity.posZ){
				EntityPlayer player = (EntityPlayer)evt.entity;
				sendPacket(player);
				doTheThing(3, evt.entity, -1, -2, 0);
				//}
			} 
		}
		else{
			if(evt.entity instanceof EntitySheep){
				return; //So sheepies don't trample all their grass in farms?
			}
			if(evt.entity instanceof EntityCow || evt.entity instanceof EntityHorse){
				doTheThing(5, evt.entity, 0, -1, 0);
			}
			if(evt.entity instanceof EntityPig){
				doTheThing(2, evt.entity, 0, -1, 0);
			}
			if(evt.entity instanceof EntityChicken || evt.entity instanceof EntityWolf){
				doTheThing(1, evt.entity, 0, -1, 0);
			} 
		}
	}

	public void doTheThing(int fatty, Entity ent, int offsetX, int offsetY, int offsetZ){
		if(ent.onGround && ent.worldObj.getBlock((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ) == Blocks.grass){
			ent.worldObj.setBlock((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ, mod_Footpaths.dirtPathBlock);
		}
		if(ent.onGround && ent.worldObj.getBlock((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ) == mod_Footpaths.dirtPathBlock){
			if(ent instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer)ent;
				if(isPlayerMoving.get(player.getDisplayName()) != null && isPlayerMoving.get(player.getDisplayName()) == true){
					int meta = ent.worldObj.getBlockMetadata((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ);
					if(meta == 15){
						return;
					}
					if(rand.nextInt(20/fatty) == 0 || true){  //TODO change
						ent.worldObj.setBlockMetadataWithNotify((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ, meta + 1, 0x02);				
					}

					isPlayerMoving.remove(player.getDisplayName());
					return;
				}
			}else{
				if(ent.prevPosX != ent.posX || ent.prevPosY != ent.posY|| ent.prevPosZ != ent.posZ){
					int meta = ent.worldObj.getBlockMetadata((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ);
					if(meta == 15){
						return;
					}
					if(rand.nextInt(100/fatty) == 0 || true){  //TODO change
						ent.worldObj.setBlockMetadataWithNotify((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ, meta + 1, 0x02);				
					}
					
					return;
				}
			}
		}

	}

	//we're going to need to have an arraylist of all the players with booleans about whether they're moving or something
	private static void sendPacket(EntityPlayer player){

		if((int)player.prevPosX != (int)player.posX || (int)player.prevPosY != (int)player.posY || (int)player.prevPosZ != (int)player.posZ){
			PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
			try {
				pb.writeBoolean(true);
				//			pb.writeInt(Minecraft.getMinecraft().thePlayer.getDisplayName().getBytes().length);
				//			pb.writeBytes(Minecraft.getMinecraft().thePlayer.getDisplayName().getBytes());
				pb.writeInt(player.getDisplayName().getBytes().length);
				pb.writeBytes(player.getDisplayName().getBytes());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			C17PacketCustomPayload packet = new C17PacketCustomPayload("FootpathsGeneral", pb);

			Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(packet);	
		}
		
		return;
	}


}
