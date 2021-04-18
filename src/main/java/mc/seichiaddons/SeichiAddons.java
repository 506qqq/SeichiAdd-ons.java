package mc.seichiaddons;

import java.util.Objects;

import mc.seichiaddons.commands.LinkCommand;
import mc.seichiaddons.stat.BuildSkillDrawer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
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

	public static final byte DirSouth = 0;
    public static final byte DirWest = 1;
    public static final byte DirNorth = 2;
    public static final byte DirEast = 3;

    private static int particleDrawCycle;
    private static byte tickCount;
    private static BuildSkillDrawer buildSkill;

    private static EntityPlayerSP player;
    private static World world;

    //リセット
    private static void resetStat() {
    	player = Minecraft.getMinecraft().player;
    	world = Minecraft.getMinecraft().world;
    	tickCount = 0;
    }

    //初期化など
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        buildSkill = new BuildSkillDrawer();
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
    	if(buildSkill.isEnable()) {
    		resetStat();
           	if(Objects.isNull(world)) {
           		buildSkill.setModeDisable();
           		return;
           	}
           	if(Objects.isNull(player)) {
           		return;
           	}
           	buildSkill.drawParticle(player, world);
    	}
    }

    @SubscribeEvent
    public static void getChatMessage(ClientChatReceivedEvent event)  {
    	String receivedMsg = new String(event.getMessage().getUnformattedText());
    	if(receivedMsg.equals(buildSkill.getServerMsgOnDisable())) {
    		buildSkill.setModeDisable();
    		return;
    	}
    	if(receivedMsg.equals(buildSkill.getServerMsgOnUpper())) {
    		buildSkill.setModeUpper();
    		return;
    	}
    	if(receivedMsg.equals(buildSkill.getServerMsgOnLower())) {
    		buildSkill.setModeLower();
    		return;
    	}
    }
}
