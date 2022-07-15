package com.jocnunez.notes.json

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.jocnunez.notes.config.ConfigService
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
        Log.d("Debug", "PATH: "+folder.path+" FILESDIR: "+context.filesDir )

        val file = File(folder, name + fileExtension)
        file.createNewFile()

        val gson = GsonBuilder().setPrettyPrinting().create()
        val json = gson.toJson(list)
        file.writeText(json)
    }

    fun readFileList():List<JsonItem> {
        val files = mutableListOf<JsonItem>()
        val configService = ConfigService(context)
        val defaultFileName = configService.getDefaultFileName()

        val folder = File(context.filesDir, folderName)
        folder.walk().forEach {
            if (!it.isDirectory) {
                val item = JsonItem(
                    it.nameWithoutExtension,
                    it.nameWithoutExtension == defaultFileName
                )
                files.add(item)
            }
        }
        return files
    }

    fun updateFile(jsonFile: JsonFile) {}

    fun deleteFile(json: JsonItem) {
        val folder = File(context.filesDir, folderName)
        val file = File(folder, json.name + fileExtension)
        file.delete()
        if (json.selected) {
            val configService = ConfigService(context)
            configService.setDefaultFileName("")
        }
    }

    fun selectFile(list: List<JsonItem>, item:JsonItem) {
        val configService = ConfigService(context)
        configService.setDefaultFileName(item.name)
        list.forEach {
            it.selected = it.name == item.name
        }
    }

    fun getSelectedIndex(list: List<JsonItem>):Int {
        return list.indexOfFirst { jsonItem -> jsonItem.selected }
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
