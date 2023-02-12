package tcip.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tcip.common.TCIP;
import tcip.common.library.Info;


public class BaseBlock extends Block {

    public BaseBlock(String name, float hardness, float resistance, String harvestTool, int harvestLevel, Material material, SoundType stepSound, String textureLocation) {

        super(material);
        setCreativeTab(TCIP.tcipTab);
        setBlockName(name);
        setHardness(hardness);
        setResistance(resistance);
        setHarvestLevel(harvestTool, harvestLevel);
        setStepSound(stepSound);
        setBlockTextureName(Info.modID+":"+textureLocation);


    }
    @Override
    protected void dropBlockAsItem (World world, int x, int y, int z, ItemStack itemStack){
        super.dropBlockAsItem(world, x, y, z, itemStack);
    }

}
