package mc.seichiaddons.skill.particle;

import java.util.Objects;

import mc.seichiaddons.skill.render.AssaultSkillRenderStateContainer;
import mc.seichiaddons.skill.state.SeichiSkillStateContainer;
import net.minecraft.util.EnumParticleTypes;

//非アサルトスキル = stagnaSkill

public class SeichiSkillDrawer extends ParticleDrawer {

	@Override
	public void drawParticle(EnumParticleTypes pt) {
		drawAssault();
	}

	private void drawAssault() {
		if(Objects.isNull(SeichiSkillStateContainer.currentAssault)) {
			AssaultSkillRenderStateContainer.cancelRender();
			return;
		}
		int breakRange = SeichiSkillStateContainer.currentAssault.breakRangeXZ;
		boolean isClot = SeichiSkillStateContainer.currentAssault.isClotSkill;
		AssaultSkillRenderStateContainer.setState(breakRange, isClot);
	}

	private void drawStagna(EnumParticleTypes pt) {

	}
}