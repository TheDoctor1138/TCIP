package tcip.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import tcip.common.library.Info;
import tcip.common.library.ItemIDs;

public class TCIPItems {

    public static void initialise() {
        loadItems();
        registerItems();
    }

    private static void loadItems() {

        ItemIDs.trackDebugger.item = new ItemSupportDebugger();
    }

    private static void registerItems() {
        for (ItemIDs items : ItemIDs.values()) {
            if (items.item != null) {
                items.item.setUnlocalizedName(Info.modID + ":" + items.name());
                GameRegistry.registerItem(items.item, items.name());
            }
        }
    }
}
