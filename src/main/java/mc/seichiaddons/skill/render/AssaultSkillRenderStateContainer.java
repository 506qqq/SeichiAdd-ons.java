package mc.seichiaddons.skill.render;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;

public class AssaultSkillRenderStateContainer {
		private static boolean renderIsEnable;
		private static int lengthXYZ;
		//凝固系ならTrue
		private static boolean isClotSkill;

		public static void cancelRender() {
			lengthXYZ = 0;
			renderIsEnable = false;
		}

		public static void setState(int len, boolean cs) {
			lengthXYZ = len;
			isClotSkill = cs;
			renderIsEnable = true;
		}

		public static boolean isRenderEnable() {
			return renderIsEnable;
		}

		private static int getHorizLength() {
			return lengthXYZ / 2;
		}

		private static int getUpLength() {
			if(isClotSkill) return lengthXYZ / 2;
			else return lengthXYZ - 2;
		}

		private static int getDownLength() {
			int res;
			if(isClotSkill) res = lengthXYZ / 2;
			else res = 1;
			//スニーク処理
			if(Minecraft.getMinecraft().player.isSneaking()) res++;
			return res;
		}

		public static Vec3d getMinPos() {
			return new Vec3d(-getHorizLength(), -getDownLength(), -getHorizLength());
		}

		public static Vec3d getMaxPos() {
			return new Vec3d(getHorizLength() + 1, getUpLength(), getHorizLength() + 1);
		}
}
