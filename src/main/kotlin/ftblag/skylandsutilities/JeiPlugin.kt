package ftblag.skylandsutilities

import exnihilocreatio.compatibility.jei.barrel.compost.CompostRecipeCategory
import exnihilocreatio.compatibility.jei.hammer.HammerRecipeCategory
import exnihilocreatio.compatibility.jei.sieve.SieveRecipeCategory
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import net.blay09.mods.excompressum.compat.jei.CompressedHammerRecipeCategory
import net.minecraft.item.ItemStack

@JEIPlugin
class JeiPlugin : IModPlugin {

    override fun register(registry: IModRegistry) {
        registry.addRecipeCatalyst(ItemStack(SkylandsUtilities.skyrootSieve), SieveRecipeCategory.UID)
        registry.addRecipeCatalyst(ItemStack(SkylandsUtilities.skyrootHammer), HammerRecipeCategory.UID)
        registry.addRecipeCatalyst(ItemStack(SkylandsUtilities.holystoneHammer), HammerRecipeCategory.UID)
        registry.addRecipeCatalyst(ItemStack(SkylandsUtilities.skyrootBarrel), CompostRecipeCategory.UID)
        registry.addRecipeCatalyst(ItemStack(SkylandsUtilities.holystoneCompressedHammer), CompressedHammerRecipeCategory.UID)
        registry.addRecipeCatalyst(ItemStack(SkylandsUtilities.skyrootCompressedHammer), CompressedHammerRecipeCategory.UID)
    }
}