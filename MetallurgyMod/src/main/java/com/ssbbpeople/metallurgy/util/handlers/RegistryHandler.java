package com.ssbbpeople.metallurgy.util.handlers;

import com.ssbbpeople.metallurgy.Main;
import com.ssbbpeople.metallurgy.blocks.ContainerGrinder;
import com.ssbbpeople.metallurgy.blocks.TileEntityGrinder;
import com.ssbbpeople.metallurgy.init.ModBlocks;
import com.ssbbpeople.metallurgy.init.ModItems;
import com.ssbbpeople.metallurgy.util.IHasModel;
import com.ssbbpeople.metallurgy.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.crash.CrashReport;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ModItems.ITEMS) 
		{
			if(item instanceof IHasModel) 
			{
				((IHasModel)item).registerModels();
			}
		}
		for(Block block : ModBlocks.BLOCKS) 
		{
			if(block instanceof IHasModel) 
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	public static void initRegistries() 
   {
    	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
   }
	public static void registerTileEntities() 
   {
	   registerTileEntity(TileEntityGrinder.class, "_GrinderContainer");
   }

	private static void registerTileEntity(Class<TileEntityGrinder> TileEntityGrinder, String string) {
		// TODO Auto-generated method stub
		try {
			GameRegistry.registerTileEntity(TileEntityGrinder,  new ResourceLocation(Reference.MOD_ID, "GUI_GRINDER"));
		} catch (final Exception ex) {
            CrashReport crashReport = new CrashReport("Error registering Tile Entity" + TileEntityGrinder.getSimpleName(), ex);
            crashReport.makeCategory("Registering Tile Entity");
            throw new ReportedException(crashReport);
		}
}
}
