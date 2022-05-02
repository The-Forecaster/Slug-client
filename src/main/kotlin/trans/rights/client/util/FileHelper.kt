package trans.rights.client.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import trans.rights.TransRights.Companion.LOGGER
import java.io.BufferedWriter
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Path

private val gson: Gson = GsonBuilder().setLenient().setPrettyPrinting().create()

inline val Path.readString: String
    get() = try {
        Files.readString(this)
    } catch (e: Exception) {
        when (e) {
            is IOException, is SecurityException -> LOGGER.error("Couldn't read $this")
            else -> throw e
        }

        ""
    }

inline val File.readString: String
    get() = this.toPath().readString

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

fun File.writeToJson(element: JsonObject) = this.toPath().writeToJson(element)

fun Path.fromJson(clearIfException: Boolean = false): JsonObject = try {
    gson.fromJson(this.readString, JsonObject::class.java)
} catch (e: JsonSyntaxException) {
    if (clearIfException) runCatching {
        clearJson()
    }

    JsonObject()
}

fun File.fromJson(clearIfException: Boolean = false) = this.toPath().fromJson(clearIfException)

@kotlin.jvm.Throws(
    IOException::class,
    IllegalArgumentException::class,
    UnsupportedOperationException::class,
    FileAlreadyExistsException::class,
    SecurityException::class
)
fun Path.clearJson() = this.writeToJson(JsonObject())

@kotlin.jvm.Throws(
    IOException::class,
    IllegalArgumentException::class,
    UnsupportedOperationException::class,
    FileAlreadyExistsException::class,
    SecurityException::class
)
fun File.clearJson() = this.toPath().clearJson()