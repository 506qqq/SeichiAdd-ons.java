package mc.seichiaddons.skill.particle;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;


abstract class ParticleDrawer {
	protected EntityPlayerSP p;
	protected World w;
	
	ParticleDrawer(EntityPlayerSP player, World world) {
		this.p = player;
		this.w = world;
	}
	
	protected enum Axis {
		X, Y, Z
	}
	
	abstract void drawParticle(EnumParticleTypes pt);
	
	protected void drawUnitParticle(EnumParticleTypes pt, double x, double y, double z) {
	   	w.spawnParticle(pt, x, y, z, 0, 0, 0);
	}
	
	protected void drawLineParticle(EnumParticleTypes pt, int X, int Y, int Z, int W, Axis E) {
		double v;
		W += 1;
		switch(E) {
			case X:
				v = X;
				while(v < (double)W) {
					drawUnitParticle(pt, v, Y, Z);
					v += 0.5;
				}
				break;
			case Y:
				v = Y;
				while(v < (double)W) {
					drawUnitParticle(pt, X, v, Z);
					v += 0.5;
				}
				break;
			case Z:
				v = Z;
				while(v < (double)W) {
					drawUnitParticle(pt, X, Y, v);
					v += 0.5;
				}
				break;
		}
	}
	
	public void drawCubeParticle(EnumParticleTypes pt,
			int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
		maxX++; maxY++; maxZ++;
		//X方向に描画
		drawLineParticle(pt, minX, minY, minZ, maxX, Axis.X);
		drawLineParticle(pt, minX, maxY, maxZ, maxX, Axis.X);
		drawLineParticle(pt, minX, minY, maxZ, maxX, Axis.X);
		drawLineParticle(pt, minX, maxY, maxZ, maxX, Axis.X);
		
		//Y方向に描画
		drawLineParticle(pt, minX, minY, minZ, maxY, Axis.Y);
		drawLineParticle(pt, maxX, minY, maxZ, maxY, Axis.Y);
		drawLineParticle(pt, minX, minY, maxZ, maxY, Axis.Y);
		drawLineParticle(pt, maxX, minY, minZ, maxY, Axis.Y);
		
		//Z方向に描画
		drawLineParticle(pt, minX, minY, minZ, maxZ, Axis.Z);
		drawLineParticle(pt, maxX, maxY, minZ, maxZ, Axis.Z);
		drawLineParticle(pt, minX, maxY, minZ, maxZ, Axis.Z);
		drawLineParticle(pt, maxX, minY, minZ, maxZ, Axis.Z);
	}
	
	public void drawOneBlockGrid(EnumParticleTypes pt,double x, double y, double z) {
       	for(int bit = 0; (bit&(1<<3)) == 0; bit++) {
       		drawUnitParticle(pt, (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
      			(bit&(1<<1)) == 0? y-0.5D: y+0.5D,
      			(bit&(1<<2)) == 0? z-0.5D: z+0.5D);
       	}
        for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
        	drawUnitParticle(pt, (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
        		(bit&(1<<1)) == 0? y-0.5D: y+0.5D,
        		z);
        }
        for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
        	drawUnitParticle(pt, (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
        		y,
        		(bit&(1<<1)) == 0? z-0.5D: z+0.5D);
        }
        for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
        	drawUnitParticle(pt, x,
        		(bit&(1<<0)) == 0? y-0.5D: y+0.5D,
        		(bit&(1<<1)) == 0? z-0.5D: z+0.5D);
        }
	}
}
