package tcip.common;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tcip.common.blocks.TCIPBlocks;
import tcip.common.core.CommonProxy;
import tcip.common.core.CreativeTabTCIP;
import tcip.common.core.handlers.RecipeHandler;
import tcip.common.items.TCIPItems;
import tcip.common.library.Info;

import java.io.File;

@Mod(modid = Info.modID, name = Info.modName, version = Info.modVersion)
public class TCIP {
    @Mod.Instance(Info.modID)
    public static TCIP instance;

    @SidedProxy(clientSide = "tcip.client.core.ClientProxy", serverSide = "tcip.common.core.CommonProxy" )
     public static CommonProxy proxy;

    public static Logger tcipLog = LogManager.getLogger(Info.modName);


    public static File configDirectory;

    public static CreativeTabs tcipTab;
    public boolean isTCLoaded;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        tcipLog.info("Starting TCIP " + Info.modVersion + "!");
        /* Config handler */
        // configDirectory = event.getModConfigurationDirectory();
        //ConfigHandler.init(new File(event.getModConfigurationDirectory(), Info.modName + ".cfg"));

        tcipLog.info("Initialising Blocks, Items, ....");
        tcipTab = new CreativeTabTCIP(CreativeTabs.getNextID(), "TCIP");
        TCIPBlocks.initialise();
        TCIPItems.initialise();
        proxy.registerTileEntities();
        proxy.setHook();
        tcipLog.info("Finished PreInitialization");
        tcipLog.info("Initialize Renderer and Events");
        proxy.registerRenderInformation();
        proxy.registerEvents(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        tcipLog.info("Activating Mod Compatibility");
        isTCLoaded = Loader.isModLoaded("tc");
    }


    @EventHandler
    public void load(FMLInitializationEvent event){

        tcipLog.info("Started Initialization");

        tcipLog.info("Initialising Recipes");
        RecipeHandler.initBlockRecipes();
        RecipeHandler.initItemRecipes();
        RecipeHandler.initSmeltingRecipes();

        tcipLog.info("Finished Initialization");
    }



    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        tcipLog.info("Started PostInitialization");
        tcipLog.info("Finished PostInitialization");
    }


}
