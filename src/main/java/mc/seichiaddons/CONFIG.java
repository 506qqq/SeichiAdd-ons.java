package mc.seichiaddons;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = SeichiAddons.MODID, type = Type.INSTANCE, name = "SeichiAdd-ons")
public class CONFIG {
	@Comment({"\n"
			+ "*****************************************\n"
			+ "ファイル削除で元のファイルが生成されます。\n"
			+ "*****************************************\n"
			+ "\n"
			+ "パーティクル生成の周期"})
    public static int stickItemID = 280;
	public static int PARTICLECYCLETICK = 16;
	public static MSGTEXT MSG = new MSGTEXT();
	public static LINKTXT LINKS = new LINKTXT();
	public static class MSGTEXT {
		@Comment({"スキル有効時のメッセージ"})
		public String ONDISABLESKILL = "直列設置: OFF";
		public String ONUPPERSKILL = "直列設置: 上側";
		public String ONLOWERSKILL = "直列設置: 下側";
	}
	public static class LINKTXT {
		public String[] COMMAND_ALIASES = {"url", "urls", "li"};
		public String[] KEYWORDS = {"hp",  "votejp", "voteorg", "buildhp", "rule"};
		public String[] URLS = {
				"https://www.seichi.network/", 
				"https://minecraft.jp/servers/play.seichi.click", 
				"https://minecraftservers.org/vote/575658", 
				"https://www.seichi.network/build", 
				"https://www.seichi.network/rule",
		};
		public String[] DESCS = {
				"ホームページ", 
				"投票(.jp)", 
				"投票(.org)",
				"HP[建築]", 
				"HP[ルール]"
		};
		
		public String MESSAGE_NOTFOUNDRESULT = "見つかりませんでした";
	}
}