package me.austin.queer.util.file

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Path
import net.fabricmc.loader.api.FabricLoader

public val maindir = FabricLoader.getInstance().getConfigDir()

private val gson = GsonBuilder().create()

fun readJson(path: Path): JsonObject {
    val jsonReader = gson.newJsonReader(BufferedReader(InputStreamReader(Files.newInputStream(path))))
    val obj = JsonObject()

    while (jsonReader.hasNext()) {
        val name = jsonReader.peek().name
        val value = jsonReader.nextString()

        obj.add(name, JsonPrimitive(value))
    }

    jsonReader.close()
    return obj
}

fun writeToJson(element: JsonObject, path: Path) = {
    val writer = BufferedWriter(OutputStreamWriter(Files.newOutputStream(path)))

    writer.write(gson.toJson(element))
    writer.close()
}

fun clearJson(path: Path) = { writeToJson(JsonObject(), path) }
