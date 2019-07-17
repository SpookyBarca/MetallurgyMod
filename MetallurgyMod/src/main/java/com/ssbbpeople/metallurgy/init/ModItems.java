package com.ssbbpeople.metallurgy.init;

import java.util.ArrayList;
import java.util.List;

import com.ssbbpeople.metallurgy.items.Chisel;
import com.ssbbpeople.metallurgy.items.ItemBase;

import net.minecraft.item.Item;

public class ModItems 
{

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item CHISEL = new Chisel("chisel");
	public static final Item PULVERIZED_IRON = new Chisel("pulverized_iron");
	
	
}
