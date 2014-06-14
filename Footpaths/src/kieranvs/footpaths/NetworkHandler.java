package kieranvs.footpaths;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Sharable
public class NetworkHandler extends SimpleChannelInboundHandler<FMLProxyPacket> {
	
	int count = 0;
	long last = 0;
	long first = 0;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket packet) throws Exception {
		if(count == 0) first = System.currentTimeMillis();
		count++;
		if(count % 1000 == 0){
			double delta = System.currentTimeMillis() - last;
			last = System.currentTimeMillis();
			if(first != last && delta != 0) System.out.println(count + "packets received. approx " + (1000000D/delta) + "/sec. Avg:" + (count/(last-first)) + "/sec");			
		}
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.CLIENT) {
			onClientPacketData(packet);
		}
		else {
			onServerPacketData(packet);
		}
	}

	public void onServerPacketData(FMLProxyPacket packet) {
		if (packet.channel().equals("FootpathsGeneral")){
			int prevX = packet.payload().readInt();
			int x = packet.payload().readInt();
			int prevY = packet.payload().readInt();
			int y = packet.payload().readInt();
			int prevZ = packet.payload().readInt();
			int z = packet.payload().readInt();
			int l = packet.payload().readInt();
			byte[] name = new byte[l];
			packet.payload().readBytes(name);
			
			if(prevX != x || prevY != y || prevZ != z){
				ForgeListener.isPlayerMoving = true; 
			} else {
				ForgeListener.isPlayerMoving = false;
			}
		}

	}

	@SideOnly(Side.CLIENT)
	public void onClientPacketData(FMLProxyPacket packet){
	}
}
