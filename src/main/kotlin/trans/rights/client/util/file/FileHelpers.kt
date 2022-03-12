package trans.rights.client.util.file

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Path

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
