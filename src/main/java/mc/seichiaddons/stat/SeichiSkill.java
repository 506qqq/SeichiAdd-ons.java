package mc.seichiaddons.stat;

public class SeichiSkill{
	String skillName;
	int breakRangeX;
	int breakRangeY;
	int breakRangeZ;
	boolean isAssault;
	SeichiSkill(String name, int X, int Y, int Z, boolean b) {
		skillName = name;
		breakRangeX = X;
		breakRangeY = Y;
		breakRangeZ = Z;
		isAssault = b;
	}
	String chatMsgOnEnable() {
		return this.skillName + ": ON";
	}
	String chatMsgOnDisable() {
		return this.skillName + ": OFF";
	}

}