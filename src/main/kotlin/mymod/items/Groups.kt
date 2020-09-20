package mymod.items

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.Identifier

inline fun FabricItemGroupBuilder.appendApply(crossinline block: MutableList<ItemStack>.() -> Unit)
    = appendItems { stack -> stack.block() }

val MY_MOD_ITEMS: ItemGroup = FabricItemGroupBuilder.create(Identifier("mymod", "mymod")).apply {
    icon { ItemStack(Items.NETHER_STAR) }
    appendApply {
        add(ItemStack(ESTUS_FLASK))
    }
}.build()
