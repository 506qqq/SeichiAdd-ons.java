package mc.seichiaddons.particle;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public interface ParticleInterface {
	void drawParticle(EntityPlayerSP player, World world);
    default void drawBlockBorder(World w, EnumParticleTypes p, double x, double y, double z) {
    	w.spawnParticle(p, x, y, z, 0, 0, 0);
    }
}
