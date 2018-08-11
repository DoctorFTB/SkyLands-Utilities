package ftblag.skylandsutilities.workbench

import net.minecraft.inventory.Container
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack

class CraftingInventory : InventoryCrafting {

    val container: Container
    val inv: IInventory

    constructor(containerIn: Container, parent: IInventory, width: Int, height: Int) : super(containerIn, width,
                                                                                             height) {
        container = containerIn
        inv = parent
    }

    override fun markDirty() {
        inv.markDirty()
        container.onCraftMatrixChanged(this)
    }

    override fun getSizeInventory(): Int {
        return width * height
    }

    override fun getStackInSlot(i: Int): ItemStack {
        return if (i < 0 || i >= sizeInventory) ItemStack.EMPTY else inv.getStackInSlot(i)
    }

    override fun decrStackSize(i: Int, size: Int): ItemStack {
        val stack = inv.decrStackSize(i, size)
        if (stack != ItemStack.EMPTY)
            container.onCraftMatrixChanged(this)
        return stack
    }

    override fun setInventorySlotContents(index: Int, stack: ItemStack) {
        inv.setInventorySlotContents(index, stack)
        container.onCraftMatrixChanged(this)
    }
}
