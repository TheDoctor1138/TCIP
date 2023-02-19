package tcip.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockMushroom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tcip.common.library.BlockIDs;
import tcip.common.library.ItemIDs;
import tcip.common.tile.TileSignal;

public class ItemSignal extends Item {

    private final Signals signalType;

    public enum Signals {
        SPANISH_SIGNAL("SPANISH_SIGNAL",  ItemIDs.itemSpanishSignal);



        private final String label;
        private ItemIDs item;

        Signals(String label, ItemIDs item) {
            this.label = label;
            this.item  = item;
        }

        public String getLabel() {
            return label;
        }
        public ItemIDs getItem() {
            return this.item;
        }


    }

    ItemSignal(Signals signalType) {
        this.signalType = signalType;
    }

    public boolean canPlaceBlock(EntityPlayer player, World world, int x, int y, int z) {
        Block block = world.getBlock(x, y- 1, z);
        if (player != null && (!player.canPlayerEdit(x, y - 1, z, 0, player.getCurrentEquippedItem()) ||
                !player.canPlayerEdit(x, y, z, 0, player.getCurrentEquippedItem()))){
            return false;
        }

        return canBeReplaced(world, x, y, z) && (World.doesBlockHaveSolidTopSurface(world ,x, y - 1, z));
    }

    private boolean canBeReplaced(World world, int x, int y, int z){
        Block block = world.getBlock(x, y, z);
        return block == null || block.isReplaceable(world, x, y, z) || block instanceof BlockFlower
                || block == Blocks.double_plant || block instanceof BlockMushroom;
    }

    private void placeSignal(World world, int x, int y, int z, Block block, int metadata){
        Block removed = world.getBlock(x, y, z);
        if(removed != null){
            removed.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        }
        world.setBlock(x, y , z, block, metadata, 3);
    }
    public int getPlacementHeight( World world, int x, int y, int z )
    {
        if(canBeReplaced(world, x, y, z)){
            y--;
        }
        return y;
    }


    private boolean SpanishSignal( EntityPlayer player, World world, int x, int y, int z, int l, Signals signalType) {
        /** Switch rail */
        placeSignal(world,x, y + 1, z, BlockIDs.signal.block, l);
        TileSignal signal = (TileSignal) world.getTileEntity(x, y + 1, z);

        signal.setFacing(l);
        signal.setSignalValue(0);
        signal.setType(signalType.getLabel());
        signal.hasModel = true;
        signal.idDrop = this.signalType.getItem().item;
        signal.linkedSignal1X = x;
        signal.linkedSignal1Y = y + 1;
        signal.linkedSignal1Z = z - 2;
        signal.linkedSignal2X = x;
        signal.linkedSignal2Y = y + 1;
        signal.linkedSignal2Z = z - 4;


        player.addChatMessage(new ChatComponentText ("x:" + signal.linkedSignal1X + " y: " + signal.linkedSignal1Y + " z: " + signal.linkedSignal1Z ));




/**
 Position
 initial state (signal colour)
 direction
 type
 redstonestate
 idDrop
 hasModel
 hascustomblockbounds
 */ return true;
    }





    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10){
    if (!world.isRemote){

        y = getPlacementHeight(world, x, y, z);
        int l = MathHelper.floor_double((player.rotationYaw * 8f) / 360.0F + 0.5D ) & 7;
        player.addChatMessage(new ChatComponentText("l: " + l));
        if (signalType == Signals.SPANISH_SIGNAL){

            if (!SpanishSignal( player, world, x, y, z, l, signalType))
                return false;

            if (player == null || player.capabilities.isCreativeMode) {
                --itemStack.stackSize;
            }
            return true;
        }
        return true;
    }
   return false;

}
}
