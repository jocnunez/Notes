package com.jocnunez.notes.storage

import android.content.Context
import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.jocnunez.notes.config.ConfigService
import com.jocnunez.notes.list.item.Item
import java.io.File

class JsonService(val context: Context) {
    private val folderName = "jsons"
    private val fileExtension = ".json"



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

    fun updateFile() {}

    fun deleteFile(json: JsonItem) {
        val folder = File(context.filesDir, folderName)
        val file = File(folder, json.name + fileExtension)
        file.delete()
        if (json.selected) {
            val configService = ConfigService(context)
            configService.setDefaultFileName("")
        }
    }

    fun selectFile(list: List<JsonItem>, item: JsonItem) {
        val configService = ConfigService(context)
        configService.setDefaultFileName(item.name)
        list.forEach {
            it.selected = it.name == item.name
        }
    }

    fun getSelectedIndex(list: List<JsonItem>):Int {
        return list.indexOfFirst { jsonItem -> jsonItem.selected }
    }

    fun getSelectedFile():File {
        val configService = ConfigService(context)
        val fileName = configService.getDefaultFileName() + fileExtension
        val folder = File(context.filesDir, folderName)
        return File(folder, fileName)
    }

    private fun getListFromExample(): List<Item> {
        val file = context.assets.open("example.json")
        val json = file.bufferedReader().use { it.readText() }
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<List<Item>>() {}.type
        return gson.fromJson(json, itemType)
    }
}
