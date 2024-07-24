package me.austin.client.api

interface Name {
    val name: String
}

interface Description {
    val description: String
}

interface Manager<out T, out L : Collection<T>> {
    val values: L

    fun load()

    fun unload()
}

interface Children<T> {
    val children: Array<out T>
}

abstract class Modular(final override val name: String, final override val description: String) : Name,
    Description

val <T> Children<T>.allChildren: List<T>
    get() {
        val list = mutableListOf<T>()

        fun recurse(children: Array<out T>) {
            for (setting in children) {
                if (setting is Children<*>) {
                    recurse(setting.children as Array<T>)
                }
                list.add(setting)
            }
        }

        recurse(this.children)
        return list
    }