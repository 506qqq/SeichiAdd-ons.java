package mc.seichiaddons.skill.render;

import net.minecraft.util.EnumFacing;

public class BuildSkillRenderStateContainer {
	private static boolean renderIsEnable;
	private static int Length;
	private static EnumFacing LookingAt;

	public static void cancelRender() {
		Length = 0;
	}

	public static void setState(EnumFacing ef, int len) {
		Length = len;
		LookingAt = ef;
		renderIsEnable = true;
	}

	public static boolean isRenderEnable() {
		return renderIsEnable;
	}

	public static int getLength() {
		return Length;
	}

	public static EnumFacing getLookingAt() {
		return LookingAt;
	}
}
