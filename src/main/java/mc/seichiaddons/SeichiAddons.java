package mc.seichiaddons;

import mc.seichiaddons.command.LinkCommand;
import mc.seichiaddons.skill.particle.BuildSkillDrawer;
import mc.seichiaddons.skill.particle.SeichiSkillDrawer;
import mc.seichiaddons.skill.render.AssaultSkillRenderStateContainer;
import mc.seichiaddons.skill.render.BuildSkillRenderStateContainer;
import mc.seichiaddons.skill.state.BuildSkillStateContainer;
import mc.seichiaddons.skill.state.SeichiSkillStateContainer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "seichiaddons", version = "0.2", name = "SeichiAdd-ons")
@EventBusSubscriber
public class SeichiAddons {
    public static final String MODID = "seichiaddons";
    public static final String NAME = "SeichiAdd-ons";
    public static final String VERSION = "0.2";

    public static BuildSkillDrawer buildSkill;
    public static SeichiSkillDrawer seichiSkill;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        buildSkill = new BuildSkillDrawer();
        seichiSkill = new SeichiSkillDrawer();
        ClientCommandHandler.instance.registerCommand(new LinkCommand());
        resetSkillState();
    }
    
    public void resetSkillState() {
   		BuildSkillStateContainer.setModeDisable();
   		SeichiSkillStateContainer.setModeDisable();
   		BuildSkillRenderStateContainer.cancelRender();
   		AssaultSkillRenderStateContainer.cancelRender();
    }
}