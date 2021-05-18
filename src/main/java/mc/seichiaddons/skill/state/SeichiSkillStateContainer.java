package mc.seichiaddons.skill.state;

import java.util.List;

import mc.seichiaddons.resource.CONFIG;
import mc.seichiaddons.skill.AssaultSkill;
import mc.seichiaddons.skill.SeichiSkill;

//非アサルトスキル = stagnaSkill

public class SeichiSkillStateContainer {
	private List<SeichiSkill> stagnaList;
	private List<AssaultSkill> assaultList;

	public SeichiSkill currentStagna;
	public AssaultSkill currentAssault;

	void SeichiSKillStateContainer() {
		this.loadConfig();
	}

	public void setSkillByChatMsg(String chatMsg) {
		for(SeichiSkill s: stagnaList) {
			if(s.chatMsgOnEnable().equals(chatMsg)) {
				currentStagna = s;
			}
			if(s.chatMsgOnDisable().equals(chatMsg)) {
				currentAssault = null;
			}
		}
		for(AssaultSkill a: assaultList) {
			if(a.chatMsgOnEnable().equals(chatMsg)) {
				currentAssault = a;
			}
			if(a.chatMsgOnDisable().equals(chatMsg)) {
				currentAssault = null;
			}
		}
	}

	private void loadConfig() {
		String[] sName = CONFIG.seichiSkillName;
		int[] sRangeX = CONFIG.seichiSkillBreakRangeX;
		int[] sRangeY = CONFIG.seichiSkillBreakRangeY;
		int[] sRangeZ = CONFIG.seichiSkillBreakRangeZ;
		int[] s_isAssault = CONFIG.seichiSkill_IsAssault;
		int[] s_isFluid = CONFIG.seichiSkill_IsFluid;
		for(int i = 0; i < sName.length; i++) {
			if(s_isAssault[i] != 1) stagnaList.add(new SeichiSkill(
					sName[i], sRangeX[i], sRangeY[i], sRangeZ[i]));
			else assaultList.add(new AssaultSkill(
					sName[i], sRangeX[i], sRangeY[i], sRangeZ[i],
					(s_isFluid[i] == 1)?true:false));
		}

	}
}