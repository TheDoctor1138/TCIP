package tcip.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import tcip.common.TCIP;
import tcip.common.library.Info;
import tcip.common.library.ItemIDs;

public class TCIPItems {

    public static void initialise() {
        loadItems();
        registerItems();
    }

    private static void loadItems() {

        ItemIDs.trackDebugger.item = new ItemSupportDebugger();
        ItemIDs.PlatformStraight.item = new ItemPlatform(ItemPlatform.PlatformTypes.STRAIGHT);
    }

    private static void registerItems() {
        for (ItemIDs items : ItemIDs.values()) {
            if (items.item != null) {
                items.item.setUnlocalizedName(Info.modID + ":" + items.name()).setCreativeTab(TCIP.tcipTab);
                GameRegistry.registerItem(items.item, items.name());
            }
        }
    }
}
