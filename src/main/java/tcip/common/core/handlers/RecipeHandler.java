package tcip.common.core.handlers;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tcip.common.library.BlockIDs;

import java.util.ArrayList;
import java.util.List;

public class RecipeHandler {

    private static ArrayList<ItemStack> multiNameOreDict(String ... names){
        ArrayList<ItemStack> entries = new ArrayList<ItemStack>();
        for (String name : names){
            entries.addAll(OreDictionary.getOres(name));

        }
        return entries;
    }

    public static void initBlockRecipes(){


        /**Block Recipes*/
        GameRegistry.addShapelessRecipe(new ItemStack(BlockIDs.asphalt.block,  8), Items.coal,Items.coal,Items.coal,Items.coal, new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.cobblestone));
        GameRegistry.addRecipe(new ItemStack(BlockIDs.asphaltSlab.block, 6), "BBB", "   ", "   ", Character.valueOf('B'), new ItemStack(BlockIDs.asphalt.block));
        GameRegistry.addRecipe(new ItemStack(BlockIDs.asphaltSlab.block, 6), "   ", "BBB", "   ", Character.valueOf('B'), new ItemStack(BlockIDs.asphalt.block));
        GameRegistry.addRecipe(new ItemStack(BlockIDs.asphaltSlab.block, 6), "   ", "   ", "BBB", Character.valueOf('B'), new ItemStack(BlockIDs.asphalt.block));
        GameRegistry.addRecipe(new ItemStack(BlockIDs.asphaltStairs.block, 4), "B  ", "BB ", "BBB", Character.valueOf('B'), new ItemStack(BlockIDs.asphalt.block));
        GameRegistry.addRecipe(new ItemStack(BlockIDs.asphaltStairs.block, 4), "  B", " BB", "BBB", Character.valueOf('B'), new ItemStack(BlockIDs.asphalt.block));

        if (Loader.isModLoaded("tc")) {

            GameRegistry.addRecipe(new ItemStack(BlockIDs.ballastSlab.block, 6), "BBB", "   ", "   ", Character.valueOf('B'), new ItemStack(train.common.library.BlockIDs.oreTC.block, 1, 3));
            GameRegistry.addRecipe(new ItemStack(BlockIDs.ballastSlab.block, 6), "   ", "BBB", "   ", Character.valueOf('B'), new ItemStack(train.common.library.BlockIDs.oreTC.block, 1, 3));
            GameRegistry.addRecipe(new ItemStack(BlockIDs.ballastSlab.block, 6), "   ", "   ", "BBB", Character.valueOf('B'), new ItemStack(train.common.library.BlockIDs.oreTC.block, 1, 3));
            GameRegistry.addRecipe(new ItemStack(BlockIDs.ballastStairs.block, 4), "B  ", "BB ", "BBB", Character.valueOf('B'), new ItemStack(train.common.library.BlockIDs.oreTC.block, 1, 3));
            GameRegistry.addRecipe(new ItemStack(BlockIDs.ballastStairs.block, 4), "  B", " BB", "BBB", Character.valueOf('B'), new ItemStack(train.common.library.BlockIDs.oreTC.block, 1, 3));
        }
    }

    public static void initItemRecipes(){

        /**Item Recipes*/
        List<ItemStack> coal = new ArrayList<ItemStack>();
        coal.add(new ItemStack(Items.coal));
        coal.addAll(OreDictionary.getOres("coal"));

    }

    public static void initSmeltingRecipes(){
        /**Smleting recipes*/
    }

    public static void addDictRecipe(ItemStack stack, Object... obj) {
        ShapedOreRecipe recipe = new ShapedOreRecipe(stack, obj);
        GameRegistry.addRecipe(recipe);
    }
}
