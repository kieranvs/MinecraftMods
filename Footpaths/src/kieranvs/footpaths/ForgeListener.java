package kieranvs.footpaths;

import scala.util.Random;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent;

public class ForgeListener {
	
	Random rand;
	
	public ForgeListener(){
		rand = new Random();
	}
	
	@SubscribeEvent
	public void PlayerUpdate(LivingEvent.LivingUpdateEvent evt){
		if(evt.entity.onGround && evt.entity.worldObj.getBlock((int)(evt.entity.posX), (int)(evt.entity.posY) - 1, (int)(evt.entity.posZ)) == Blocks.grass){
			evt.entity.worldObj.setBlock((int)(evt.entity.posX), (int)(evt.entity.posY) - 1, (int)(evt.entity.posZ), mod_Footpaths.dirtPathBlock);
		}
		if(evt.entity.onGround && evt.entity.worldObj.getBlock((int)(evt.entity.posX), (int)(evt.entity.posY) - 1, (int)(evt.entity.posZ)) == mod_Footpaths.dirtPathBlock){
			if(evt.entity.prevPosX != evt.entity.posX || evt.entity.prevPosY != evt.entity.posY || evt.entity.prevPosZ != evt.entity.posZ){
				int meta = evt.entity.worldObj.getBlockMetadata((int)(evt.entity.posX), (int)(evt.entity.posY) - 1, (int)(evt.entity.posZ));
				if(meta == 15){
					return;
				}
				if(rand.nextInt(100) == 0 ){
					evt.entity.worldObj.setBlockMetadataWithNotify((int)(evt.entity.posX), (int)(evt.entity.posY) - 1, (int)(evt.entity.posZ), meta + 1, 0x02);				
				}
			}
		}
	}

}
