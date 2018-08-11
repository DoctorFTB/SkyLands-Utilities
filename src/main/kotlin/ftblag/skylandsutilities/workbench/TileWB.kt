package ftblag.skylandsutilities.workbench

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class TileWB : TileEntity(), IInventory {

    private val inv: NonNullList<ItemStack> = NonNullList.withSize(10, ItemStack.EMPTY)

    override fun getName(): String {
        return "container"
    }

    override fun hasCustomName(): Boolean {
        return false
    }

    override fun getSizeInventory(): Int {
        return inv.size
    }

    override fun isEmpty(): Boolean {
        for (stack in inv)
            if (stack.isEmpty)
                return false
        return true
    }

    override fun getStackInSlot(index: Int): ItemStack {
        return if (index >= 0 && index < sizeInventory) inv.get(index) else ItemStack.EMPTY
    }

    override fun decrStackSize(index: Int, count: Int): ItemStack {
        return ItemStackHelper.getAndSplit(inv, index, count)
    }

    override fun removeStackFromSlot(index: Int): ItemStack {
        return ItemStackHelper.getAndRemove(inv, index)
    }

    override fun setInventorySlotContents(index: Int, stack: ItemStack) {
        if (stack.getCount() > getInventoryStackLimit())
            stack.setCount(getInventoryStackLimit())
        inv.set(index, stack)
    }

    override fun getInventoryStackLimit(): Int {
        return 64
    }

    override fun isUsableByPlayer(player: EntityPlayer): Boolean {
        return if (world.getTileEntity(pos) !== this)
            false
        else
            player.getDistanceSq(pos.x + 0.5, pos.y + 0.5, pos.z + 0.5) <= 64.0
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        super.readFromNBT(compound)
        ItemStackHelper.loadAllItems(compound, inv)
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        ItemStackHelper.saveAllItems(compound, inv)
        return super.writeToNBT(compound)
    }

    override fun openInventory(player: EntityPlayer?) {
    }

    override fun closeInventory(player: EntityPlayer?) {
    }

    override fun isItemValidForSlot(index: Int, stack: ItemStack?): Boolean {
        return false
    }

    override fun getField(id: Int): Int {
        return 0
    }

    override fun setField(id: Int, value: Int) {
    }

    override fun getFieldCount(): Int {
        return 0
    }

    override fun clear() {
        inv.clear()
    }

    override fun shouldRefresh(world: World?, pos: BlockPos?, oldState: IBlockState, newSate: IBlockState): Boolean {
        return oldState.getBlock() != newSate.getBlock()
    }

    override fun getUpdatePacket(): SPacketUpdateTileEntity? {
        val nbtTag = NBTTagCompound()
        writeToNBT(nbtTag)
        return SPacketUpdateTileEntity(pos, 1, nbtTag)
    }

    override fun onDataPacket(net: NetworkManager?, pkt: SPacketUpdateTileEntity) {
        readFromNBT(pkt.nbtCompound)
    }

    override fun getUpdateTag(): NBTTagCompound {
        val updateTag = super.getUpdateTag()
        writeToNBT(updateTag)
        return updateTag
    }
}