package ftblag.skylandsutilities

import com.gildedgames.aether.common.blocks.BlocksAether
import com.gildedgames.aether.common.items.ItemsAether
import exnihilocreatio.blocks.BlockBarrel
import exnihilocreatio.blocks.BlockSieve
import exnihilocreatio.items.tools.CrookBase
import exnihilocreatio.items.tools.HammerBase
import exnihilocreatio.util.Data
import exnihilocreatio.util.IHasModel
import net.blay09.mods.excompressum.item.ItemCompressedCrook
import net.blay09.mods.excompressum.item.ItemCompressedHammer
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraft.item.ItemShears

@Mod(modid = SkylandsUtilities.MODID, name = "Skylands Utilities", version = "@VERSION@", dependencies = "required-after:exnihilocreatio;required-after:aether@[1.12.2-1.0.8,);required-after:excompressum;")
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
    val holystoneCrook = CrookBase("holystone_crook", 128).setCreativeTab(tab)
    val skyrootHammer = HammerBase("skyroot_hammer", 64, Item.ToolMaterial.WOOD).setCreativeTab(tab)
    val holystoneHammer = HammerBase("holystone_hammer", 128, Item.ToolMaterial.STONE).setCreativeTab(tab)
    val holystoneStick = HolyStick()
    val skyrootBarrel = BlockBarrel(0, Material.WOOD).setUnlocalizedName("skyroot_barrel")
    val holystoneShears = CustShears("holystone_shears", 128)
    val skyrootShears = CustShears("skyroot_shears", 64)
    val holystoneCompressedCrook = CustComprCrook("holystone_compressed_crook", 1152)
    val skyrootCompressedCrook = CustComprCrook("skyroot_compressed_crook", 576)
    val holystoneCompressedHammer = CustomComprHammer("holystone_compressed_hammer", Item.ToolMaterial.STONE, 1152)
    val skyrootCompressedHammer = CustomComprHammer("skyroot_compressed_hammer", Item.ToolMaterial.WOOD, 576)

    class HolyStick : Item, IHasModel {
        constructor() {
            setCreativeTab(tab)
            setRegistryName("holystone_stick")
            setUnlocalizedName("holystone_stick")
            Data.ITEMS.add(this)
        }
    }

    class CustShears : ItemShears, IHasModel {
        constructor(name : String, dmg : Int) {
            setCreativeTab(tab)
            setRegistryName(name)
            setUnlocalizedName(name)
            setMaxDamage(dmg)
            Data.ITEMS.add(this)
        }
    }

    class CustComprCrook : ItemCompressedCrook, IHasModel {
        constructor(name : String, dmg : Int) {
            setCreativeTab(tab)
            setRegistryName(name)
            setUnlocalizedName(name)
            setMaxDamage(dmg)
            Data.ITEMS.add(this)
        }
    }

    class CustomComprHammer : ItemCompressedHammer, IHasModel {
        constructor(name : String, material : ToolMaterial, dmg : Int) : super(material, "") {
            setCreativeTab(tab)
            setRegistryName(name)
            setUnlocalizedName(name)
            setMaxDamage(dmg)
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

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "holy_crook"), null, ItemStack(holystoneCrook),
                "XX ", " X ", " X ", 'X', holystoneStick)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "holy_hammer"), null, ItemStack(holystoneHammer),
                " X ", " YX", "Y  ", 'X', BlocksAether.holystone, 'Y', holystoneStick)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "holy_stick"), null, ItemStack(holystoneStick),
                "X", "X", 'X', BlocksAether.holystone)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "skyroot_barrel"), null, ItemStack(skyrootBarrel),
                "P P", "P P", "PSP", 'P', BlocksAether.skyroot_planks, 'S', BlocksAether.skyroot_slab)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "holy_shears"), null, ItemStack(holystoneShears),
                " X", "X ", 'X', BlocksAether.holystone)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "skyroot_shears"), null, ItemStack(skyrootShears),
                " X", "X ", 'X', BlocksAether.skyroot_planks)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "holy_compr_crook"), null, ItemStack(holystoneCompressedCrook),
                "XXX", "XXX", "XXX", 'X', holystoneCrook)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "skyroot_compr_crook"), null, ItemStack(skyrootCompressedCrook),
                "XXX", "XXX", "XXX", 'X', skyrootCrook)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "holy_compr_hammer"), null, ItemStack(holystoneCompressedHammer),
                "XXX", "XXX", "XXX", 'X', holystoneHammer)

        GameRegistry.addShapedRecipe(ResourceLocation(MODID, "skyroot_compr_hammer"), null, ItemStack(skyrootCompressedHammer),
                "XXX", "XXX", "XXX", 'X', skyrootHammer)
    }
}