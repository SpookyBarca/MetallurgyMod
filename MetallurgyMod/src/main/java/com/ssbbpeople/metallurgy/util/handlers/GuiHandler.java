package com.ssbbpeople.metallurgy.util.handlers;

import com.ssbbpeople.metallurgy.blocks.ContainerGrinder;
import com.ssbbpeople.metallurgy.blocks.GuiGrinder;
import com.ssbbpeople.metallurgy.blocks.TileEntityGrinder;
import com.ssbbpeople.metallurgy.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_GRINDER)
		{
			return new ContainerGrinder(player.inventory, (TileEntityGrinder)world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_GRINDER)
		{
			return new GuiGrinder(player.inventory, (TileEntityGrinder)world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}
	
	public static void registerGuis()
	{
		
	}
}
