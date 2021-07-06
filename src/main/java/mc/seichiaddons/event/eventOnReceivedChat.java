package mc.seichiaddons.event;

import mc.seichiaddons.skill.state.BuildSkillStateContainer;
import mc.seichiaddons.skill.state.SeichiSkillStateContainer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class eventOnReceivedChat {
    @SubscribeEvent
    public static void getChatMessage(ClientChatReceivedEvent event)  {
    	String receivedMsg = new String(event.getMessage().getUnformattedText());
    	BuildSkillStateContainer.setSkillByChat(receivedMsg);
    	SeichiSkillStateContainer.setSkillByChat(receivedMsg);
    }
}
