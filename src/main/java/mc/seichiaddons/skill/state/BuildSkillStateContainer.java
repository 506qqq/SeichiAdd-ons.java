package mc.seichiaddons.skill.state;

import java.util.Objects;

import mc.seichiaddons.skill.BuildSkill;
import mc.seichiaddons.skill.BuildSkill.Mode;

public class BuildSkillStateContainer {
	public static void setSkillByChat(String receivedMsg) {
		if(receivedMsg.equals(BuildSkill.getServerMsgOnDisable())) {
			BuildSkill.skillMode = Mode.OnDisable;
			return;
		}
		if(receivedMsg.equals(BuildSkill.getServerMsgOnUpper())) {
			BuildSkill.skillMode = Mode.OnUpper;
			return;
		}
		if(receivedMsg.equals(BuildSkill.getServerMsgOnLower())) {
			BuildSkill.skillMode = Mode.OnLower;
			return;
		}
	}

    public static void setModeDisable() {
    	BuildSkill.skillMode = BuildSkill.Mode.OnDisable;
    }

    public static boolean isEnable() {
    	if(Objects.isNull(BuildSkill.skillMode)) return false;
    	return BuildSkill.skillMode == BuildSkill.Mode.OnUpper ||
    			BuildSkill.skillMode == BuildSkill.Mode.OnLower;
    }

    public static boolean isDisable() {
    	boolean res = BuildSkill.getMode() == BuildSkill.Mode.OnDisable;
    	return res;
    }

    public static boolean isUpper() {
    	return BuildSkill.getMode() == BuildSkill.Mode.OnUpper;
    }

    public static boolean isLower() {
    	return BuildSkill.getMode() == BuildSkill.Mode.OnLower;
    }
}
