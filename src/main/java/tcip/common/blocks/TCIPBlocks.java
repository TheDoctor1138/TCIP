package tcip.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tcip.common.TCIP;
import tcip.common.library.BlockIDs;
import tcip.common.library.Info;

public class TCIPBlocks {

    public static void initialise(){
        loadBlocks();
        registerBlocks();
        //setHarvestLevels();
    }


    public static void loadBlocks(){
        BlockIDs.asphalt.block = new BaseBlock("Asphalt Block", 2f, 10f, "pickaxe", 1, Material.rock, Block.soundTypeStone, "asphalt");
        BlockIDs.asphaltSlab.block = new BlockAsphaltSlab(false).setCreativeTab(TCIP.tcipTab).setBlockTextureName("asphalt");
        BlockIDs.asphaltDoubleSlab.block = new BlockAsphaltSlab(true).setBlockTextureName("tc:asphalt");
        BlockIDs.asphaltStairs.block = new BaseStairs(BlockIDs.asphalt.block).setHardness(2.0f).setStepSound(Block.soundTypeStone).setBlockName("Asphalt Stairs").setCreativeTab(TCIP.tcipTab).setLightOpacity(0);
    }

    public static void registerBlocks() {
        for (BlockIDs blocks : BlockIDs.values()) {
            if(blocks.block != null) {
                blocks.block.setBlockName(Info.modID + ":" + blocks.name());
                if (blocks.hasItemBlock) {
                    GameRegistry.registerBlock(blocks.block, blocks.itemBlockClass, blocks.name());
                } else {
                    GameRegistry.registerBlock(blocks.block, blocks.name());
                }
            }
        }
    }


}
