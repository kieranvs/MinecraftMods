package kieranvs.footpaths;

import scala.util.Random;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityOcelot;

public class ForgeListener {
	
	Random rand;
	
	public ForgeListener(){
		rand = new Random();
	}
	
	@SubscribeEvent
	public void MobUpdate(LivingEvent.LivingUpdateEvent evt){
		if(evt.entity instanceof EntityPlayer){
			doTheThing(3f, evt.entity, -1, -2, 0);
		} 
		if(evt.entity instanceof EntitySheep){
			return; //So sheepies don't trample all their grass in farms?
		}
		if(evt.entity instanceof EntityCow || evt.entity instanceof EntityHorse){
			doTheThing(5f, evt.entity, -1, -1, 0);
		}
		if(evt.entity instanceof EntityChicken || evt.entity instanceof EntityWolf || evt.entity instanceof Entity){
			doTheThing(1f, evt.entity, -1, -1, 0);
		} 
		else {
			doTheThing(3f, evt.entity, -1, -1, 0);
		}
	}
	
//	@SubscribeEvent
//	public void PlayerUpdate(PlayerTickEvent evt){
//		System.out.println("Player");
//		doTheThing(1, evt.player, 0, -2, -2, true);
//	}
	
	public void doTheThing(float fatty, Entity ent, int offsetX, int offsetY, int offsetZ){
		if(ent.onGround && ent.worldObj.getBlock((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ) == Blocks.grass){
			ent.worldObj.setBlock((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ, mod_Footpaths.dirtPathBlock);
		}
		if(ent.onGround && ent.worldObj.getBlock((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ) == mod_Footpaths.dirtPathBlock){
			if(ent.prevPosX != ent.posX || ent.prevPosY != ent.posY|| ent.prevPosZ != ent.posZ){
				int meta = ent.worldObj.getBlockMetadata((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ);
				if(meta == 15){
					return;
				}
				if(rand.nextInt(100) == 0 || true){
					System.out.println("Levelling up to " + (meta + 1));
					ent.worldObj.setBlockMetadataWithNotify((int)(ent.posX) + offsetX, (int)(ent.posY) + offsetY, (int)(ent.posZ) + offsetZ, meta + 1, 0x02);				
				}
			}
		}

	}

}
