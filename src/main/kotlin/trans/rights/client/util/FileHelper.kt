package trans.rights.client.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import trans.rights.TransRights.Companion.LOGGER
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Path

private val gson: Gson = GsonBuilder().setLenient().setPrettyPrinting().create()

internal val Path.readString: String
    get() = try {
        Files.readString(this)
    } catch (e: Exception) {
        when (e) {
            is IOException, is SecurityException -> LOGGER.error("Couldn't read $this")
            else -> throw e
        }

        ""
    }

@kotlin.jvm.Throws(
    IOException::class,
    IllegalArgumentException::class,
    UnsupportedOperationException::class,
    FileAlreadyExistsException::class,
    SecurityException::class
)
fun Path.writeToJson(element: JsonObject) {
    val writer = BufferedWriter(OutputStreamWriter(Files.newOutputStream(this)))

    writer.write(gson.toJson(element))
    writer.close()
}

fun Path.fromJson(clearIfException: Boolean = false): JsonObject {
    return try {
        gson.fromJson(this.readString, JsonObject::class.java)
    } catch (e: JsonSyntaxException) {
        if (clearIfException) this.clearJson()

        JsonObject()
    }
}

@kotlin.jvm.Throws(
    IOException::class,
    IllegalArgumentException::class,
    UnsupportedOperationException::class,
    FileAlreadyExistsException::class,
    SecurityException::class
)
fun Path.clearJson() = this.writeToJson(JsonObject())
