package mc.seichiaddons.util;

import net.minecraft.util.EnumFacing;

public class FacingUtil {
	public static boolean isHorizontal(EnumFacing ef) {
		return ef != EnumFacing.UP && ef != EnumFacing.DOWN;
	}
}
