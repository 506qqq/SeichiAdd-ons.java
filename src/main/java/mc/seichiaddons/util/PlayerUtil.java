package mc.seichiaddons.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;

public class PlayerUtil {
	public static BlockPos getPlayerBlockPos() {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		int pX = (int)(Math.round((player.posX)-0.5D));
    	int pY = (int)Math.floor(player.posY+1.6D);
    	int pZ = (int)(Math.round((player.posZ)-0.5D));
        return new BlockPos(pX, pY, pZ);
	}
}
