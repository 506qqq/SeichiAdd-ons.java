package mc.seichiaddons;

import java.util.Objects;

import mc.seichiaddons.command.LinkCommand;
import mc.seichiaddons.resource.CONFIG;
import mc.seichiaddons.skill.BuildSkill;
import mc.seichiaddons.skill.particle.BuildSkillDrawer;
import mc.seichiaddons.skill.particle.SeichiSkillDrawer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

@Mod(modid = SeichiAddons.MODID, name = SeichiAddons.NAME, version = SeichiAddons.VERSION)
@EventBusSubscriber
public class SeichiAddons
{
    public static final String MODID = "seichiaddons";
    public static final String NAME = "SeichiAdd-ons";
    public static final String VERSION = "0.2";

    private static int particleDrawCycle;
    private static byte tickCount;

    private static BuildSkillDrawer buildSkill;
    private static SeichiSkillDrawer seichiSkill;

    private static EntityPlayerSP player;
    private static World world;


    private static void resetStat() {
    	player = Minecraft.getMinecraft().player;
    	world = Minecraft.getMinecraft().world;
    	tickCount = 0;
    }


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        buildSkill = new BuildSkillDrawer(player, world);
        particleDrawCycle = CONFIG.PARTICLECYCLETICK;
        ClientCommandHandler.instance.registerCommand(new LinkCommand());
        resetStat();
    }

    @SubscribeEvent
    public static void onTicked(ClientTickEvent event) {
    	if(tickCount <= particleDrawCycle) {
    		tickCount++;
    		return;
    	}
    	if(buildSkill.skillState.isEnable()) {
    		resetStat();
           	if(Objects.isNull(world)) {
           		buildSkill.skillState.setModeDisable();
           		return;
           	}
           	if(Objects.isNull(player)) {
           		return;
           	}
           	buildSkill.drawParticle(EnumParticleTypes.REDSTONE);
    	}
    }

    @SubscribeEvent
    public static void getChatMessage(ClientChatReceivedEvent event)  {
    	String receivedMsg = new String(event.getMessage().getUnformattedText());
    	if(receivedMsg.equals(BuildSkill.getServerMsgOnDisable())) {
    		buildSkill.skillState.setModeDisable();
    		return;
    	}
    	if(receivedMsg.equals(BuildSkill.getServerMsgOnUpper())) {
    		buildSkill.skillState.setModeUpper();
    		return;
    	}
    	if(receivedMsg.equals(BuildSkill.getServerMsgOnLower())) {
    		buildSkill.skillState.setModeLower();
    		return;
    	}
    }
}