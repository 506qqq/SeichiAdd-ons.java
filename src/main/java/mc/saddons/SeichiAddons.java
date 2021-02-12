package mc.saddons;

import java.util.Objects;

import org.apache.logging.log4j.Logger;

import mc.saddons.particle.ParticleManager;
import mc.saddons.stat.BuildSkills;
import mc.saddons.stat.Players;
import mc.saddons.stat.Worlds;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Type;
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
    public static final String MODID = "saddons";
    public static final String NAME = "SeichiAdd-ons";
    public static final String VERSION = "0.1a";

    private static Logger logger;
    private static byte tickCount;
    private static ParticleManager prtMng;

    @Config(modid = MODID, type = Type.INSTANCE, name = "SeichiAdd-ons")
    public static class CONFIG {
    	@Comment({"\n"
    			+ "*****************************************\n"
    			+ "ファイル削除で元のファイルが生成されます。\n"
    			+ "*****************************************\n"
    			+ "\n"
    			+ "パーティクル生成の周期"})
    	public static int PARTICLECYCLETICK = 16;
    	public static MSGTEXT MSG = new MSGTEXT();
    	public static class MSGTEXT {
    		@Comment({"スキル有効時のメッセージ"})
    		public String ONDISABLESKILL = "ブロックを並べるスキル(仮): OFF";
    		public String ONUPPERSKILL = "ブロックを並べるスキル(仮): 上側";
    		public String ONLOWERSKILL = "ブロックを並べるスキル(仮): 下側";
    	}
    }
    
    //リセット
    public static void resetStat() {
    	Worlds.unloadStat();
    	Players.unloadStat();
    	BuildSkills.setModeDisable();
    }
    
    //初期化など
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        tickCount = 0;
        prtMng = new ParticleManager();
    }

    //Tick毎の処理
    @SubscribeEvent
    public static void onTicked(ClientTickEvent event) {
    		if(tickCount >= CONFIG.PARTICLECYCLETICK) {
    			tickCount = 0;
    			if(BuildSkills.isEnable()) {
    				Players.reloadStat();
    				Worlds.reloadStat();
        	    	if(Objects.isNull(Worlds.getStat())) {//ぬるぽ回避
        	    		resetStat();
        	    		return;
        	    	}
        	    	else if(Objects.isNull(Players.getStat())) {//ぬるぽ回避
        	    		return;
        	    	}
        	    	else {
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
    	if(chatMsg.equals("deb")) {
    		logger.info("MODE is {}", BuildSkills.getModes());
    	}
    }
}
