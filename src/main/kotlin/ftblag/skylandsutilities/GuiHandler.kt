package ftblag.skylandsutilities

import ftblag.skylandsutilities.workbench.ContainerWB
import ftblag.skylandsutilities.workbench.GuiWB
import ftblag.skylandsutilities.workbench.TileWB
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

class GuiHandler : IGuiHandler {

    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        if (ID == 0)
            return ContainerWB(player, world.getTileEntity(BlockPos(x, y, z)) as TileWB)
        return null
    }

    override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        if (ID == 0)
            return GuiWB(player, world.getTileEntity(BlockPos(x, y, z)) as TileWB)
        return null
    }
}