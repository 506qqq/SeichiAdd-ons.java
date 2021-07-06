package mc.seichiaddons.skill.particle;


import mc.seichiaddons.resource.CONFIG;
import mc.seichiaddons.skill.render.BuildSkillRenderStateContainer;
import mc.seichiaddons.skill.state.BuildSkillStateContainer;
import mc.seichiaddons.util.FacingUtil;
import mc.seichiaddons.util.PlayerUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BuildSkillDrawer extends ParticleDrawer {
	public BuildSkillStateContainer skillState;
	private EntityPlayerSP player;

	private final Item stick;

	public BuildSkillDrawer() {
		stick = Item.getItemById(CONFIG.stickItemID);
	}
	public void drawParticle(EnumParticleTypes pt) {
		if (BuildSkillStateContainer.isDisable()) {
			BuildSkillRenderStateContainer.cancelRender();
			return;
		}
		player = Minecraft.getMinecraft().player;
		if (!player.getHeldItemOffhand().getItem().equals(stick)) {
			BuildSkillRenderStateContainer.cancelRender();
			return;
		}
		if(CONFIG.drawBuildSkillByRender) {
			this.drawRender();
			return;
		}
		EnumFacing ef = calcFacing();
		BlockPos bp = calcStartCoordinate(ef);
        this.drawOneBlockGrid(pt, bp.getX(), bp.getY(), bp.getZ());
        return;
	}

	private void drawRender() {
		EnumFacing ef = calcFacing();
		BlockPos bp = calcStartCoordinate(ef);
		int length = calcLength(ef, bp);
		BuildSkillRenderStateContainer.setState(ef, length);
	}
	
	private BlockPos calcStartCoordinate(EnumFacing dir) {
		BlockPos bp = PlayerUtil.getPlayerBlockPos();
    	bp.offset(dir);
    	if(dir.equals(EnumFacing.UP)) bp.offset(EnumFacing.UP);
        if(BuildSkillStateContainer.isLower()) bp.offset(EnumFacing.DOWN);
        return bp;
	}

	private EnumFacing calcFacing() {
    	byte Yaw = (byte)((int)(Math.floor((player.rotationYaw / 90.0D) + 0.5D)) & 3);
    	float pitch = player.rotationPitch;
    	if(pitch > 45.0f) {
    		return EnumFacing.DOWN;
    	}
    	else if(pitch < -45.0f) {
    		return EnumFacing.UP;
    	}
    	else {
    		switch(Yaw) {//横向き
    		case 0:
    			return EnumFacing.SOUTH;
    		case 1:
    			return EnumFacing.WEST;
    		case 2:
    			return EnumFacing.NORTH;
    		case 3:
    			return EnumFacing.EAST;
    		}
    	}
    	//到達不可能
		return null;
	}

	
	//プレイヤーの目の前のブロックから壁にブチあたるまでのブロックの個数
	private int calcLength(EnumFacing dir, BlockPos bp) {
		World world = Minecraft.getMinecraft().world;
		BlockPos pos = bp;
		if(FacingUtil.isHorizontal(dir)) {
			if(BuildSkillStateContainer.isLower()) {
				pos = pos.offset(EnumFacing.DOWN);
			}
		}
		if(dir == EnumFacing.DOWN) {
			pos = pos.offset(EnumFacing.DOWN);
		}
		IBlockState ibs;
		int res;
		for(res = 0; res < 15; res++) {
			pos = pos.offset(dir);
			ibs = world.getBlockState(pos);
			if(!ibs.getBlock().isAir(ibs, world, pos)) {
				break;
			}
		}
		return res;
	}

}
