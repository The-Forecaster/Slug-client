package me.austin.client.util

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import me.austin.client.Queer.Companion.LOGGER
import java.io.BufferedWriter
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter
import java.nio.file.Files
import java.nio.file.Path

private val gson = GsonBuilder().setLenient().setPrettyPrinting().create()

@get:Throws(OutOfMemoryError::class)
inline val Path.readString: String
    get() = try {
        Files.readString(this) ?: ""
    } catch (e: Exception) {
        when (e) {
            is IOException, is SecurityException -> LOGGER.error("Couldn't read $this")
            else -> throw e
        }

        ""
    }

@Throws(
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

@Throws(
    IOException::class,
    IllegalArgumentException::class,
    UnsupportedOperationException::class,
    FileAlreadyExistsException::class,
    SecurityException::class
)
fun File.writeToJson(element: JsonObject) = this.toPath().writeToJson(element)

fun Path.fromJson(clearIfException: Boolean = true) = try {
    gson.fromJson(this.readString, JsonObject::class.java) ?: JsonObject()
} catch (e: JsonSyntaxException) {
    if (clearIfException) runCatching(Path::clearJson).onFailure(Throwable::printStackTrace)

    JsonObject()
}

fun File.fromJson(clearIfException: Boolean = false) = this.toPath().fromJson(clearIfException)

@Throws(
    IOException::class,
    IllegalArgumentException::class,
    UnsupportedOperationException::class,
    FileAlreadyExistsException::class,
    SecurityException::class
)
fun Path.clearJson() = this.writeToJson(JsonObject())

@Throws(
    IOException::class,
    IllegalArgumentException::class,
    UnsupportedOperationException::class,
    FileAlreadyExistsException::class,
    SecurityException::class
)
fun File.clearJson() = this.toPath().clearJson()