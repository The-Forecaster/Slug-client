package trans.rights.client.api

import java.io.File

interface SaveLoadClass {
    val file: File

    fun save(file: File)

    fun load(file: File)
}
