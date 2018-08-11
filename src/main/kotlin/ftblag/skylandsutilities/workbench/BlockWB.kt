package ftblag.skylandsutilities.workbench

import exnihilocreatio.util.Data
import exnihilocreatio.util.IHasModel
import ftblag.skylandsutilities.SkylandsUtilities
import net.minecraft.block.Block
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.InventoryHelper
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry

class BlockWB : Block, ITileEntityProvider, IHasModel {
    constructor() : super(Material.WOOD) {
        setUnlocalizedName("slu_wb")
        setRegistryName("slu_wb")
        setCreativeTab(SkylandsUtilities.tab)
        setHardness(2.5F)
        setSoundType(SoundType.WOOD)
        GameRegistry.registerTileEntity(TileWB::class.java, "slu_wbtile")
        Data.BLOCKS.add(this)
    }

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState?, player: EntityPlayer,
                                  hand: EnumHand?, facing: EnumFacing?, hitX: Float, hitY: Float,
                                  hitZ: Float): Boolean {
        if (!world.isRemote)
            player.openGui(SkylandsUtilities.instance(), 0, world, pos.x, pos.y, pos.z)
        return true
    }

    override fun createNewTileEntity(worldIn: World?, meta: Int): TileEntity {
        return TileWB()
    }

    override fun removedByPlayer(state: IBlockState, world: World, pos: BlockPos, player: EntityPlayer,
                                 willHarvest: Boolean): Boolean {
        if (!world.isRemote)
            InventoryHelper.dropInventoryItems(world, pos, world.getTileEntity(pos) as IInventory)
        return super.removedByPlayer(state, world, pos, player, willHarvest)
    }
}
