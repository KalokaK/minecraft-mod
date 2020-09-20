package mymod

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
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
import java.lang.NullPointerException

val estusSettings = Item.Settings().group(ItemGroup.FOOD).maxCount(10)
val ESTUS_FLASK = EstusFlask(estusSettings)

class EstusFlask(settings: Settings): Item(settings) {
    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        return super.use(world, user, hand)
    }
    override fun finishUsing(stack: ItemStack?, world: World?, user: LivingEntity?): ItemStack {
        println("use on relaease estus is being called")
        user?.heal(10.0F)
        if (user is PlayerEntity) {
            (user as PlayerEntity).itemCooldownManager.set(this, 20)
        }

        return super.finishUsing(stack, world, user)
    }
    override fun isFood(): Boolean {
        return false
    }
    override fun isUsedOnRelease(stack: ItemStack?): Boolean {
        return true
    }
}

fun registerItems() {
    Registry.register(Registry.ITEM, Identifier("mymod", "estus_flask"), ESTUS_FLASK)
}