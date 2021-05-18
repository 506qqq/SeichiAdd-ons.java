package mc.seichiaddons.skill.particle;

import mc.seichiaddons.skill.state.SeichiSkillStateContainer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

//非アサルトスキル = stagnaSkill

public class SeichiSkillDrawer extends ParticleDrawer {
	SeichiSkillStateContainer skillState;

	SeichiSkillDrawer(EntityPlayerSP p, World w) {
		super(p, w);
	}

	@Override
	public void drawParticle(EnumParticleTypes pt) {
		if(skillState.currentStagna.isNotNull()) {
			drawStagna(pt);
		}
		if(skillState.currentAssault.isNotNull()) {
			drawAssault(pt);
		}
	}

	private void drawAssault(EnumParticleTypes pt) {
		int minX, minY, minZ, maxX, maxY, maxZ;

	}

	private void drawStagna(EnumParticleTypes pt) {

	}
}
