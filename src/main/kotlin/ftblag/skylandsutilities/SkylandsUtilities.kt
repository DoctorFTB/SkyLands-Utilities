package ftblag.skylandsutilities

import com.gildedgames.aether.common.blocks.BlocksAether
import com.gildedgames.aether.common.items.ItemsAether
import com.gildedgames.orbis_api.core.GameRegistrar
import exnihilocreatio.blocks.BlockBarrel
import exnihilocreatio.blocks.BlockSieve
import exnihilocreatio.items.tools.CrookBase
import exnihilocreatio.items.tools.HammerBase
import exnihilocreatio.util.Data
import exnihilocreatio.util.IHasModel
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.common.ForgeHooks.getRegistryName
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader




@Mod(modid = SkylandsUtilities.MODID, name = "Skylands Utilities", version = "@VERSION@", dependencies = "required-after:exnihilocreatio@[1.12-0.2.4,);required-after:aether@[1.12.2-1.0.8,);")
object SkylandsUtilities {
    const val MODID = "skylandsutilities"

    val tab = object : CreativeTabs(MODID) {
        @SideOnly(Side.CLIENT)
        override fun getTabIconItem(): ItemStack {
            return ItemStack(skyrootSieve)
        }
    }

    val skyrootSieve : Block = BlockSieve().setUnlocalizedName("skyroot_sieve").setCreativeTab(tab)
    val skyrootCrook = CrookBase("skyroot_crook", 64).setCreativeTab(tab)
    val holystoneСrook = CrookBase("holystone_crook", 128).setCreativeTab(tab)
    val skyrootHammer = HammerBase("skyroot_hammer", 64, Item.ToolMaterial.WOOD).setCreativeTab(tab)
    val holystoneHammer = HammerBase("holystone_hammer", 128, Item.ToolMaterial.STONE).setCreativeTab(tab)
    val holystoneStick = HolyStick()
    val skyrootBarrel = BlockBarrel(0, Material.WOOD).setUnlocalizedName("skyroot_barrel")

    class HolyStick : Item, IHasModel{
        constructor() {
            setCreativeTab(tab)
            setRegistryName("holystone_stick")
            setUnlocalizedName("holystone_stick")
            Data.ITEMS.add(this)
        }
    }

    @JvmStatic
    @Mod.InstanceFactory
    fun instance() = SkylandsUtilities

    @Mod.EventHandler
    fun init(e : FMLInitializationEvent) {
        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "sieve"), null, ItemStack(skyrootSieve),
                "X X", "XYX", "Z Z", 'X', BlocksAether.skyroot_planks, 'Y', BlocksAether.skyroot_slab, 'Z', ItemsAether.skyroot_stick)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "sky_crook"), null, ItemStack(skyrootCrook),
                "XX ", " X ", " X ", 'X', ItemsAether.skyroot_stick)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "sky_hammer"), null, ItemStack(skyrootHammer),
                " X ", " YX", "Y  ", 'X', BlocksAether.skyroot_planks, 'Y', ItemsAether.skyroot_stick)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "holy_crook"), null, ItemStack(holystoneСrook),
                "XX ", " X ", " X ", 'X', holystoneStick)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "holy_hammer"), null, ItemStack(holystoneHammer),
                " X ", " YX", "Y  ", 'X', BlocksAether.holystone, 'Y', holystoneStick)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "holy_stick"), null, ItemStack(holystoneStick),
                "X", "X", 'X', BlocksAether.holystone)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "skyroot_barrel"), null, ItemStack(skyrootBarrel),
                "P P", "P P", "PSP", 'P', BlocksAether.skyroot_planks, 'S', BlocksAether.skyroot_slab)
    }
}