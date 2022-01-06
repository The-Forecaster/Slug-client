package me.austin.queer.util.file

import com.google.gson.JsonObject
import com.google.gson.GsonBuilder

import java.io.File
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.nio.file.Files;
import java.nio.file.Path

import kotlin.io.path.exists

import me.austin.queer.Globals.*

object FileHelper {
    private val gson = GsonBuilder().setPrettyPrinting().create()
    val mainpath = File(mc.runDirectory.absolutePath + "/"+ NAME)

    @JvmStatic
    fun createFile(file: File) {
        try {
            file.createNewFile()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun writeToJson(element : JsonObject, path: Path) {
        try {
            val writer = BufferedWriter(OutputStreamWriter(Files.newOutputStream(path)))

            writer.write(gson.toJson(element))
            writer.close()
        }
        catch (e : Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    private fun fileExists(path: String) : Boolean {
        try {
            return mainpath.resolve(path).exists()
        } 
        catch(e: Exception) {
            return false
        }
    }
}