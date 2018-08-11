package ftblag.skylandsutilities.workbench

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.block.model.ItemCameraTransforms
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.init.Blocks
import net.minecraftforge.fml.relauncher.Side.CLIENT
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(CLIENT)
class RenderWB : TileEntitySpecialRenderer<TileWB>() {
    val mc = Minecraft.getMinecraft()

    override fun render(te: TileWB, x1: Double, y1: Double, z1: Double, partialTicks: Float, d: Int, a: Float) {
        if (te.world.getBlockState(te.pos.up()).block !== Blocks.AIR)
            return
        for (i in 0 until te.sizeInventory) {
            val stack = te.getStackInSlot(i)
            if (!stack.isEmpty) {
                GlStateManager.pushMatrix()
                setLightmapDisabled(true)
                val x = x1 + Math.rint(100.0 * (0.67 - i % 3 * 0.19)) / 100.0
                val y = y1 + 1.02
                val z = z1 + Math.rint(100.0 * (0.67 - i / 3 * 0.19)) / 100.0
                GlStateManager.translate(x, y, z)
                GlStateManager.scale(0.65, 0.65, 0.65)
                val time = Minecraft.getSystemTime() / 800.0
                GlStateManager.translate(0.0, Math.sin(time % (2 * Math.PI)) * 0.065, 0.0)
                GlStateManager.rotate((time * 40.0 % 360).toFloat(), 0f, 1f, 0f)
                mc.renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND)
                GlStateManager.popMatrix()
            }
        }
    }
}