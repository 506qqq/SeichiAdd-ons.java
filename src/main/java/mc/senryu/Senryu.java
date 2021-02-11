package mc.senryu;

import java.util.Objects;

import org.apache.logging.log4j.Logger;

import mc.senryu.managers.ParticleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;
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

@Mod(modid = Senryu.MODID, name = Senryu.NAME, version = Senryu.VERSION)
@EventBusSubscriber
public class Senryu
{
    public static final String MODID = "senryu";
    public static final String NAME = "Senryu-Mod";
    public static final String VERSION = "0.1-beta";

	private static World world;
	private static EntityPlayerSP player;
	
    private static Logger logger;
    private static byte tickCount;
    private static ParticleManager prtMng;

    //デバッグ用コマンド
    // /tellraw @p {"text": "ブロックを並べるスキル(仮): OFF", "color": "green"}
    // /tellraw @p {"text": "ブロックを並べるスキル(仮): 上側", "color": "green"}
    // /tellraw @p {"text": "ブロックを並べるスキル(仮): 下側", "color": "green"}
    // /tellraw @p {"text": "deb", "color": "green"}



    @Config(modid = MODID, type = Type.INSTANCE, name = "SenryuConfig")
    public static class CONFIG {
    	@Comment({"*****************************************\n"
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

    public static EntityPlayerSP getPlayer() {
    	return player;
    }
    
    public static World getWorld() {
    	return world;
    }
    
    public static void reloadPlayer() {
    	player = Minecraft.getMinecraft().player;
    }
    
    public static void reloadWorld() {
    	world = Minecraft.getMinecraft().world;
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
    				reloadPlayer();
    				reloadWorld();
        	    	if(Objects.isNull(player)) {//ぬるぽ回避
        	    		return;
        	    	}
        	    	else if(Objects.isNull(world)) {//ぬるぽ回避
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