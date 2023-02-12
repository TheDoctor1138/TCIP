package tcip.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabTCIP extends CreativeTabs {

    public CreativeTabTCIP(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Blocks.coal_block);
    }

    @Override
    public String getTranslatedTabLabel() {
        return super.getTabLabel();
    }

    @Override
    public Item getTabIconItem(){return Items.coal;}
}
