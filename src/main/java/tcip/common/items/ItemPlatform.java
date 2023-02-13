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
import tcip.common.tile.TileSupport;

public class ItemPlatform extends Item {

    private final PlatformTypes type;
    PlatformTypes tempType;

    public enum PlatformTypes{
        STRAIGHT("STRAIGHT", "STRAIGHT", ItemIDs.PlatformStraight, "1x1");

        private final String label;
        private final String type;
        private final ItemIDs item;
        private final String tooltip;

        PlatformTypes(String label, String type, ItemIDs item, String tooltip){
            this.label = label;
            this.type = type;
            this.item = item;
            this.tooltip = tooltip;
        }

        public String getLabel() {
            return this.label;
        }

        public String getType() {
            return this.type;
        }

        public ItemIDs getItem() {return this.item;}

        public String getTooltip() {
            return this.tooltip;
        }

    }
    public ItemPlatform(PlatformTypes t){
        this.type = t;
    }

    private boolean canBeReplaced(World world, int x, int y, int z){
        Block block = world.getBlock(x, y, z);
        return block == null || block.isReplaceable(world, x, y, z) || block instanceof BlockFlower
                || block == Blocks.double_plant || block instanceof BlockMushroom;
    }

    private boolean canPlaceBlock(EntityPlayer player, World world, int x, int y, int z) {
        return canBeReplaced(world, x, y, z);
    }


    private void placeBlock(World world, int x, int y, int z, Block block, int metadata){
        Block removed = world.getBlock(x, y, z);
        if(removed != null){
            removed.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        }
        world.setBlock(x, y, z, block, metadata, 3);
    }

    public int getPlacementHeight( World world, int x, int y, int z )
    {
        if(canBeReplaced(world, x, y, z)){
            y--;
        }
        return y;
    }


    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (!world.isRemote) {

            y = getPlacementHeight(world, x, y, z);
            int l = MathHelper.floor_double((player!=null?player.rotationYaw:par10) * 4.0F / 360.0F) & 7;

            if (type == PlatformTypes.STRAIGHT){

                if (!straightPlatform(player, world, x, y, z, l, type))
                    return false;

                if (player == null || player.capabilities.isCreativeMode) {
                    --itemstack.stackSize;
                }
                return true;
            }



        }

        return true;
    }


    /**Placement methods*/

    private boolean straightPlatform(EntityPlayer player, World world, int x, int y, int z, int l, PlatformTypes type){
        if (!canPlaceBlock(player, world, x, y + 1, z)) {
            return false;
        }
        player.addChatMessage(new ChatComponentText("yes"));
        placeBlock(world, x, y + 1, z, BlockIDs.support.block, l);
        TileSupport support = (TileSupport) world.getTileEntity(x, y + 1, z);
        support.setType(type.getLabel());
        support.idDrop = this.type.getItem().item;
        support.setBlockBounds(0.25f,0.0f,0.25f,0.75f,1.0f,0.75f);
        support.hasCustomBlockBounds = true;
        return true;
    }



}
