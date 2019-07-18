package com.ssbbpeople.metallurgy.blocks;

import com.ssbbpeople.metallurgy.blocks.slots.SlotGrinderFuel;
import com.ssbbpeople.metallurgy.blocks.slots.SlotGrinderOutput;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerGrinder extends Container
{
	private final IInventory tileEntityGrinder;
	TileEntityGrinder tileentity;
    private IItemHandler InvHandler;
	private int cookTime, totalCookTime, ovenBurnTime, currentItemBurnTime;
	
	public ContainerGrinder(InventoryPlayer playerInventory, IInventory furnaceInventory, TileEntityGrinder containerTileEntity) 
	{
		this.tileentity = containerTileEntity;
		this.tileEntityGrinder = furnaceInventory;
		this.addSlotToContainer(new Slot(furnaceInventory, 0, 56, 17));
		this.addSlotToContainer(new SlotGrinderFuel(furnaceInventory, 1, 56, 53));
		this.addSlotToContainer(new SlotGrinderOutput(playerInventory.player, furnaceInventory, 2, 116, 35));
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int k = 0; k < 9; k++)
		{
			this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
		}
	}
	
	public ContainerGrinder(IInventory playerInventory, IInventory furnaceInventory, TileEntityGrinder containerTileEntity) {
		this.tileentity = containerTileEntity;
		this.tileEntityGrinder = furnaceInventory;
        this.InvHandler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
	}

	@Override
	public void addListener(IContainerListener listener) 
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileEntityGrinder);
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.cookTime != this.tileEntityGrinder.getField(2))
            {
                icontainerlistener.sendWindowProperty(this, 2, this.tileEntityGrinder.getField(2));
            }

            if (this.ovenBurnTime != this.tileEntityGrinder.getField(0))
            {
                icontainerlistener.sendWindowProperty(this, 0, this.tileEntityGrinder.getField(0));
            }

            if (this.currentItemBurnTime != this.tileEntityGrinder.getField(1))
            {
                icontainerlistener.sendWindowProperty(this, 1, this.tileEntityGrinder.getField(1));
            }

            if (this.totalCookTime != this.tileEntityGrinder.getField(3))
            {
                icontainerlistener.sendWindowProperty(this, 3, this.tileEntityGrinder.getField(3));
            }
        }

        this.cookTime = this.tileEntityGrinder.getField(2);
        this.ovenBurnTime = this.tileEntityGrinder.getField(0);
        this.currentItemBurnTime = this.tileEntityGrinder.getField(1);
        this.totalCookTime = this.tileEntityGrinder.getField(3);
	}
	
	@Override
	public void updateProgressBar(int id, int data) 
	{
		this.tileEntityGrinder.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return  this.tileEntityGrinder.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) 
	{
		ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (!GrinderRecipes.instance().getCookingResult(itemstack1).isEmpty())
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (TileEntityGrinder.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}
