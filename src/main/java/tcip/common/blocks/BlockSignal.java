package tcip.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tcip.common.TCIP;
import tcip.common.tile.TileSignal;
import train.common.library.Info;

import java.util.Random;

public class BlockSignal extends Block {
    private IIcon texture;
    protected BlockSignal() {
        super(Material.iron);
        setCreativeTab(null);
        this.setBlockBounds(0.250f, 0.0f, 0.250f, 0.75f, 2f, 0.75f);
        getUseNeighborBrightness();
        this.setBlockName("Signal");

    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        TileSignal tileEntity = (TileSignal) world.getTileEntity(x, y, z);
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
        TileSignal tileEntity = (TileSignal) world.getTileEntity(i, j, k);
        if (tileEntity != null && (tileEntity.idDrop != null) && !world.isRemote) {
            EntityPlayer player = TCIP.proxy.getPlayer();
            if (!(player != null && player.capabilities.isCreativeMode)) {
                this.dropBlockAsItem(world, i, j, k, new ItemStack(tileEntity.idDrop, 1, 0));
            }
        }

        world.removeTileEntity(i, j, k);
    }


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
        return new TileSignal();
    }





    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        texture = iconRegister.registerIcon(Info.modID.toLowerCase() + ":asphalt");
    }



    @Override
    public IIcon getIcon(int i, int j) {
        return texture;
    }


    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        TileEntity tileEntity = world.getTileEntity(i, j, k);
        if (tileEntity instanceof TileSignal) {
            return AxisAlignedBB.getBoundingBox(i + this.minX , j + this.minY , k + this.minZ , i + maxX, j + this.maxY , k + this.maxZ);
        }

        return null;
    }

    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block par5) {
        TileEntity tile = world.getTileEntity(i, j, k);
        if (tile == null || !(tile instanceof TileSignal))
            return;
        TileSignal tileEntity = (TileSignal) world.getTileEntity(i, j, k);
        if (tileEntity != null && !world.isRemote) {
            tileEntity.isGettingPowered = world.isBlockIndirectlyGettingPowered(i, j, k);
        }
    }

    @Override
    public boolean canProvidePower() {return true;}

    public int isProvidingWeakPower(IBlockAccess world, int i, int j, int k, int p_149709_5_)
    {
        TileEntity tile = world.getTileEntity(i, j, k);
        if (tile instanceof TileSignal) {
            TCIP.tcipLog.info(((TileSignal) tile).getSignalValue());
            switch (((TileSignal) tile).getSignalValue()) {
                case 1:
                    return 0;
                case 2:
                    return 7;
                default:
                    return 15;
            }
        }
        else {
            return 0;
        }
    }





}


