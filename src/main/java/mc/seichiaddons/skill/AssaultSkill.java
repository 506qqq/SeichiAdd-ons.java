package mc.seichiaddons.skill;

public class AssaultSkill extends SeichiSkill {
	public boolean isClotSkill;
	public AssaultSkill(String name, int XYZ, boolean b) {
		super(name, XYZ, XYZ);
		isClotSkill = b;
	}
	
	public AssaultSkill() {
		super();
	}
}
