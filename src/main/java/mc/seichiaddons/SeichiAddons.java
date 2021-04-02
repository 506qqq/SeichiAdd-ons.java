package mc.seichiaddons;

import java.util.Objects;

import mc.seichiaddons.commands.LinkCommand;
import mc.seichiaddons.particle.ParticleManager;
import mc.seichiaddons.stat.BuildSkills;
import mc.seichiaddons.stat.Players;
import mc.seichiaddons.stat.Worlds;
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

    private static byte tickCount;
    private static ParticleManager prtMng;
    
    
    //リセット
    public static void resetStat() {
    	Worlds.reloadStat();
    	Players.reloadStat();
    }
    
    //初期化など
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        tickCount = 0;
        prtMng = new ParticleManager();
        ClientCommandHandler.instance.registerCommand(new LinkCommand());
        
    }
    
    
    //Tick毎の処理
    @SubscribeEvent
    public static void onTicked(ClientTickEvent event) {
    		if(tickCount >= CONFIG.PARTICLECYCLETICK) {
    			tickCount = 0;
    			if(BuildSkills.isEnable()) {
    				resetStat();
        	    	if(Objects.isNull(Worlds.getStat())) {//ぬるぽ回避
        	    		BuildSkills.setModeDisable();
        	    		return;
        	    	}
        	    	else if(Objects.isNull(Players.getStat())) {//ぬるぽ回避
        	    		return;
        	    	}
        	    	else {
        	    		resetStat();
        	    		prtMng.drawParticle();
        	    	}
    			}
    		}
    		else {
    			tickCount++;
    		}
    }

    //チャット受信時の処理
    @SubscribeEvent
    public static void getChatMessage(ClientChatReceivedEvent event)  {
    	String chatMsg = new String(event.getMessage().getUnformattedText());
    	if(chatMsg.equals(CONFIG.MSG.ONDISABLESKILL)) {
    		BuildSkills.setModeDisable();
    	}
    	if(chatMsg.equals(CONFIG.MSG.ONUPPERSKILL)) {
    		BuildSkills.setModeUpper();
    	}
    	if(chatMsg.equals(CONFIG.MSG.ONLOWERSKILL)) {
    		BuildSkills.setModeLower();
    	}
    }
}
