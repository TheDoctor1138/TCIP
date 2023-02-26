package tcip.client.core;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;
import tcip.client.core.handlers.ClientTickHandler;
import tcip.client.render.blocks.RenderPlatform;
import tcip.client.render.blocks.RenderSignal;
import tcip.common.TCIP;
import tcip.common.core.CommonProxy;
import tcip.common.tile.TileSignal;
import tcip.common.tile.TileSupport;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

public class ClientProxy extends CommonProxy {
    public static boolean isHoliday() {
        Calendar cal = Calendar.getInstance();
        return(cal.get(Calendar.MONTH) == Calendar.DECEMBER || (cal.get(Calendar.MONTH) == Calendar.JANUARY) && cal.get(Calendar.DATE) < 7);
    }
    public static boolean isPumpkin() {
        Calendar cal = Calendar.getInstance();
        return(cal.get(Calendar.MONTH) == Calendar.OCTOBER || cal.get(Calendar.MONTH) == Calendar.NOVEMBER && cal.get(Calendar.DATE) < 15);
    }



    @Override
    public void registerEvents(FMLPreInitializationEvent event) {
        super.registerEvents(event);
    }

    @Override
    public void registerRenderInformation(){
        FMLCommonHandler.instance().bus().register(new ClientTickHandler());
        /**Handles rendering of block*/
        // ClientRegistry.bindTileEntitySpecialRenderer(TILE.class, new RenderTILE());
        /**Handles rendeing of held Item (if that is a model)*/
        // MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BLOCK.block), new ItemRenderBLOCK());


        ClientRegistry.bindTileEntitySpecialRenderer(TileSupport.class, new RenderPlatform());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSignal.class, new RenderSignal());



    }

    @Override
    public Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }

    @Override
    public EntityPlayer getPlayer() {
        return getMinecraft().thePlayer;
    }


    @Optional.Method(modid = "NotEnoughItems")
    @Override
    public void doNEICheck(ItemStack stack) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            if(Loader.isModLoaded("Not Enough Items")) {
                try {
                    Class neiApi = Class.forName("codechicken.nei.api.API");
                    Method hideItem = neiApi.getDeclaredMethod("hideItem", stack.getClass());
                    hideItem.invoke(null, stack);
                } catch (ClassNotFoundException e) {
                    TCIP.tcipLog.log(Level.WARN, "Chicken core didn't have required class: Wrong version of the library or something is horribly wrong", e);
                } catch (NoSuchMethodException e) {
                    TCIP.tcipLog.log(Level.WARN, "Chicken core didn't have required method: Wrong version of the library or something is horribly wrong", e);
                } catch (SecurityException e) {
                    TCIP.tcipLog.log(Level.FATAL, "Something is horribly wrong", e);
                } catch (IllegalAccessException e) {
                    TCIP.tcipLog.log(Level.FATAL, "Something is horribly wrong", e);
                } catch (IllegalArgumentException e) {
                    TCIP.tcipLog.log(Level.WARN, "Chicken core had the method but it's signature was wrong: Wrong version of the library or something is horribly wrong", e);
                } catch (InvocationTargetException e) {
                    TCIP.tcipLog.log(Level.WARN, "The method we called from Chicken core threw an exception", e);
                }
            }
        }
    }


}
