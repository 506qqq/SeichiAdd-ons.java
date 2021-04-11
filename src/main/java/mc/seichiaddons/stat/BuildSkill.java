package mc.seichiaddons.stat;

import mc.seichiaddons.CONFIG;
import mc.seichiaddons.particle.ParticleDrawer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.world.World;

//建築スキル判定系
public class BuildSkill {
    private byte SKILLMODE;
    private byte DISABLESKILL = 0;
    private final byte UPPERSKILL = 1;
    private final byte LOWERSKILL = 2;

	public final byte DirSouth = 0;
    public final byte DirWest = 1;
    public final byte DirNorth = 2;
    public final byte DirEast = 3;

    private final Item stick;

    public BuildSkill() {
    	SKILLMODE = 0;
    	stick = Item.getItemById(CONFIG.stickItemID);
    }

    public void init() {
    	SKILLMODE = 0;
    }

    public byte getModes() {
    	return SKILLMODE;
    }

    public void setModeUpper() {
    	SKILLMODE = this.UPPERSKILL;
    }

    public void setModeLower() {
    	SKILLMODE = this.LOWERSKILL;
    }

    public void setModeDisable() {
    	SKILLMODE = this.DISABLESKILL;
    }

    public boolean isEnable() {
    	return SKILLMODE == UPPERSKILL || SKILLMODE == LOWERSKILL;
    }

    public boolean isDisable() {
    	return SKILLMODE == DISABLESKILL;
    }

    public boolean isUpper() {
    	return SKILLMODE == UPPERSKILL;
    }

    public boolean isLower() {
    	return SKILLMODE == LOWERSKILL;
    }

    public String getServerMsgOnDisable() {
    	return CONFIG.MSG.ONDISABLESKILL;
    }

    public String getServerMsgOnUpper() {
    	return CONFIG.MSG.ONUPPERSKILL;
    }

    public String getServerMsgOnLower() {
    	return CONFIG.MSG.ONLOWERSKILL;
    }

	public void drawParticle(ParticleDrawer particle, EntityPlayerSP p, World w) {
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
    		case DirSouth:
    			pZ+=2.0D;
    			break;
    		case DirWest:
    			pX-=2.0D;
    			break;
    		case DirNorth:
    			pZ-=2.0D;
    			break;
    		case DirEast:
    			pX+=2.0D;
    			break;
    		}
    	}
        if(this.isLower()) pY -= 1.0;
        particle.drawOneBlockGrid(pX, pY, pZ, w);
        return;
	}
}