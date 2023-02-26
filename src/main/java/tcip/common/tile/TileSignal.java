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
import tcip.common.items.ItemSignal;
import train.common.api.EntityRollingStock;
import train.common.library.BlockIDs;

import java.util.List;

public class TileSignal extends TileEntity {

    public String type;
    private int facingMeta;
    public int linkedSignal1X;
    public int linkedSignal1Y;
    public int linkedSignal1Z;
    public int linkedSignal2X;
    public int linkedSignal2Y;
    public int linkedSignal2Z;
    public int signalValue;
    public boolean hasRollingStock;
    public boolean isLinked = false;
    public boolean isLinked2 = false;
    public boolean isLinking = false;
    public boolean hasModel = true;
    public boolean isGettingPowered;
    private boolean previousRedStoneState;
    public Item idDrop;
    private int updateTicks;
    private int updateTick2;





    public TileSignal(){
        if(this.worldObj != null){
            facingMeta = this.getBlockMetadata();
        }
    }

    public int getFacing() {
        return facingMeta;
    }

    public void setFacing(int facing){
        this.facingMeta = facing;
    }

    public void setType(String type) {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        this.type = type;
    }

    public String getType() {

        return this.type;
    }

    public void setSignalValue(int value){
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        worldObj.notifyBlockChange(xCoord, yCoord, zCoord, BlockIDs.signal.block);
        this.signalValue = value;
    }

    public int getSignalValue(){
        return this.signalValue;
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        Block type = getBlockType();
        if (type == BlockIDs.signal.block )
        {
            bb = AxisAlignedBB.getBoundingBox(xCoord - 18, yCoord, zCoord - 18, xCoord + 18, yCoord + 4 , zCoord + 18);
        }

        return bb;
    }

    private ItemSignal.Signals renderType = null;

    public ItemSignal.Signals getSignalType(){
        if (renderType == null){
            if(hasModel && getType() != null){
                for(ItemSignal.Signals signal : ItemSignal.Signals.values()){
                    if (signal.getLabel().equals(getType())){
                        renderType = signal;
                    }
                }
            }
        }
        return renderType;
    }


