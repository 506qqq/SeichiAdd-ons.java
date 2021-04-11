package mc.seichiaddons;

import java.util.Objects;

import mc.seichiaddons.commands.LinkCommand;
import mc.seichiaddons.particle.ParticleDrawer;
import mc.seichiaddons.stat.BuildSkill;
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

    private static int particleDrawCycle;
    private static byte tickCount;
    private static ParticleDrawer particleDrawer;
    private static BuildSkill buildSkill;

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
        particleDrawer = new ParticleDrawer();
        buildSkill = new BuildSkill();
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
           	if(Objects.isNull(world)) {//ぬるぽ回避
           		buildSkill.setModeDisable();
           		return;
           	}
           	if(Objects.isNull(player)) {//ぬるぽ回避
           		return;
           	}
           	buildSkill.drawParticle(particleDrawer, player, world);
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
    	if(receivedMsg.equals("deb")) {
    		System.out.println(buildSkill.getModes());
    		return;
    	}
    }
}
