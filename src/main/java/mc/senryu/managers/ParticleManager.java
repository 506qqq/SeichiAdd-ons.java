package mc.senryu.managers;

import java.util.Objects;

import org.apache.logging.log4j.Logger;

import mc.senryu.BuildSkills;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

//パーティクル表示系
public class ParticleManager {
	Logger logger;

	private static World world;
	private static EntityPlayerSP player;

	private static ItemStack stick = new ItemStack(Item.getItemById(280/*棒*/));

	private double px;
	private double py;
	private double pz;

	public ParticleManager() {
		world = Minecraft.getMinecraft().world;
		player = Minecraft.getMinecraft().player;
	}

	//#defineみたいなのってJavaにはないの...?
    public final byte DirSouth = 0;
    public final byte DirWest = 1;
    public final byte DirNorth = 2;
    public final byte DirEast = 3;


    //描画処理
    public void drawParticle() {
    	if(Objects.isNull(player)) {//ぬるぽ回避
    		player = Minecraft.getMinecraft().player;
    	}
    	else if(Objects.isNull(world)) {//ぬるぽ回避
    		world = Minecraft.getMinecraft().world;
    	}
    	else if(player.getHeldItemOffhand().equals(stick)) {
    		byte Yaw = (byte)((int)(Math.floor((player.rotationYaw / 90.0D) + 0.5D)) & 3);
    		px = ((Math.round((player.posX)-0.5D))+0.5D);//n+0.5の形にする
    		py = Math.round(player.posY + 1.6F) - 0.5D;//この+1.6はBuildAssistより
    		pz = ((Math.round((player.posZ)-0.5D))+0.5D);//n+0.5の形にする
    		switch(Yaw) {
    		case DirSouth:
    			pz+=2.0D;
    			break;
    		case DirWest:
    			px-=2.0D;
    			break;
    		case DirNorth:
    			pz-=2.0D;
    			break;
    		case DirEast:
    			px+=2.0D;
    			break;
    		}
    		if(BuildSkills.isLower()) py -= 1.0;
    		this.showGrid(px, py, pz);
    	}
     }

    public EntityPlayerSP getPlayer() {
    	return player;
    }


    //1ブロックの枠線にパーティクルを配置(引数: ブロックの中心座標(n+0.5の形))}
    public void showGrid(double x, double y, double z) {
    	for(int bit = 0; (bit&(1<<3)) == 0; bit++) {
    		showRSdust( (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
    					(bit&(1<<1)) == 0? y-0.5D: y+0.5D,
    					(bit&(1<<2)) == 0? z-0.5D: z+0.5D);
    	}

    	for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
    		showRSdust( (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
    					(bit&(1<<1)) == 0? y-0.5D: y+0.5D,
    					z);
    	}
    	for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
    		showRSdust( (bit&(1<<0)) == 0? x-0.5D: x+0.5D,
    					y,
    					(bit&(1<<1)) == 0? z-0.5D: z+0.5D);
    	}
    	for(int bit = 0; (bit&(1<<2)) == 0; bit++) {
    		showRSdust(x,
    					(bit&(1<<0)) == 0? y-0.5D: y+0.5D,
    					(bit&(1<<1)) == 0? z-0.5D: z+0.5D);
    	}
    }

    //赤石ダストパーティクルを表示(ものぐさ)
    private void showRSdust(double x, double y, double z) {
    	world.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z, 0, 0, 0);
    }

//    private void showHeart(double x, double y, double z) {
//    	world.spawnParticle(EnumParticleTypes.HEART, x, y, z, 0, 0, 0);
//    }
}
