package mc.seichiaddons.event;

import org.lwjgl.opengl.GL11;

import mc.seichiaddons.skill.render.AssaultSkillRenderStateContainer;
import mc.seichiaddons.skill.render.BuildSkillRenderStateContainer;
import mc.seichiaddons.skill.state.BuildSkillStateContainer;
import mc.seichiaddons.util.FacingUtil;
import mc.seichiaddons.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class eventOnRenderWorld {
	@SubscribeEvent
	public static void onRenderWorld(RenderWorldLastEvent e) {
		renderWorld();
		return;
	}

    private static void renderWorld() {
    	BufferBuilder bb = Tessellator.getInstance().getBuffer();
    	BlockPos bp = PlayerUtil.getPlayerBlockPos();
    	double pX = Minecraft.getMinecraft().getRenderManager().viewerPosX;
        double pY = Minecraft.getMinecraft().getRenderManager().viewerPosY;
        double pZ = Minecraft.getMinecraft().getRenderManager().viewerPosZ;

        glbegin();
        GlStateManager.translate(bp.getX() - pX, bp.getY() - pY, bp.getZ() - pZ);
        bb.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        if(BuildSkillRenderStateContainer.isRenderEnable())
        	renderBuildSkill(bb);
        if(AssaultSkillRenderStateContainer.isRenderEnable())
        	renderAssaultSkill(bb);
    	Tessellator.getInstance().draw();
    	glend();
    	return;
    }

    private static void renderBuildSkill(BufferBuilder bb) {
		int loop = BuildSkillRenderStateContainer.getLength();
		EnumFacing la = BuildSkillRenderStateContainer.getLookingAt();
    	int x = 0, y = 0, z = 0;
    	if(FacingUtil.isHorizontal(la)) {
    		//スキルモード: 下側の場合
    		if(BuildSkillStateContainer.isLower()) y--;
    	}
    	if(la == EnumFacing.DOWN) {
    		//下向きに発動する場合
    		y--;
    	}
    	for(int i = 0; i < loop; i++) {
    		switch(la) {
    			case SOUTH	:z++;	break;
    			case NORTH	:z--;	break;
    			case EAST	:x++;	break;
    			case WEST	:x--;	break;
    			case UP		:y++;	break;
    			case DOWN	:y--;	break;
    		}
    		selectCuboid(new Vec3d(x, y, z), new Vec3d(x+1, y+1, z+1), bb, 255, 255, 255);
    	}
    }

    private static void renderAssaultSkill(BufferBuilder bb) {
    	Vec3d min = AssaultSkillRenderStateContainer.getMinPos();
    	Vec3d max = AssaultSkillRenderStateContainer.getMaxPos();
    	selectCuboid(min, max, bb, 255, 0, 0);
    	return;
    }
    
    private static void glbegin() {
    	GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.pushMatrix();
        GL11.glLineWidth(4F);
        return;
    }

    private static void glend() {
    	GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glPopAttrib();
        GlStateManager.popMatrix();
        return;
    }

    private static void selectCuboid(Vec3d min, Vec3d max, BufferBuilder bb, int R, int G, int B) {
        bb.pos(min.x, min.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(min.x, max.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(min.x, min.y, max.z).color(R, G, B, 200).endVertex();
        bb.pos(min.x, max.y, max.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, min.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, max.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, min.y, max.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, max.y, max.z).color(R, G, B, 200).endVertex();

        bb.pos(min.x, min.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(min.x, min.y, max.z).color(R, G, B, 200).endVertex();
        bb.pos(min.x, max.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(min.x, max.y, max.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, min.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, min.y, max.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, max.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, max.y, max.z).color(R, G, B, 200).endVertex();

        bb.pos(min.x, min.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, min.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(min.x, min.y, max.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, min.y, max.z).color(R, G, B, 200).endVertex();
        bb.pos(min.x, max.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, max.y, min.z).color(R, G, B, 200).endVertex();
        bb.pos(min.x, max.y, max.z).color(R, G, B, 200).endVertex();
        bb.pos(max.x, max.y, max.z).color(R, G, B, 200).endVertex();
        return;
    }

    /*
        private static void renderBlock(BlockPos pos, float thickness) {
        double renderPosX = Minecraft.getMinecraft().getRenderManager().viewerPosX;
        double renderPosY = Minecraft.getMinecraft().getRenderManager().viewerPosY;
        double renderPosZ = Minecraft.getMinecraft().getRenderManager().viewerPosZ;

        GlStateManager.translate(pos.getX() - renderPosX, pos.getY() - renderPosY, pos.getZ() - renderPosZ);
        GL11.glColor4ub(FF, FF, FF,(byte) 255);

        World world = Minecraft.getMinecraft().world;
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        glbegin();
        drawWireframe : {
            if(block != null) {
                AxisAlignedBB axis;
                axis = state.getSelectedBoundingBox(world, pos);
                if(axis == null)
                    break drawWireframe;
                axis = axis.offset(-pos.getX(), -pos.getY(), -pos.getZ());
                GL11.glLineWidth(thickness);
            }
        }
        glend();
    }

    private static void selectCuboid(AxisAlignedBB aabb, BufferBuilder bb) {
    	Vec3d min = new Vec3d(aabb.minX, aabb.minY, aabb.minZ);
    	Vec3d max = new Vec3d(aabb.maxX, aabb.maxY, aabb.maxZ);
    	selectCuboid(min, max, bb);
    }

    private static void drawOneBlock(BufferBuilder bb) {
    	selectCuboid(new Vec3d(0, 0, 0), new Vec3d(1, 1, 1), bb);
    }
    */
}
