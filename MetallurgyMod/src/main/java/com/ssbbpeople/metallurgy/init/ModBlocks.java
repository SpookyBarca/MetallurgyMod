package com.ssbbpeople.metallurgy.init;

import java.util.ArrayList;
import java.util.List;

import com.ssbbpeople.metallurgy.blocks.BlockBase;
import com.ssbbpeople.metallurgy.blocks.ExtractedIronOre;
import com.ssbbpeople.metallurgy.blocks.Grinder;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks 
	{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block EXTRACTED_IRON_ORE = new ExtractedIronOre("extracted_iron_ore", Material.IRON);
	public static final Block GRINDER = new Grinder("grinder");
	
	}


