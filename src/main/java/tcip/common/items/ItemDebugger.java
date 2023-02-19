package tcip.common.items;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tcip.common.library.BlockIDs;
import tcip.common.library.Info;
import tcip.common.tile.TileSignal;
import tcip.common.tile.TileSupport;
import tcip.common.tile.TileSupportGag;

import java.util.List;

public class ItemDebugger extends Item {
    public ItemDebugger() {
        super();
        maxStackSize = 1;
        setCreativeTab(null);
    }

    private boolean isAttaching = false;

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {

        if (!world.isRemote) {

            if (!(player.canCommandSenderUseCommand(2, "") && player.capabilities.isCreativeMode)) {
                player.addChatMessage(new ChatComponentText("You are not allowed to to that!"));
                return false;
            }

            Block block = world.getBlock(x, y, z);
            if (block == BlockIDs.support.block) {
                TileSupport tile = (TileSupport) world.getTileEntity(x, y, z);

                if (tile != null)
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "TileSupport"));
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "Name: " + EnumChatFormatting.WHITE + tile.getType()));
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "x: " + EnumChatFormatting.WHITE + tile.xCoord + EnumChatFormatting.GOLD + " y: " + EnumChatFormatting.WHITE + tile.yCoord + EnumChatFormatting.GOLD + " z: " + EnumChatFormatting.WHITE + tile.zCoord));
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "Meta: " + EnumChatFormatting.WHITE + tile.getBlockMetadata()));
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "LinkedX: " + EnumChatFormatting.WHITE + tile.linkedX + EnumChatFormatting.GOLD + " LinkedY: " + EnumChatFormatting.WHITE + tile.linkedY + EnumChatFormatting.GOLD + " LinkedZ: " + EnumChatFormatting.WHITE + tile.linkedZ));
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "Ballast: " + EnumChatFormatting.WHITE + Block.getBlockById(tile.getBallast()[0]).getLocalizedName() + EnumChatFormatting.GOLD + " DamageValue: " + EnumChatFormatting.WHITE + tile.getBallast()[1] + EnumChatFormatting.GOLD + " Colour: " + EnumChatFormatting.WHITE + tile.getBallast()[2]));
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "minX: " + EnumChatFormatting.WHITE + tile.getBlockBounds()[0] + EnumChatFormatting.BOLD.GOLD + "minY: " + EnumChatFormatting.WHITE + tile.getBlockBounds()[1] + EnumChatFormatting.BOLD.GOLD + "minZ: " + EnumChatFormatting.WHITE + tile.getBlockBounds()[2] + EnumChatFormatting.BOLD.GOLD + "maxX: " + EnumChatFormatting.WHITE + tile.getBlockBounds()[3] + EnumChatFormatting.BOLD.GOLD + "maxY: " + EnumChatFormatting.WHITE + tile.getBlockBounds()[4] + EnumChatFormatting.BOLD.GOLD + "maxZ: " + EnumChatFormatting.WHITE + tile.getBlockBounds()[5]));


                player.addChatMessage(new ChatComponentText(" "));
            } else if (block == BlockIDs.supportGag.block) {
                TileSupportGag tile = (TileSupportGag) world.getTileEntity(x, y, z);
                if (tile != null) {
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "TileTCGag"));
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "Name: " + EnumChatFormatting.WHITE + tile.type));
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "x: " + EnumChatFormatting.WHITE + tile.xCoord + EnumChatFormatting.GOLD + " y: " + EnumChatFormatting.WHITE + tile.yCoord + EnumChatFormatting.GOLD + " z: " + EnumChatFormatting.WHITE + tile.zCoord));
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "Meta: " + EnumChatFormatting.WHITE + tile.getBlockMetadata()));
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "OriginX: " + EnumChatFormatting.WHITE + tile.originX + EnumChatFormatting.GOLD + " OriginY: " + EnumChatFormatting.WHITE + tile.originY + EnumChatFormatting.GOLD + " OriginZ: " + EnumChatFormatting.WHITE + tile.originZ));
                    player.addChatMessage(new ChatComponentText(" "));

                }
            } else if (Loader.isModLoaded("tc") && block == BlockIDs.signal.block) {
                TileSignal tile1 = (TileSignal) world.getTileEntity(x, y, z);
                TileSignal tileTemp = null;
                if (tile1 != null) {
                    if (!tile1.isLinking) {
                        player.addChatMessage(new ChatComponentText("Attachment mode on for: " + tile1.getSignalType().getLabel() + "."));
                        tileTemp = tile1;
                        player.addChatMessage(new ChatComponentText("temp: " + tileTemp.getSignalValue()));
                        tile1.isLinking = true;
                    }

                    if (!tile1.isLinking && tile1 != tileTemp){

                    }
                    else {
                        player.addChatMessage(new ChatComponentText("Attachment reset."));
                        tile1.isLinking = false;
                        tileTemp = null;
                    }

                    /*else if (world.getTileEntity(x, y, z) != tileTemp) {
                        TileSignal tile2 = (TileSignal) world.getTileEntity(x, y, z);
                        if (!tile2.isLinking) {
                            player.addChatMessage(new ChatComponentText("test"));
                        }
                    }
                    */

                }

            }




                /*TileSignal tile = (TileSignal) world.getTileEntity(x, y, z);
                if (tile != null) {

                    tile.setSignalValue(tile.getSignalValue() + 1);
                    if (tile.getSignalValue() > 2 ){
                        tile.setSignalValue(0);
                    }
                    tile.markDirty();
                    tile.getWorldObj().markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
                }*/


            else {
                player.addChatMessage(new ChatComponentText("Not a TCIP: SupportBlock"));
                return false;
            }


        }



        return super.onItemUse(itemstack, player, world, x, y, z, par7, par8, par9, par10);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Info.modID.toLowerCase() + ":item_track_debugger");
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("\u00a77" + "Gets TileEntityData for current support block");
    }
}
