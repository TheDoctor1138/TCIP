package tcip.common.core;

import cpw.mods.fml.common.Loader;

public class TCIPCore {

    public static boolean isTCLoaded = false;

    public static void ModsLoaded() {
        if(Loader.isModLoaded("tc")){

            isTCLoaded = true;
        }
    }

}
