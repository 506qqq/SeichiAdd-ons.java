package mc.seichiaddons.skill.state;

import java.util.Objects;

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
    	if(Objects.isNull(this.skillState)) return false;
    	return skillState.skillMode == BuildSkill.Mode.OnUpper || skillState.skillMode == BuildSkill.Mode.OnLower;
    }

    public boolean isDisable() {
    	if(Objects.isNull(this)) return false;
    	return skillState.skillMode == BuildSkill.Mode.OnDisable;
    }

    public boolean isUpper() {
    	if(Objects.isNull(this)) return false;
    	return skillState.skillMode == BuildSkill.Mode.OnUpper;
    }

    public boolean isLower() {
    	if(Objects.isNull(this)) return false;
    	return skillState.skillMode == BuildSkill.Mode.OnLower;
    }
}
