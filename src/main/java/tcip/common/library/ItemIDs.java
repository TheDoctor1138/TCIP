package tcip.common.library;

import net.minecraft.item.Item;

public enum ItemIDs{
    trackDebugger("ItemDebugger", "item_debugger"),
    PlatformStraight("ItemPlatform", "item_platform_straight"),
    itemSpanishSignal("ItemSignal", "item_signal_spanish")
    ;


    public Item item;
    public String className;
    public String iconName;

    /**
     * @param classMethodName
     * @param iconName
     */
    ItemIDs(String classMethodName, String iconName) {
        this.className = classMethodName;
        this.iconName = iconName;
    }
}
