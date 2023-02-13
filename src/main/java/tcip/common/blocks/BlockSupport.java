package tcip.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tcip.common.TCIP;
import tcip.common.tile.TileSupport;

import java.util.Random;

public class BlockSupport extends Block {

    protected BlockSupport() {
        super(Material.iron);
        setCreativeTab(null);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        getUseNeighborBrightness();

    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        TileSupport tileEntity = (TileSupport) world.getTileEntity(x, y, z);
        if (tileEntity != null && tileEntity.idDrop != null) {
            return new ItemStack(tileEntity.idDrop);
        }
        return null;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    private static final int[] matrixXZ = {0, -1, -2, 1, 2}, matrixY = {0, -1, -2, 1, 2};

    @Override
    public void breakBlock(World world, int i, int j, int k, Block par5, int par6) {
        TileSupport tileEntity = (TileSupport) world.getTileEntity(i, j, k);
        if (tileEntity != null && tileEntity.isLinkedToBlock) {
            // NOTE: func_147480_a = destroyBlock
            world.func_147480_a(tileEntity.linkedX, tileEntity.linkedY, tileEntity.linkedZ, false);
            world.removeTileEntity(tileEntity.linkedX, tileEntity.linkedY, tileEntity.linkedZ);
        }
        if (tileEntity != null && (tileEntity.idDrop != null) && !world.isRemote) {
            EntityPlayer player = TCIP.proxy.getPlayer();
            if (!(player != null && player.capabilities.isCreativeMode)) {
                this.dropBlockAsItem(world, i, j, k, new ItemStack(tileEntity.idDrop, 1, 0));
            }
        }
        for (int x : matrixXZ) {
            for (int z : matrixXZ) {
                for (int y : matrixY) {
                    if (tileEntity != null && world.getBlock(x + tileEntity.xCoord, y + tileEntity.yCoord, z + tileEntity.zCoord) instanceof BlockSupportGag) {
                        world.notifyBlockChange((x + tileEntity.xCoord), (y + tileEntity.yCoord + 1), (z + tileEntity.zCoord), Blocks.air);
                        world.markBlockForUpdate((x + tileEntity.xCoord), (y + tileEntity.yCoord + 1), (z + tileEntity.zCoord));
                    }
                    if (tileEntity != null && world.getBlock(x + tileEntity.xCoord, y + tileEntity.yCoord, z + tileEntity.zCoord) instanceof BlockSupport) {
                        world.notifyBlockChange((x + tileEntity.xCoord), (y + tileEntity.yCoord + 1), (z + tileEntity.zCoord), Blocks.air);
                        world.markBlockForUpdate((x + tileEntity.xCoord), (y + tileEntity.yCoord + 1), (z + tileEntity.zCoord));
                    }
                }
            }
        }

        world.removeTileEntity(i, j, k);
    }

/*
    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block par5) {
        TileEntity tile = world.getTileEntity(i, j, k);
        if (tile == null || !(tile instanceof TileSupport))
            return;

        TileSupport tileEntity = (TileSupport) world.getTileEntity(i, j, k);
        if (tileEntity != null && tileEntity.isLinkedToBlock) {
            if (world.isAirBlock(tileEntity.linkedX, tileEntity.linkedY, tileEntity.linkedZ)) {
                // NOTE: func_147480_a = destroyBlock
                world.removeTileEntity(i, j, k);
                world.func_147480_a(i, j, k, false);
            }
        }
        if (!World.doesBlockHaveSolidTopSurface(world, i, j - 1, k)) {
            // NOTE: func_147480_a = destroyBlock
            world.func_147480_a(i, j, k, false);
            world.removeTileEntity(i, j, k);
        }

    }
*/
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileSupport();
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k)
    {
        TileSupport tileEntity = (TileSupport) world.getTileEntity(i, j, k);
        if (tileEntity != null && tileEntity.hasCustomBlockBounds){

            this.setBlockBounds((float) tileEntity.getBlockBounds()[0], (float)tileEntity.getBlockBounds()[1],(float)tileEntity.getBlockBounds()[2],(float)tileEntity.getBlockBounds()[3],(float)tileEntity.getBlockBounds()[4],(float)tileEntity.getBlockBounds()[5]);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        TileEntity tileEntity = world.getTileEntity(i, j, k);
        if (tileEntity instanceof TileSupport) {
            return AxisAlignedBB.getBoundingBox(i + this.minX , j + this.minY , k + this.minZ , i + maxX, j + this.maxY , k + this.maxZ);
        }

        return null;
    }



}