    @Override
    public void updateEntity() {
        if (worldObj.isRemote) {
            return;
        }
        TileEntity linkedSignal;
        TileEntity linkedSignal2;
        List list = null;

        switch (worldObj.getBlockMetadata(xCoord, yCoord, zCoord)) {
            case 4: {
                list = this.worldObj.getEntitiesWithinAABB(EntityRollingStock.class, AxisAlignedBB.getBoundingBox(this.xCoord + 1, this.yCoord - 1, this.zCoord, this.xCoord + 2, this.yCoord + 2, this.zCoord + 1));
                break;
            }
            case 0: {
                list = this.worldObj.getEntitiesWithinAABB(EntityRollingStock.class, AxisAlignedBB.getBoundingBox(this.xCoord - 1, this.yCoord - 1, this.zCoord, this.xCoord, this.yCoord + 1, this.zCoord + 1));
                break;
            }
            case 6: {
                list = this.worldObj.getEntitiesWithinAABB(EntityRollingStock.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord - 1, this.zCoord + 1, this.xCoord + 1, this.yCoord + 2, this.zCoord + 2));
                break;
            }
            case 2: {
                list = this.worldObj.getEntitiesWithinAABB(EntityRollingStock.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord - 1, this.zCoord - 1, this.xCoord + 1, this.yCoord + 2, this.zCoord));
                break;
            }


        }
        if (list != null) {
            for (Object o : list) {
                if (o instanceof EntityRollingStock) {

                    this.hasRollingStock = false;
                    this.setSignalValue(1);
                    linkedSignal = worldObj.getTileEntity(linkedSignal1X, linkedSignal1Y, linkedSignal1Z);
                    linkedSignal2 = worldObj.getTileEntity(linkedSignal2X, linkedSignal2Y, linkedSignal2Z);


                    if (linkedSignal instanceof TileSignal){
                        ((TileSignal) linkedSignal).hasRollingStock = true;
                    }
                }
            }
        }
        else {
         return;
        }




        updateTicks++;
        updateTick2++;


        if (updateTicks % 20 == 0) {
            updateTicks = 0;
            linkedSignal = worldObj.getTileEntity(linkedSignal1X, linkedSignal1Y, linkedSignal1Z);
            if (linkedSignal instanceof TileSignal) {

                TileSignal tile = (TileSignal) linkedSignal;
                if (isGettingPowered){
                    setSignalValue(1);
                    return;
                }

                switch (getLinkedSignalValue(linkedSignal1X, linkedSignal1Y, linkedSignal1Z)) {

                    case 0: {
                        if (tile.hasRollingStock) {
                            setSignalValue(1);
                            break;
                        }
                        setSignalValue(0);
                        break;
                    }
                    case 1: {

                        if (tile.hasRollingStock) {
                            setSignalValue(1);
                            break;
                        }
                        setSignalValue(2);
                        break;

                    }
                    case 2: {
                        if (tile.hasRollingStock) {
                            setSignalValue(1);
                            break;
                        }
                        setSignalValue(0);
                        break;
                    }

                }
            }
        }


        if (updateTick2 % 12000 == 0) {
            updateTick2 = 0;

            hasRollingStock = false;


        }



    }


    public int getLinkedSignalValue(int linkedX, int linkedY, int linkedZ){

        TileEntity tileLinkedSignal = worldObj.getTileEntity(linkedX, linkedY, linkedZ);
        if (tileLinkedSignal != null && tileLinkedSignal instanceof TileSignal ){
            int linkedSignalValue = ((TileSignal) tileLinkedSignal).getSignalValue();
           return linkedSignalValue;
        }
        return -1;


    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("type")) {
            type = nbt.getString("type");
        }

        else {
            type = ItemSignal.Signals.SPANISH_SIGNAL.getLabel();
        }
        facingMeta = nbt.getByte("facing");
        linkedSignal1X = nbt.getInteger("lS1X");
        linkedSignal1Y = nbt.getInteger("lS1Y");
        linkedSignal1Z = nbt.getInteger("lS1Z");
        linkedSignal2X = nbt.getInteger("lS2X");
        linkedSignal2Y = nbt.getInteger("lS2Y");
        linkedSignal2Z = nbt.getInteger("lS2Z");
        signalValue = nbt.getInteger("signalValue");
        hasModel = nbt.getBoolean("hasModel");
        isGettingPowered = nbt.getBoolean("isGettingPowered");
        previousRedStoneState = nbt.getBoolean("previousRedstoneState");
        idDrop = Item.getItemById(nbt.getInteger("IdDrop"));
        hasRollingStock = nbt.getBoolean("hasRollingStock");
        isLinked = nbt.getBoolean("isLinked");
        isLinked2 = nbt.getBoolean("isLinked2");

    }


    @Override
    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        nbt.setString("type", type);
        nbt.setByte("facing", (byte) facingMeta);
        nbt.setInteger("lS1X", linkedSignal1X);
        nbt.setInteger("lS1Y", linkedSignal1Y);
        nbt.setInteger("lS1Z", linkedSignal1Z);
        nbt.setInteger("lS2X", linkedSignal2X);
        nbt.setInteger("lS2Y", linkedSignal2Y);
        nbt.setInteger("lS2Z", linkedSignal2Z);
        nbt.setBoolean("isLinked", isLinked);
        nbt.setBoolean("isLinked2", isLinked2);

        nbt.setInteger("signalValue", signalValue);
        nbt.setBoolean("hasModel", hasModel);
        nbt.setBoolean("isGettingPowered", isGettingPowered);
        nbt.setBoolean("previousRedstoneState", previousRedStoneState);
        nbt.setInteger("IdDrop", Item.getIdFromItem(idDrop));
        nbt.setBoolean("hasRollingStock", hasRollingStock);
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
