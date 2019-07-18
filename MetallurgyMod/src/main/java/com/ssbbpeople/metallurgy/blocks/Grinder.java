package com.ssbbpeople.metallurgy.blocks;

import java.util.Random;

import com.ssbbpeople.metallurgy.Main;
import com.ssbbpeople.metallurgy.init.ModBlocks;
import com.ssbbpeople.metallurgy.init.ModItems;
import com.ssbbpeople.metallurgy.util.Reference;
import com.ssbbpeople.metallurgy.util.ModEnums.ModEnums;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Grinder extends Block implements ITileEntityProvider {

	public static final PropertyDirection FACING = BlockHorizontal.FACING; 
	public static final PropertyBool BURNING = PropertyBool.create("burning");
	private static final int GuiGrinder = ModEnums.GUI_ENUM.GRINDER.getGID();
	private static final IInteractionObject TileEntityGrinder = null;
	
	public Grinder(String name) 
	{
		super(Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, false));
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.METALLURGY);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		// TODO Auto-generated constructor stub
	}
	   @Override
	    public boolean hasTileEntity(IBlockState state) {
	        return true;
	    }
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(ModBlocks.GRINDER);
	}
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) 
	{
		return new ItemStack(ModBlocks.GRINDER);
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if (worldIn.isRemote) 
		{
			return true;
		}
		TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(!(tileEntity instanceof TileEntityGrinder)) {
				return false;
			}
			playerIn.openGui(Main.instance, GuiGrinder, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
		if (!worldIn.isRemote) 
		{
			IBlockState north = worldIn.getBlockState(pos.north());
			IBlockState south = worldIn.getBlockState(pos.south());
			IBlockState west = worldIn.getBlockState(pos.west());
			IBlockState east = worldIn.getBlockState(pos.east());
			EnumFacing face = (EnumFacing)state.getValue(FACING);
			
			if (face == EnumFacing.NORTH && !south.isFullBlock()) face = EnumFacing.SOUTH;
			else if (face == EnumFacing.SOUTH && !north.isFullBlock()) face = EnumFacing.NORTH;
			else if (face == EnumFacing.WEST && !east.isFullBlock()) face = EnumFacing.EAST;
			else if (face == EnumFacing.EAST && !west.isFullBlock()) face = EnumFacing.WEST;
			worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
		}
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos) 
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if(active) worldIn.setBlockState(pos, ModBlocks.GRINDER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, true), 3);
		else worldIn.setBlockState(pos, ModBlocks.GRINDER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, false), 3);
	}
	public TileEntity createTileEntity(World worldIn, int meta) 
	{
		return new TileEntityGrinder();
	}
	
	private void setupTile(Class<? extends TileEntity> class1, TileEntitySpecialRenderer render) {
		try {
			ClientRegistry.bindTileEntitySpecialRenderer(class1, render.getClass().newInstance());
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	public void onBlockPlacedBy(World worldin, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		worldin.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) 
	{
		TileEntityGrinder tileentity = (TileEntityGrinder)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
		super.breakBlock(worldIn, pos, state);
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}
	public IBlockState withRotation(IBlockState state, Rotation rot) 
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) 
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {BURNING,FACING});
	}
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing facing = EnumFacing.getFront(meta);
		if (facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);	
	}
	
	public int getMetaFromState(IBlockState state) 
	{
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityGrinder();
	}
}
