package tcip.common.library;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import tcip.common.items.slabs.*;

public enum BlockIDs {

    /**Normal Blocks*/
    asphalt(false, null),
    /**Slabs*/
    asphaltSlab(true, ItemAsphaltSlab.class),
    asphaltDoubleSlab(true, ItemAsphaltSlab.class),
    ballastSlab(true, ItemBallastSlab.class),
    ballastDoubleSlab(true, ItemBallastSlab.class),
    /**Stairs*/
    asphaltStairs(false, null),
    ballastStairs(false, null),
    /**Model Blocks*/

    support(false, null),
    supportGag(false, null),
    platformStraight(false, null),

    /**Functional Blocks*/





    /**TCCE Dependant Blocks*/

    signal(false, null),
    ;

    public Block block;
    public boolean hasItemBlock;
    public Class itemBlockClass;


    BlockIDs(boolean hasItemBlock, Class<? extends ItemBlock> itemBlockClass) {
        this.hasItemBlock = hasItemBlock;
        this.itemBlockClass = itemBlockClass;
    }

}
