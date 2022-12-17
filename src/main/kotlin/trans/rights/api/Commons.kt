package trans.rights.api

interface Nameable {
    val name: String
}

interface Manager<out T, out L : Collection<T>> {
    val values: L

    fun load()

    fun unload()
}

abstract class Modular(final override val name: String, val description: String) : Nameable