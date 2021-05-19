package mc.seichiaddons.event;

import java.util.Objects;

import mc.seichiaddons.SeichiAddons;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

@EventBusSubscriber
public class eventOnTicked {
	
    private static int particleDrawCycle;
    private static byte tickCount;
	
    @SubscribeEvent
    public static void onTicked(ClientTickEvent event) {
    	if(tickCount <= particleDrawCycle) {
    		tickCount++;
    		return;
    	}
    	if(SeichiAddons.buildSkill.skillState.isEnable()) {
    		SeichiAddons.resetStat();
           	if(Objects.isNull(SeichiAddons.world)) {
           		SeichiAddons.buildSkill.skillState.setModeDisable();
           		SeichiAddons.seichiSkill.skillState.setModeDisable();
           		return;
           	}
           	if(Objects.isNull(SeichiAddons.player)) {
           		return;
           	}
           	SeichiAddons.buildSkill.drawParticle(EnumParticleTypes.REDSTONE);
           	SeichiAddons.seichiSkill.drawParticle(EnumParticleTypes.DRAGON_BREATH);
    	}
    }
}
