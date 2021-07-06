package mc.seichiaddons.skill.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;


abstract class ParticleDrawer {
	
	protected enum Axis {
		X, Y, Z
	}
	
	abstract void drawParticle(EnumParticleTypes pt);
	
	protected void drawUnitParticle(EnumParticleTypes pt, double x, double y, double z) {
	   	Minecraft.getMinecraft().world.spawnParticle(pt, x, y, z, 0, 0, 0);
	}
	
	protected void drawLineParticle(EnumParticleTypes pt, int X, int Y, int Z, int W, Axis E) {
		double v;
		W += 1;
		switch(E) {
			case X:
				v = X;
				while(v < (double)W) {
					drawUnitParticle(pt, v, Y, Z);
					v += 1.0;
				}
				break;
			case Y:
				v = Y;
				while(v < (double)W) {
					drawUnitParticle(pt, X, v, Z);
					v += 1.0;
				}
				break;
			case Z:
				v = Z;
				while(v < (double)W) {
					drawUnitParticle(pt, X, Y, v);
					v += 1.0;
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
		drawLineParticle(pt, minX, maxY, minZ, maxX, Axis.X);
		
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
	
	public void drawOneBlockGrid(EnumParticleTypes pt, double x, double y, double z) {
		for(int i = 0; i < 3; i++) {
			drawUnitParticle(pt, x, y, z);
			drawUnitParticle(pt, x, y, z+1.0);
			drawUnitParticle(pt, x, y+1.0, z);
			drawUnitParticle(pt, x, y+1.0, z+1.0);
			if(i != 1) {//2段目: 中点は表示しない
				drawUnitParticle(pt, x, y, z+0.5);
				drawUnitParticle(pt, x, y+0.5, z);
				drawUnitParticle(pt, x, y+0.5, z+1.0);
				drawUnitParticle(pt, x, y+1.0, z+0.5);
			}
			x += 0.5;
		}
	}
	
	public void drawOneBlockGrid(EnumParticleTypes pt, int xi, int yi, int zi) {
		drawOneBlockGrid(pt, xi, yi, zi);
	}
	
}
