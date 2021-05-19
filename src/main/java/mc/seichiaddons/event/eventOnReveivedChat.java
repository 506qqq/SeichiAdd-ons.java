package mc.seichiaddons.event;

import mc.seichiaddons.SeichiAddons;
import mc.seichiaddons.skill.BuildSkill;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class eventOnReveivedChat {
    @SubscribeEvent
    public static void getChatMessage(ClientChatReceivedEvent event)  {
    	String receivedMsg = new String(event.getMessage().getUnformattedText());
    	if(receivedMsg.equals(BuildSkill.getServerMsgOnDisable())) {
    		SeichiAddons.buildSkill.skillState.setModeDisable();
    		return;
    	}
    	if(receivedMsg.equals(BuildSkill.getServerMsgOnUpper())) {
    		SeichiAddons.buildSkill.skillState.setModeUpper();
    		return;
    	}
    	if(receivedMsg.equals(BuildSkill.getServerMsgOnLower())) {
    		SeichiAddons.buildSkill.skillState.setModeLower();
    		return;
    	}
    	if(receivedMsg.matches(": ON$")) {
    		SeichiAddons.seichiSkill.skillState.setSkillByChatMsg(receivedMsg);
    	}
    	if(receivedMsg.matches(": OFF$")) {
    		SeichiAddons.seichiSkill.skillState.setSkillByChatMsg(receivedMsg);
    	}
    }
}
