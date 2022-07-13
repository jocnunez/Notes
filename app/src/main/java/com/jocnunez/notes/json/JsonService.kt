package com.jocnunez.notes.json

import android.content.Context
import com.google.gson.GsonBuilder
import com.jocnunez.notes.list.ListService
import com.jocnunez.notes.list.item.Item
import org.json.JSONArray
import java.io.File

class JsonService(val context: Context) {
    private val folderName = "jsons"
    private val fileExtension = ".json"

    fun createJsonFile(name: String, copyExample: Boolean) {
        var list = mutableListOf<Item>()

        if (copyExample) {
            list = getListFromExample()
        }

        //TODO validate name, validate if file exists ...
        val folder = File(context.filesDir, folderName)
        folder.mkdir()

        val file = File(folder, name + fileExtension)
        file.createNewFile()

        val gson = GsonBuilder().setPrettyPrinting().create()
        val json = gson.toJson(list)
        file.writeText(json)
    }

    fun getFileList():MutableList<JsonItem> {
        val files = mutableListOf<JsonItem>()

        val folder = File(context.filesDir, folderName)
        folder.walk().forEach {
            if (!it.isDirectory) {
                val item = JsonItem(it.nameWithoutExtension)
                files.add(item)
            }
        }
        return files
    }

    private fun getListFromExample(): MutableList<Item> {
        val file = context.assets.open("example.json")
        val jsonArray = JSONArray(file.bufferedReader().use {
            it.readText()
        })
        val list = ListService(context).parseJson(jsonArray)
        return list
    }
}
