package tcip.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import tcip.common.items.ItemPlatform;
import tcip.common.library.BlockIDs;

import javax.annotation.Nullable;

public class TileSupport extends TileEntity {

    private int[] ballast = new int[3];
    private double[] blockBounds = new double[6];
    private int facingMeta;
    public int linkedX;
    public int linkedY;
    public int linkedZ;
    public boolean isLinkedToBlock;
    public String type;
    public boolean hasModel = true;
    private int updateTicks;
    private int upadteTicks2;
    public Item idDrop;
    private int isLeftFlag = -5;
    public boolean hasCustomBlockBounds;
    public TileSupport() {
        if(this.worldObj != null)
            facingMeta = this.getBlockMetadata();
    }


    public double getMaxRenderDistanceSquared(){
        return 16384.0;
    }


    public int getFacing() {
        return facingMeta;
    }

    public void setFacing(int facing) {
        this.facingMeta = facing;
    }

    public void setType(String type) {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        this.type = type;
    }

    public String getType(){
        return this.type;
    }



    public void setBlockBounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ){
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        this.blockBounds[0] = minX;
        this.blockBounds[1] = minY;
        this.blockBounds[2] = minZ;
        this.blockBounds[3] = maxX;
        this.blockBounds[4] = maxY;
        this.blockBounds[5] = maxZ;

    }
    @Nullable
    public double[] getBlockBounds() {
        if (blockBounds == null) {
            blockBounds[0] = 0;
            blockBounds[1] = 0;
            blockBounds[2] = 0;
            blockBounds[3] = 0;
            blockBounds[4] = 0;
            blockBounds[5] = 0;
        }
        return blockBounds;
    }

    public void setBallast(int ballastID, int ballastDamageValue, int ballastColour){
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        this.ballast[0] = ballastID;
        this.ballast[1] = ballastDamageValue;
        this.ballast[2] = ballastColour;

    }

    public int[] getBallast(){
        if (ballast != null){
            return ballast;
        }
        else {
            return null;
        }
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        Block type = getBlockType();
        if (type == BlockIDs.support.block)
        {
            bb = AxisAlignedBB.getBoundingBox(xCoord - 18, yCoord, zCoord - 18, xCoord + 18, yCoord , zCoord + 18);
        }

        return bb;
    }






    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        facingMeta = nbt.getByte("Orientation");
        linkedX = nbt.getInteger("linkedX");
        linkedY = nbt.getInteger("linkedY");
        linkedZ = nbt.getInteger("linkedZ");
        if (nbt.hasKey("ballast")) {
            ballast[0] = nbt.getInteger("ballast");
            ballast[1] = nbt.getInteger("ballastDamageValue");
            ballast[2] = nbt.getInteger("ballastColour");
        }
        else {
            ballast[0] = 0;
            ballast[1] = 0;
            ballast[2] = 0;
        }

        if (nbt.hasKey("hasBlockBounds")){
            blockBounds[0] = nbt.getDouble("minX");
            blockBounds[1] = nbt.getDouble("minY");
            blockBounds[2] = nbt.getDouble("minZ");
            blockBounds[3] = nbt.getDouble("maxX");
            blockBounds[4] = nbt.getDouble("maxY");
            blockBounds[5] = nbt.getDouble("maxZ");

        }
        if (nbt.hasKey("type")) {
            type = nbt.getString("type");
        } else {
            type = ItemPlatform.PlatformTypes.STRAIGHT.getLabel();
        }
        hasCustomBlockBounds = nbt.getBoolean("hasBlockBounds");
        isLinkedToBlock = nbt.getBoolean("isLinkedToBlock");
        hasModel = nbt.getBoolean("hasModel");
        idDrop = Item.getItemById(nbt.getInteger("idDrop"));

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setByte("Orientation", (byte) facingMeta);
        nbt.setInteger("linkedX", linkedX);
        nbt.setInteger("linkedY", linkedY);
        nbt.setInteger("linkedZ", linkedZ);
        if (type != null) {
            nbt.setString("type", type);
        }
        if (ballast != null) {
            nbt.setInteger("ballast", ballast[0]);
            nbt.setInteger("ballastDamageValue", ballast[1]);
            nbt.setInteger("ballastColour", ballast[2]);
        }
        nbt.setBoolean("hasBlockBounds", hasCustomBlockBounds);
        if (blockBounds != null) {

            nbt.setDouble("minX", blockBounds[0]);
            nbt.setDouble("minY", blockBounds[1]);
            nbt.setDouble("minZ", blockBounds[2]);
            nbt.setDouble("maxX", blockBounds[3]);
            nbt.setDouble("maxY", blockBounds[4]);
            nbt.setDouble("maxZ", blockBounds[5]);
        }
        nbt.setBoolean("isLinkedToBlock", isLinkedToBlock);
        nbt.setBoolean("hasModel", hasModel);
        nbt.setInteger("idDrop", Item.getIdFromItem(idDrop));
    }

    @Override
    public Packet getDescriptionPacket() {

        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);

        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
        this.readFromNBT(pkt.func_148857_g());
        super.onDataPacket(net, pkt);
    }

}
