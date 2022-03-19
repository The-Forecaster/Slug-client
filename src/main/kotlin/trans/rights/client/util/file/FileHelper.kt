package trans.rights.client.util.file

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Path
import trans.rights.client.TransRights.Companion.LOGGER

object FileHelper {
    private val gson: Gson = GsonBuilder().setPrettyPrinting().setLenient().create()

    fun writeToJson(element: JsonObject, path: Path) {
        val writer = BufferedWriter(OutputStreamWriter(Files.newOutputStream(path)))

        writer.write(gson.toJson(element))
        writer.close()
    }

    fun read(path: Path): String {
        return try {
            Files.readString(path)
        } catch (e: IOException) {
            LOGGER.error("Couldn't read $path")

            ""
        }
    }

    fun fromJson(path: Path): JsonObject {
        return try {
            gson.fromJson(read(path), JsonObject::class.java)
        } catch (e: JsonSyntaxException) {
            JsonObject()
        }
    }

    fun clearJson(path: Path) = writeToJson(JsonObject(), path)
}
