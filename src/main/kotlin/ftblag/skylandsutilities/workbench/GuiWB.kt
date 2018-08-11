package ftblag.skylandsutilities.workbench

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

class GuiWB : GuiContainer {

    constructor(player: EntityPlayer, te: TileWB) : super(ContainerWB(player, te)) {
        xSize = 176
        ySize = 166
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        fontRenderer.drawString(I18n.format("container.crafting"), 28, 6, 4210752)
        fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752)
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F)
        val x = (width - xSize) / 2
        val y = (height - ySize) / 2
        mc.renderEngine.bindTexture(ResourceLocation("minecraft", "textures/gui/container/crafting_table.png"))
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize)
    }
}