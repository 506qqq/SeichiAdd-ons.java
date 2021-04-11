package mc.seichiaddons.particle;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ParticleDrawer{
	World world;
	public void drawOneBlockGrid(double x, double y, double z, World w) {
		this.world = w;
       	for(int bit = 0; (bit&(1<<3)) == 0; bit++) {
       		showRSdust( (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
      			(bit&(1<<1)) == 0? y-0.5D: y+0.5D,
      			(bit&(1<<2)) == 0? z-0.5D: z+0.5D);
       	}
        for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
        	showRSdust( (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
        		(bit&(1<<1)) == 0? y-0.5D: y+0.5D,
        		z);
        }
        for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
        	showRSdust( (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
        		y,
        		(bit&(1<<1)) == 0? z-0.5D: z+0.5D);
        }
        for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
        	showRSdust(x,
        		(bit&(1<<0)) == 0? y-0.5D: y+0.5D,
        		(bit&(1<<1)) == 0? z-0.5D: z+0.5D);
        }
	}

    private void showRSdust(double x, double y, double z) {
    	world.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z, 0, 0, 0);
    }

}
