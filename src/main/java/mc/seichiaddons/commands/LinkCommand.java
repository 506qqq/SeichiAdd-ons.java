package mc.seichiaddons.commands;

import java.util.ArrayList;
import java.util.List;

import mc.seichiaddons.CONFIG;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;

public class LinkCommand implements ICommand {

	private final List<String> aliases = new ArrayList<String>();
	private final String[] keywords;
	private final String[] Links;
	private final String[] Descriptions;

	private List<Integer> matchResult = new ArrayList<>();

	GuiScreen GUI;

	public LinkCommand() {
		keywords = CONFIG.LINKS.KEYWORDS;
		Links = CONFIG.LINKS.URLS;
		Descriptions = CONFIG.LINKS.DESCS;
		matchResult.clear();
		for(String alias: CONFIG.LINKS.COMMAND_ALIASES) {
			aliases.add(alias);
		}
	}

	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		if(args.length == 0) {
			showUsage();
		}
		else if(args.length == 1) {
			matchResult.clear();
			boolean hasResultFound = false;
			String arg = args[0];
			String keyword;
			if(arg == "help") {
				showUsage();
			}
			for(int i = 0; i < keywords.length; i++) {
				keyword = keywords[i];
				if(keyword.length() > arg.length()) {
					keyword = keyword.substring(0, arg.length());
				}
				if(arg.equals(keyword)) {
					matchResult.add(i);
					hasResultFound = true;
				}
			}
			if(hasResultFound) {
				showMessage(String.format(CONFIG.LINKS.MESSAGE_BEGINSHOWRESULT, arg));
				matchResult.forEach((i) -> showLink(Links[i], Descriptions[i]));
				showMessage(CONFIG.LINKS.MESSAGE_FINISHSHOWRESULT);
			} 
			else { 
				showMessage(CONFIG.LINKS.MESSAGE_NOTFOUNDRESULT);
			}
		}
		else {
			showMessage("Error");
		}
	}

	public String getName() {
		return CONFIG.LINKS.NAME;
	}

	public String getUsage(ICommandSender sender) {
		return CONFIG.LINKS.USAGE;
	}
	
	public void showUsage() {
		showMessage(CONFIG.LINKS.MESSAGE_HELP);
	}

	public void showMessage(String Msg) {
		TextComponentString Str = new TextComponentString(Msg);
		Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(Str);
	}

	public void showLink(String Url, String Description) {
		TextComponentString Str = new TextComponentString(Description + ": " + Url);
		Str.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Url));
		Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(Str);
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}
}