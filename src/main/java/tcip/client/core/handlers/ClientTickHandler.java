package tcip.client.core.handlers;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import tcip.common.TCIP;
import tcip.common.library.Info;

public class ClientTickHandler {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static boolean isHidden = false;

    @SubscribeEvent
    public void tick(TickEvent event) {
        if(event.side != Side.CLIENT) {
            return;
        }
        switch(event.phase) {
            case START:
                tickStart(event);
                break;
            case END:
                break;
            default:
                break;
        }
    }

    private void tickStart(TickEvent event) {


        if(!isHidden && Loader.isModLoaded("Not Enough Items")) {
            if(mc.theWorld != null && mc.theWorld.playerEntities != null) {
                TCIP.proxy.doNEICheck(new ItemStack(Block.getBlockFromName(Info.modID + ":support")));
                TCIP.proxy.doNEICheck(new ItemStack(Block.getBlockFromName(Info.modID + ":supportGag")));
                isHidden = true;
            }
        }
    }

}