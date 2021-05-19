package mc.seichiaddons.skill.particle;

import mc.seichiaddons.skill.state.SeichiSkillStateContainer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

//非アサルトスキル = stagnaSkill

public class SeichiSkillDrawer extends ParticleDrawer {
	public SeichiSkillStateContainer skillState;

	public SeichiSkillDrawer(EntityPlayerSP p, World w) {
		super(p, w);
		this.skillState = new SeichiSkillStateContainer();
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
		minX = (int)p.posX - skillState.currentAssault.breakLEFT();
		maxX = (int)p.posY + skillState.currentAssault.breakRIGHT();
		minY = (int)(p.posY-1.0D) - skillState.currentAssault.breakDOWN();
		maxY = (int)(p.posY-1.0D) - skillState.currentAssault.breakUP();
		minZ = (int)p.posZ - skillState.currentAssault.breakFRONT();
		maxZ = (int)p.posZ + skillState.currentAssault.breakBACK();
		drawCubeParticle(pt, minX, minY, minZ, maxX, maxY, maxZ);
	}

	private void drawStagna(EnumParticleTypes pt) {
		
	}
}
