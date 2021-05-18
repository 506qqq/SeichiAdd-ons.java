package mc.seichiaddons.skill.particle;

import mc.seichiaddons.resource.CONFIG;
import mc.seichiaddons.skill.state.BuildSkillStateContainer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BuildSkillDrawer extends ParticleDrawer {
	public BuildSkillStateContainer skillState;

	private final Item stick;
	private final EnumParticleTypes particleType;

	public BuildSkillDrawer(EntityPlayerSP p, World w) {
		super(p, w);
		this.skillState = new BuildSkillStateContainer();
		stick = Item.getItemById(CONFIG.stickItemID);
		particleType = EnumParticleTypes.REDSTONE;
	}

	public void drawParticle(EnumParticleTypes pt) {
    	double pX = ((Math.round((p.posX)-0.5D))+0.5D);
    	double pY = Math.floor(p.posY+1.6D)+0.5D;
    	double pZ = ((Math.round((p.posZ)-0.5D))+0.5D);
    	if (!p.getHeldItemOffhand().getItem().equals(stick)) {
    		return;
    	}
    	byte Yaw = (byte)((int)(Math.floor((p.rotationYaw / 90.0D) + 0.5D)) & 3);

    	float pitch = p.rotationPitch;
    	if(pitch > 45.0f) {
    		pY -= 2;
    	}
    	else if(pitch < -45.0f) {
    		pX += 2;
    	}
    	else {
    		switch(Yaw) {
    		case 0:
    			pZ+=2.0D;
    			break;
    		case 1:
    			pX-=2.0D;
    			break;
    		case 2:
    			pZ-=2.0D;
    			break;
    		case 3:
    			pX+=2.0D;
    			break;
    		}
    	}
        if(this.skillState.isLower()) pY -= 1.0;
        this.drawOneBlockGrid(particleType, pX, pY, pZ);
        return;
	}

	void drawUnitParticle(World w, double pX, double pY, double pZ) {
		w.spawnParticle(particleType, pX, pY, pZ, 0, 0, 0);
	}


}
