package castledecorations.block;

import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFountain extends TileEntity {
	
	private Random rand = new Random();
	
	@Override
	public void updateEntity(){
		if(worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)){
			if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
				if(worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord) == 9){
					int num = rand.nextInt(200);
					for(int i = 0; i < num; i++){
						float particlex = (float)this.xCoord + rand.nextFloat();
						float particley = (float)this.yCoord + 1 + rand.nextFloat() * worldObj.getBlockPowerInput(this.xCoord, this.yCoord, this.zCoord);
						float particlez = (float)this.zCoord + rand.nextFloat();
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySplashFX(Minecraft.getMinecraft().theWorld, particlex, particley, particlez, 0, 1, 0));
					}
				}
			}
		}
	}
	
	@Override
	public boolean canUpdate(){
		return true;
	}

}
