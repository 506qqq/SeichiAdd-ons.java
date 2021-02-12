package mc.saddons.stat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class Players {
	private static EntityPlayerSP player;

	public static EntityPlayerSP getStat() {
		return player;
	}

	public static void setStat(EntityPlayerSP player) {
		Players.player = player;
	}

    public static void reloadStat() {
    	player = Minecraft.getMinecraft().player;
    }
    
    public static void unloadStat() {
    	player = null;
    }
}