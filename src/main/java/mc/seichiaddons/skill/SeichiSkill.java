package mc.seichiaddons.skill;

public class SeichiSkill{
	private String skillName;
	public int breakRangeXZ;
	protected int breakRangeY;
	
	public SeichiSkill(String name, int XZ, int Y) {
		skillName = name;
		breakRangeXZ = XZ;
		breakRangeY = Y;
		
	}
	
	public SeichiSkill() {
		skillName = null;
		breakRangeXZ = 0;
		breakRangeY = 0;
	}
	public boolean isNotNull() {
		return this != null;
	}
	public String chatMsgOnEnable() {
		return this.skillName + ": ON";
	}
	public String chatMsgOnDisable() {
		return this.skillName + ": OFF";
	}

}