package trans.rights.client.api.commons

interface Nameable {
    val name: String
}

interface Manager<out T, L : Collection<T>> {
    val values: L

    fun load()

    fun unload()
}

abstract class Modular(override val name: String, val description: String) : Nameable