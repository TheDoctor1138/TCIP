package tcip.common.core;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import tcip.common.tile.TileSignal;
import tcip.common.tile.TileSupport;
import tcip.common.tile.TileSupportGag;


public class CommonProxy implements IGuiHandler {
	public static boolean debug = false;

	public void throwAlphaException() {
		throw new IllegalStateException("You're trying to use a Traincraft alpha-version past its expiry date. Download a release-build at https://minecraft.curseforge.com/projects/traincraft.");
	}

	public void setKeyBinding(String name, int value) {}

	public void registerRenderInformation() {}

	public void registerEvents(FMLPreInitializationEvent event){

	}

	public void registerEvent(Object o){
		FMLCommonHandler.instance().bus().register(o);
		MinecraftForge.EVENT_BUS.register(o);
	}

	public void registerTileEntities(){
		GameRegistry.registerTileEntity(TileSupport.class, "TileSupport");
		GameRegistry.registerTileEntity(TileSupportGag.class, "TileSupportGag");
		GameRegistry.registerTileEntity(TileSignal.class, "TileSignal");

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	public static Entity getEntity(World world, int entityId) {
		if ((world != null) && (world instanceof WorldServer)) {
			return world.getEntityByID(entityId);
		}
		return null;
	}


	public Minecraft getClientInstance() {
		return FMLClientHandler.instance().getClient();
	}

	public Minecraft getMinecraft() {
		return null;
	}


	public void doNEICheck(ItemStack stack) {}

	public EntityPlayer getPlayer() {
		return null;
	}

	public void setHook() {}
}