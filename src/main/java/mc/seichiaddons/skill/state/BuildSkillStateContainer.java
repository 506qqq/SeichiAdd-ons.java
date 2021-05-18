package mc.seichiaddons.skill.state;

import mc.seichiaddons.skill.BuildSkill;

public class BuildSkillStateContainer{
	private BuildSkill skillState;

    public void setModeUpper() {
    	skillState.skillMode = BuildSkill.Mode.OnUpper;
    }

    public void setModeLower() {
    	skillState.skillMode = BuildSkill.Mode.OnLower;
    }

    public void setModeDisable() {
    	skillState.skillMode = BuildSkill.Mode.OnDisable;
    }

    public boolean isEnable() {
    	return skillState.skillMode == BuildSkill.Mode.OnUpper || skillState.skillMode == BuildSkill.Mode.OnLower;
    }

    public boolean isDisable() {
    	return skillState.skillMode == BuildSkill.Mode.OnDisable;
    }

    public boolean isUpper() {
    	return skillState.skillMode == BuildSkill.Mode.OnUpper;
    }

    public boolean isLower() {
    	return skillState.skillMode == BuildSkill.Mode.OnLower;
    }
}
