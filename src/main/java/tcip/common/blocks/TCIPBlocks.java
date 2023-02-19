package tcip.common.blocks;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tcip.common.TCIP;
import tcip.common.blocks.Slabs.BlockAsphaltSlab;
import tcip.common.blocks.Slabs.BlockBallastSlab;
import tcip.common.library.BlockIDs;
import tcip.common.library.Info;

public class TCIPBlocks {

    public static void initialise(){
        loadBlocks();
        registerBlocks();
        //setHarvestLevels();
    }


    public static void loadBlocks(){

        if (Loader.isModLoaded("tc")) {
            TCIP.tcipLog.info("Initialising TrainCraft Dependant Blocks");
            BlockIDs.ballastSlab.block = new BlockBallastSlab(false).setCreativeTab(TCIP.tcipTab).setBlockTextureName("tcip:ballast");
            BlockIDs.ballastDoubleSlab.block = new BlockBallastSlab(true).setBlockTextureName("tcip:ballast");
            BlockIDs.ballastStairs.block = new BaseStairs(BlockIDs.ballastSlab.block).setHardness(2.0f).setStepSound(Block.soundTypeStone).setBlockName("Ballast Stairs").setCreativeTab(TCIP.tcipTab).setLightOpacity(0);
            BlockIDs.signal.block = new BlockSignal();
            TCIP.tcipLog.info("Finished Initialising TrainCraft Dependant Blocks");

        }
        BlockIDs.asphalt.block = new BaseBlock("Asphalt Block", 2f, 10f, "pickaxe", 1, Material.rock, Block.soundTypeStone, "asphalt");
        BlockIDs.asphaltSlab.block = new BlockAsphaltSlab(false).setCreativeTab(TCIP.tcipTab).setBlockTextureName("tcip:asphalt");
        BlockIDs.asphaltDoubleSlab.block = new BlockAsphaltSlab(true).setBlockTextureName("tcip:asphalt");
        BlockIDs.asphaltStairs.block = new BaseStairs(BlockIDs.asphalt.block).setHardness(2.0f).setStepSound(Block.soundTypeStone).setBlockName("Asphalt Stairs").setCreativeTab(TCIP.tcipTab).setLightOpacity(0);
        BlockIDs.support.block = new BlockSupport();
        BlockIDs.supportGag.block = new BlockSupportGag();


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
