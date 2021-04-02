package mc.seichiaddons.stat;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class Worlds {
	static World world;

    public static World getStat() {
    	return world;
    }

    public static void reloadStat() {
    	world = Minecraft.getMinecraft().world;
    }
    
    public static void unloadStat() {
    	world = null;
    }

}