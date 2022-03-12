package trans.rights.client.modules.hack

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.File
import trans.rights.client.TransRights.Companion.maindir
import trans.rights.client.modules.Manager
import trans.rights.client.modules.hack.hacks.*
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Path

object HackManager : Manager<Hack>(mutableSetOf()) {
    val dir = File("$maindir/hacks")

    init {
        if (!dir.exists()) dir.mkdirs()

        this.add(FlightHack)
        this.add(AutoHit)
    }

    fun save() {
        this.values.stream().forEach { hack -> hack.save(hack.file) }
    }

    override fun load() {
        this.values.stream().forEach { hack ->
            hack.load(hack.file)
            hack.save(hack.file)
        }
    }

    override fun unload() {
        this.values.stream().forEach { hack ->
            hack.save(hack.file)
            hack.settings.clear()
        }
        values.clear()
    }
}

private val gson = GsonBuilder().setPrettyPrinting().setLenient().create()

fun readJson(path: Path): JsonObject {
    val jsonReader = gson.newJsonReader(InputStreamReader(Files.newInputStream(path)))
    val obj = JsonObject()

    while (jsonReader.hasNext()) {
        val name = jsonReader.peek().name
        val value = jsonReader.nextString()

        obj.add(name, JsonPrimitive(value))
    }

    jsonReader.close()
    return obj
}

fun writeToJson(element: JsonObject, path: Path) {
    val writer = BufferedWriter(OutputStreamWriter(Files.newOutputStream(path)))

    writer.write(gson.toJson(element))
    writer.close()
}

fun clearJson(path: Path) = writeToJson(JsonObject(), path)
