package trans.rights.client.api.friend

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.minecraft.client.network.ClientPlayerEntity
import trans.rights.TransRights.Companion.mainDirectory
import trans.rights.client.api.Wrapper
import trans.rights.client.api.commons.Manager
import trans.rights.client.api.commons.Nameable
import trans.rights.client.util.FileHelper
import java.io.File
import java.util.*

class Friend(override val name: String, val uuid: UUID) : Nameable

object FriendManager : Manager<Friend>(mutableListOf()), Wrapper {
    private val friendFile: File = File("${mainDirectory}friends.json")

    override fun load() {
        if (!friendFile.exists()) {
            friendFile.createNewFile()
            return
        }

        for (entry in FileHelper.fromJson(friendFile.toPath()).keySet()) {
            values.add(Friend(entry, minecraft.socialInteractionsManager.getUuid(entry)))
        }
    }

    override fun unload() {
        save()

        super.unload()
    }

    fun save() {
        val obj = JsonObject()

        for (friend in values) {
            obj.add(friend.name, JsonPrimitive(friend.uuid.toString()))
        }

        FileHelper.writeToJson(obj, friendFile.toPath())
    }
}

fun ClientPlayerEntity.isFriend(): Boolean {
    for (friend in FriendManager.values) {
        if (this.uuid == friend.uuid) {
            return true
        }
    }
    return false
}


