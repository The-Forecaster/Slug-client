package me.austin.queer.util.file

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.exists
import me.austin.queer.Globals.*

object FileHelper {
    private val gson = GsonBuilder().setPrettyPrinting().create()
    val mainpath = File(mc.runDirectory.absolutePath + "/" + NAME)

    @JvmStatic
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

    @JvmStatic
    fun writeToJson(element: JsonObject, path: Path) {
        val writer = BufferedWriter(OutputStreamWriter(Files.newOutputStream(path)))

        writer.write(gson.toJson(element))
        writer.close()
    }

    @JvmStatic
    fun clearJson(path: Path) {
        writeToJson(JsonObject(), path)
    }

    @JvmStatic
    private fun fileExists(path: String): Boolean {
        try {
            return mainpath.resolve(path).exists()
        } 
        catch (e: Exception) {
            return false
        }
    }
}
