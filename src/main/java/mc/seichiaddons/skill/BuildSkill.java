package mc.seichiaddons.skill;

import mc.seichiaddons.resource.CONFIG;

//建築スキル判定系
public class BuildSkill {
	public static enum Mode{
		OnUpper, OnLower, OnDisable
	};
    public Mode skillMode;

    public static String getServerMsgOnDisable() {
    	return CONFIG.MSG.ONDISABLESKILL;
    }

    public static String getServerMsgOnUpper() {
    	return CONFIG.MSG.ONUPPERSKILL;
    }

    public static String getServerMsgOnLower() {
    	return CONFIG.MSG.ONLOWERSKILL;
    }
}

