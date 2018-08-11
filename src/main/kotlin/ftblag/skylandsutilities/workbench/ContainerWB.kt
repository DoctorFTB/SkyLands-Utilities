package ftblag.skylandsutilities.workbench

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.*
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.IRecipe

class ContainerWB(player: EntityPlayer, private val te: TileWB) : Container() {
    val crafter: CraftingInventory
    val craftResult: InventoryCraftResult
    var currentRecipe: IRecipe? = null

    init {

        craftResult = InventoryCraftResult()
        crafter = CraftingInventory(this, te, 3, 3)

        addSlotToContainer(SlotCrafting(player, crafter, craftResult, 0, 124, 35))

        for (i in 0 until 3)
            for (j in 0 until 3)
                addSlotToContainer(Slot(crafter, j + i * 3, 30 + j * 18, 17 + i * 18))

        for (k in 0 until 3)
            for (i1 in 0 until 9)
                addSlotToContainer(Slot(player.inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18))

        for (l in 0 until 9)
            addSlotToContainer(Slot(player.inventory, l, 8 + l * 18, 142))
        onCraftMatrixChanged(crafter)
    }

    override fun onCraftMatrixChanged(inventoryIn: IInventory?) {
        if (currentRecipe == null || !currentRecipe!!.matches(crafter, te.world))
            currentRecipe = CraftingManager.findMatchingRecipe(crafter, te.world)
        if (currentRecipe == null) {
            craftResult.setInventorySlotContents(0, ItemStack.EMPTY)
            return
        }
        val stack = if (currentRecipe != null) currentRecipe!!.getCraftingResult(crafter) else ItemStack.EMPTY
        if (stack == ItemStack.EMPTY) {
            craftResult.setInventorySlotContents(0, ItemStack.EMPTY)
            return
        }
        craftResult.setInventorySlotContents(0, stack.copy())
    }

    override fun canInteractWith(playerIn: EntityPlayer): Boolean {
        return te.isUsableByPlayer(playerIn)
    }

    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var itemstack = ItemStack.EMPTY
        val slot = inventorySlots[index]

        if (slot != null && slot.hasStack) {
            val itemstack1 = slot.stack
            itemstack = itemstack1.copy()

            if (index == 0) {
                itemstack1.item.onCreated(itemstack1, te.world, playerIn)

                if (!mergeItemStack(itemstack1, 10, 46, true))
                    return ItemStack.EMPTY

                slot.onSlotChange(itemstack1, itemstack)
            } else if (index >= 10 && index < 37) {
                if (!mergeItemStack(itemstack1, 37, 46, false))
                    return ItemStack.EMPTY
            } else if (index >= 37 && index < 46) {
                if (!mergeItemStack(itemstack1, 10, 37, false))
                    return ItemStack.EMPTY
            } else if (!mergeItemStack(itemstack1, 10, 46, false))
                return ItemStack.EMPTY

            if (itemstack1.isEmpty)
                slot.putStack(ItemStack.EMPTY)
            else
                slot.onSlotChanged()

            if (itemstack1.count == itemstack.count)
                return ItemStack.EMPTY

            val itemstack2 = slot.onTake(playerIn, itemstack1)

            if (index == 0)
                playerIn.dropItem(itemstack2, false)
        }

        return itemstack
    }

    override fun canMergeSlot(stack: ItemStack, slotIn: Slot): Boolean {
        return slotIn.inventory !== craftResult && super.canMergeSlot(stack, slotIn)
    }
}
