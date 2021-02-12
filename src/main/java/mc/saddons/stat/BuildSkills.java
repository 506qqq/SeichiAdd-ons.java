package mc.saddons.stat;

//建築スキル判定系
public class BuildSkills {
    private static byte SKILLMODE;
    private static final byte DISABLESKILL = 0;
    private static final byte UPPERSKILL = 1;
    private static final byte LOWERSKILL = 2;

    public BuildSkills() {
    	SKILLMODE = 0;
    }

    public void init() {
    	SKILLMODE = 0;
    }

    public static byte getModes() {
    	return SKILLMODE;
    }

    public static void setModeUpper() {
    	SKILLMODE = BuildSkills.UPPERSKILL;
    }

    public static void setModeLower() {
    	SKILLMODE = BuildSkills.LOWERSKILL;
    }

    public static void setModeDisable() {
    	SKILLMODE = BuildSkills.DISABLESKILL;
    }

    public static boolean isEnable() {
    	return SKILLMODE == UPPERSKILL || SKILLMODE == LOWERSKILL;
    }

    public static boolean isDisable() {
    	return SKILLMODE == DISABLESKILL;
    }

    public static boolean isUpper() {
    	return SKILLMODE == UPPERSKILL;
    }

    public static boolean isLower() {
    	return SKILLMODE == LOWERSKILL;
    }
}