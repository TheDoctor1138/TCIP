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
import train.common.library.BlockIDs;

public class TileSupport extends TileEntity {

    private int[] ballast = new int[3];
    private int facingMeta;
    public int linkedX;
    public int linkedY;
    public int linkedZ;
    public boolean isLinkedToBlock;
    private String type;
    public boolean hasModel = true;
    private int updateTicks;
    private int upadteTicks2;
    public Item idDrop;
    private int isLeftFlag = -5;

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
            return new int[]{0};
        }
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        Block type = getBlockType();
        if (type == BlockIDs.tcRail.block )
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
        if (nbt.hasKey("type")) {
            type = nbt.getString("type");
        } else {
            type = null;
        }

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
        if (ballast[0]  != 0) {
            nbt.setInteger("ballast", ballast[0]);
            nbt.setInteger("ballastDamageValue", ballast[1]);
            nbt.setInteger("ballastColour", ballast[2]);
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
