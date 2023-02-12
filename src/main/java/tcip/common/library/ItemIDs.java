package tcip.common.library;

import net.minecraft.item.Item;

public enum ItemIDs{

       trackDebugger("ItemTrackDebugger", "placeholder"),
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
