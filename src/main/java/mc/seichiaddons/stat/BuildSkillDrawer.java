package mc.seichiaddons.stat;

import mc.seichiaddons.CONFIG;
import mc.seichiaddons.SeichiAddons;
import mc.seichiaddons.particle.ParticleInterface;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BuildSkillDrawer extends BuildSkill implements ParticleInterface {

	private final Item stick;
	private final EnumParticleTypes particleType;
	
	public BuildSkillDrawer() {
		super();
		stick = Item.getItemById(CONFIG.stickItemID);
		particleType = EnumParticleTypes.REDSTONE;
	}

	@Override
	public void drawParticle(EntityPlayerSP p, World w) {
    	EntityPlayerSP player = p;
    	World world = w;
    	double pX = ((Math.round((player.posX)-0.5D))+0.5D);
    	double pY = Math.floor(player.posY+1.6D)+0.5D;
    	double pZ = ((Math.round((player.posZ)-0.5D))+0.5D);
    	if (!player.getHeldItemOffhand().getItem().equals(stick)) {
    		return;
    	}
    	byte Yaw = (byte)((int)(Math.floor((player.rotationYaw / 90.0D) + 0.5D)) & 3);
    	float pitch = player.rotationPitch;
    	if(pitch > 45.0f) {
    		pY -= 2;
    	}
    	else if(pitch < -45.0f) {
    		pX += 2;
    	}
    	else {
    		switch(Yaw) {
    		case SeichiAddons.DirSouth:
    			pZ+=2.0D;
    			break;
    		case SeichiAddons.DirWest:
    			pX-=2.0D;
    			break;
    		case SeichiAddons.DirNorth:
    			pZ-=2.0D;
    			break;
    		case SeichiAddons.DirEast:
    			pX+=2.0D;
    			break;
    		}
    	}
        if(this.isLower()) pY -= 1.0;
        this.drawOneBlockGrid(pX, pY, pZ, world);
        return;
	}
	
	void drawUnitParticle(World w, double pX, double pY, double pZ) {
		w.spawnParticle(particleType, pX, pY, pZ, 0, 0, 0);
	}
	
	public void drawOneBlockGrid(double x, double y, double z, World w) {
       	for(int bit = 0; (bit&(1<<3)) == 0; bit++) {
       		drawUnitParticle(w, (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
      			(bit&(1<<1)) == 0? y-0.5D: y+0.5D,
      			(bit&(1<<2)) == 0? z-0.5D: z+0.5D);
       	}
        for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
        	drawUnitParticle(w, (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
        		(bit&(1<<1)) == 0? y-0.5D: y+0.5D,
        		z);
        }
        for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
        	drawUnitParticle(w, (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
        		y,
        		(bit&(1<<1)) == 0? z-0.5D: z+0.5D);
        }
        for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
        	drawUnitParticle(w, x,
        		(bit&(1<<0)) == 0? y-0.5D: y+0.5D,
        		(bit&(1<<1)) == 0? z-0.5D: z+0.5D);
        }
	}
}
