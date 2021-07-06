package mc.seichiaddons.skill.state;

import java.util.ArrayList;
import java.util.List;

import mc.seichiaddons.resource.CONFIG;
import mc.seichiaddons.skill.AssaultSkill;
import mc.seichiaddons.skill.SeichiSkill;

//非アサルトスキル = stagnaSkill

public class SeichiSkillStateContainer{
	private static List<SeichiSkill> stagnaList;
	private static List<AssaultSkill> assaultList;

	public static SeichiSkill currentStagna;
	public static AssaultSkill currentAssault;

	static {
		stagnaList = new ArrayList<>();
		assaultList = new ArrayList<>();
		currentStagna = null;
		currentAssault = null;
		loadConfig();
	}

	public static void setModeDisable() {
		currentStagna = new SeichiSkill();
		currentAssault = new AssaultSkill();
		return;
	}

	public static void setSkillByChat(String chatMsg) {
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
		return;
	}

	private static void loadConfig() {
		String[] sName = CONFIG.stagnaSkillName;
		int[] sRangeXZ = CONFIG.stagnaSkillBreakRangeXZ;
		int[] sRangeY = CONFIG.stagnaSkillBreakRangeY;

		String[] aName = CONFIG.assaultSkillName;
		int[] aRangeXYZ = CONFIG.assaultSkillBreakRangeXYZ;
		boolean[] aIsClot = CONFIG.assaultIsClotSkill;

		for(int i = 0; i < sName.length; i++) {
			stagnaList.add(new SeichiSkill(sName[i], sRangeXZ[i], sRangeY[i]));
		}
		for(int i = 0; i < aName.length; i++) {
			assaultList.add(new AssaultSkill(aName[i], aRangeXYZ[i], aIsClot[i]));
		}
		return;
	}

	public static boolean isEnable() {
		//TODO: 自動生成
		return true;
	}
}