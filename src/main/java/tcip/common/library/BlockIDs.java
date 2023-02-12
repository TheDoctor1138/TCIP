package tcip.common.library;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import tcip.common.items.ItemAsphaltSlab;

public enum BlockIDs {

    asphalt(false, null),
    asphaltSlab(true, ItemAsphaltSlab.class),
    asphaltDoubleSlab(true, ItemAsphaltSlab.class),
    asphaltStairs(false, null),

    ;

    public Block block;
    public boolean hasItemBlock;
    public Class itemBlockClass;


    BlockIDs(boolean hasItemBlock, Class<? extends ItemBlock> itemBlockClass) {
        this.hasItemBlock = hasItemBlock;
        this.itemBlockClass = itemBlockClass;
    }

}
