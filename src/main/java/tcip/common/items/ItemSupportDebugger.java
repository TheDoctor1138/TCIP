package tcip.common.items;

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
import tcip.common.tile.TileSupport;
import tcip.common.tile.TileSupportGag;

import java.util.List;

public class ItemSupportDebugger extends Item {
    public ItemSupportDebugger() {
        super();
        maxStackSize = 1;
        setCreativeTab(null);
    }


    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {

        if (!world.isRemote) {

            if (!(player.canCommandSenderUseCommand(2, "") && player.capabilities.isCreativeMode)){
                player.addChatMessage(new ChatComponentText("You are not allowed to to that!"));
                return false;
            }

            Block block = world.getBlock(x, y, z);
            if (block == BlockIDs.support.block){
                TileSupport tile = (TileSupport) world.getTileEntity(x, y, z);

                if (tile != null)
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "TileSupport"));
                player.addChatMessage(new ChatComponentText( EnumChatFormatting.BOLD.GOLD + "Name: " +  EnumChatFormatting.WHITE + tile.getType()));
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "x: "    +  EnumChatFormatting.WHITE +  tile.xCoord + EnumChatFormatting.GOLD +  " y: " +  EnumChatFormatting.WHITE +tile.yCoord +  EnumChatFormatting.GOLD + " z: "+  EnumChatFormatting.WHITE + tile.zCoord));
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "Meta: " +  EnumChatFormatting.WHITE + tile.getBlockMetadata()));
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "LinkedX: "    +  EnumChatFormatting.WHITE +  tile.linkedX + EnumChatFormatting.GOLD +  " LinkedY: " +  EnumChatFormatting.WHITE +tile.linkedY +  EnumChatFormatting.GOLD + " LinkedZ: "+  EnumChatFormatting.WHITE + tile.linkedZ));
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "Ballast: "    +  EnumChatFormatting.WHITE +  Block.getBlockById(tile.getBallast()[0]).getLocalizedName() + EnumChatFormatting.GOLD +  " DamageValue: " +  EnumChatFormatting.WHITE +tile.getBallast()[1] +  EnumChatFormatting.GOLD + " Colour: "+  EnumChatFormatting.WHITE + tile.getBallast()[2]));
                player.addChatMessage(new ChatComponentText(" "));
            }
            else  if (block == BlockIDs.supportGag.block){
               TileSupportGag tile = (TileSupportGag) world.getTileEntity(x, y, z);
                if (tile != null) {
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "TileTCGag"));
                    player.addChatMessage(new ChatComponentText( EnumChatFormatting.BOLD.GOLD + "Name: " +  EnumChatFormatting.WHITE + tile.type));
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "x: "    +  EnumChatFormatting.WHITE +  tile.xCoord + EnumChatFormatting.GOLD +  " y: " +  EnumChatFormatting.WHITE + tile.yCoord +  EnumChatFormatting.GOLD + " z: "+  EnumChatFormatting.WHITE + tile.zCoord));
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "Meta: " +  EnumChatFormatting.WHITE + tile.getBlockMetadata()));
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.GOLD + "OriginX: "    +  EnumChatFormatting.WHITE +  tile.originX + EnumChatFormatting.GOLD +  " OriginY: " +  EnumChatFormatting.WHITE + tile.originY +  EnumChatFormatting.GOLD + " OriginZ: "+  EnumChatFormatting.WHITE + tile.originZ));
                    player.addChatMessage(new ChatComponentText(" "));

                }
            }


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
