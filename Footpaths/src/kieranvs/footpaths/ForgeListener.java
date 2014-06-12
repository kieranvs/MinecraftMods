package kieranvs.footpaths;

import scala.util.Random;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent;

public class ForgeListener {
	
	Random rand;
	
	public ForgeListener(){
		rand = new Random();
	}
	
	@SubscribeEvent
	public void MobUpdate(LivingEvent.LivingUpdateEvent evt){
		doTheThing(5, evt.entity, -1, -1, -1);
	}
	
	@SubscribeEvent
	public void PlayerUpdate(PlayerTickEvent evt){
		doTheThing(1, evt.player, 0, 0, 0);
	}
	
	public void doTheThing(float fatty, Entity ent, int offsetX, int offsetY, int offsetZ){
		if(ent.onGround && ent.worldObj.getBlock((int)(ent.posX), (int)(ent.posY) - 1, (int)(ent.posZ)) == Blocks.grass){
			ent.worldObj.setBlock((int)(ent.posX), (int)(ent.posY) - 1, (int)(ent.posZ), mod_Footpaths.dirtPathBlock);
		}
		if(ent.onGround && ent.worldObj.getBlock((int)(ent.posX), (int)(ent.posY) - 1, (int)(ent.posZ)) == mod_Footpaths.dirtPathBlock){
			if(ent.prevPosX != ent.posX || ent.prevPosY != ent.posY || ent.prevPosZ != ent.posZ){
				int meta = ent.worldObj.getBlockMetadata((int)(ent.posX), (int)(ent.posY) - 1, (int)(ent.posZ));
				if(meta == 15){
					return;
				}
				if(rand.nextInt(100) == 0 || true){
					System.out.println("Levelling up to " + (meta + 1));
					ent.worldObj.setBlockMetadataWithNotify((int)(ent.posX), (int)(ent.posY) - 1, (int)(ent.posZ), meta + 1, 0x02);				
				}
			}
		}

	}

}
