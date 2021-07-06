package mc.seichiaddons.event;

import java.util.Objects;

import mc.seichiaddons.SeichiAddons;
import mc.seichiaddons.skill.render.AssaultSkillRenderStateContainer;
import mc.seichiaddons.skill.render.BuildSkillRenderStateContainer;
import mc.seichiaddons.skill.state.BuildSkillStateContainer;
import mc.seichiaddons.skill.state.SeichiSkillStateContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

@EventBusSubscriber
public class eventOnTicked {
	private static int particleDrawCycle = 16;
    private static byte tickCount;
    
    @SubscribeEvent
    public static void onTicked(ClientTickEvent event) {
    	
    	if(tickCount <= particleDrawCycle) {
    		tickCount++;
    		return;
    	}
    		tickCount = 0;
           	if(Objects.isNull(Minecraft.getMinecraft().world)) {
           		if(BuildSkillStateContainer.isEnable())
           			BuildSkillStateContainer.setModeDisable();
           		if(SeichiSkillStateContainer.isEnable())
           			SeichiSkillStateContainer.setModeDisable();
           		if(BuildSkillRenderStateContainer.isRenderEnable())
           			BuildSkillRenderStateContainer.cancelRender();
           		if(AssaultSkillRenderStateContainer.isRenderEnable())
           			AssaultSkillRenderStateContainer.cancelRender();
           		return;
           	}
           	if(Objects.isNull(Minecraft.getMinecraft().player)) {
           		return;
           	}
           	SeichiAddons.buildSkill.drawParticle(EnumParticleTypes.END_ROD);
           	SeichiAddons.seichiSkill.drawParticle(EnumParticleTypes.REDSTONE);
    }
}
