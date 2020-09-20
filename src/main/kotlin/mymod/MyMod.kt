package mymod

// For support join https://discord.gg/v6v4pMv
import mymod.items.registerItems

@Suppress("unused")
fun init() {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    registerItems()

    println("init main still works")
}