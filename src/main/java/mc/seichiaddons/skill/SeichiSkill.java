package mc.seichiaddons.skill;

public class SeichiSkill{
	private String skillName;
	protected int breakRangeX;
	protected int breakRangeY;
	protected int breakRangeZ;
	public SeichiSkill(String name, int X, int Y, int Z) {
		skillName = name;
		breakRangeX = X;
		breakRangeY = Y;
		breakRangeZ = Z;
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
	public int breakUP() {
		return breakRangeY - 2;
	}
	public int breakDOWN() {
		return 1;
	}
	public int breakRIGHT() {
		return breakRangeX / 2;
	}
	public int breakLEFT() {
		return breakRangeX / 2;
	}
	public int breakBACK() {
		return breakRangeZ;
	}
	public int breakFRONT() {
		return 0;
	}

}