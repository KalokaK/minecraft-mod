package mymod

import net.minecraft.advancement.criterion.Criteria
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsage
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.TypedActionResult
import net.minecraft.util.UseAction
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

val estusSettings = Item.Settings().group(ItemGroup.COMBAT).maxCount(10)
val ESTUS_FLASK = EstusFlask(estusSettings)

class EstusFlask(settings: Settings): Item(settings) {

    override fun finishUsing(stack: ItemStack?, world: World?, user: LivingEntity?): ItemStack {
        val playerEntity = if (user is PlayerEntity) user else null
        if (playerEntity is ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(playerEntity as ServerPlayerEntity?, stack)
        }

        if (!world!!.isClient()){
            user?.heal(10.0F)
            if (user is PlayerEntity) (user as PlayerEntity).itemCooldownManager?.set(this, 20)

            if (playerEntity != null) {
                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this))
                if (!playerEntity.abilities.creativeMode) {
                    stack!!.decrement(1)
                }
            }

        }
        return super.finishUsing(stack, world, user)
    }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        return ItemUsage.consumeHeldItem(world, user, hand)
    }

    override fun getMaxUseTime(stack: ItemStack?): Int {
        return 32
    }

    override fun getUseAction(stack: ItemStack?): UseAction {
        return UseAction.DRINK
    }

}

fun registerItems() {
    Registry.register(Registry.ITEM, Identifier("mymod", "estus_flask"), ESTUS_FLASK)
}