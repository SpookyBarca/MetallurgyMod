package com.ssbbpeople.metallurgy.util.handlers;

import java.awt.Container;

import com.ssbbpeople.metallurgy.blocks.ContainerGrinder;
import com.ssbbpeople.metallurgy.blocks.GuiGrinder;
import com.ssbbpeople.metallurgy.blocks.TileEntityGrinder;
import com.ssbbpeople.metallurgy.util.Reference;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler
{	
	public static final int GUI_CONTAINER = 0;

	/**
	 * gets the server's part of a Gui
	 *
	 * @return a {@link Container} for the server
	 */
	@Override
	public Container getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
		switch (ID) {
			case GUI_CONTAINER:
				return null;
		}
		return null;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public Gui getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
		switch (ID) {
			case GUI_CONTAINER:
				return null;
		}
		return null;
	}
}
