package mymod

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.TypedActionResult
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext

val estusSettings = Item.Settings().group(ItemGroup.FOOD).maxCount(10).food(FoodComponent.Builder().alwaysEdible().hunger(0).build())
val ESTUS_FLASK = EstusFlask(estusSettings)

class EstusFlask(settings: Settings): Item(settings) {
    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        user?.heal(10.0F)
        user?.itemCooldownManager?.set(this, 20)
        user!!.getStackInHand(hand).decrement(1)
        return super.use(world, user, hand)
    }

    override fun isUsedOnRelease(stack: ItemStack?): Boolean {
        return true
    }
}

fun registerItems() {
    Registry.register(Registry.ITEM, Identifier("mymod", "estus_flask"), ESTUS_FLASK)
}