package mc.seichiaddons.stat;

import mc.seichiaddons.CONFIG;

//建築スキル判定系
public class BuildSkill {
    private byte SKILLMODE;
    private byte DISABLESKILL = 0;
    private final byte UPPERSKILL = 1;
    private final byte LOWERSKILL = 2;

    public BuildSkill() {
    	SKILLMODE = 0;

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
}