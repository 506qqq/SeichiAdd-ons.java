package mc.seichiaddons.skill;

public class AssaultSkill extends SeichiSkill {
	public boolean isFluid;
	public AssaultSkill(String name, int X, int Y, int Z, boolean b) {
		super(name, X, Y, Z);
		this.isFluid = b;
	}
	
	public AssaultSkill() {
		super();
		this.isFluid = false;
	}

	@Override
	public int breakUP() {
		if(this.isFluid)  return breakRangeY / 2;
		else return breakRangeY - 2;
	}
	@Override
	public int breakDOWN() {
		if(this.isFluid) return breakRangeY / 2;
		else return 1;
	}
	@Override
	public int breakFRONT() {
		return breakRangeZ / 2;
	}
	@Override
	public int breakBACK() {
		return breakRangeZ / 2;
	}
}
