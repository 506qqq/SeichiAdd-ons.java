package mc.seichiaddons;

import mc.seichiaddons.command.LinkCommand;
import mc.seichiaddons.skill.particle.BuildSkillDrawer;
import mc.seichiaddons.skill.particle.SeichiSkillDrawer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SeichiAddons.MODID)
public class SeichiAddons {
    public static final String MODID = "seichiaddons";
    public static final String NAME = "SeichiAdd-ons";
    public static final String VERSION = "0.2";

    public static BuildSkillDrawer buildSkill;
    public static SeichiSkillDrawer seichiSkill;

    public static EntityPlayerSP player;
    public static World world;

    public static void resetStat() {
    	player = Minecraft.getMinecraft().player;
    	world = Minecraft.getMinecraft().world;
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        buildSkill = new BuildSkillDrawer(player, world);
        seichiSkill = new SeichiSkillDrawer(player, world);
        ClientCommandHandler.instance.registerCommand(new LinkCommand());
        resetStat();
    }
}