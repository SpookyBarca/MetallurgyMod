package com.ssbbpeople.metallurgy;

import com.ssbbpeople.metallurgy.init.ModBlocks;
import com.ssbbpeople.metallurgy.init.ModRecipes;
import com.ssbbpeople.metallurgy.proxy.CommonProxy;
import com.ssbbpeople.metallurgy.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	public static final CreativeTabs METALLURGY = (new CreativeTabs("metallurgy")
	{
		public ItemStack getTabIconItem()
		{
			return new ItemStack(ModBlocks.EXTRACTED_IRON_ORE);
		}
	});
			
		@Instance
		public static Main instance;
		
		@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
		public static CommonProxy proxy;
		
		@EventHandler
		public static void PreInit(FMLPreInitializationEvent event)
		{
			
		}
		
		@EventHandler
		public static void init(FMLInitializationEvent event)
		{
			ModRecipes.init();
		}
		
		@EventHandler
		public static void PostInit(FMLPostInitializationEvent event)
		{
			
		}
		
				
		
}
