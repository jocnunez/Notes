package com.jocnunez.notes.json

import android.content.Context
import com.jocnunez.notes.list.ListService
import com.jocnunez.notes.list.item.Item
import org.json.JSONArray
import java.io.File

class JsonService(val context: Context) {

    fun createJsonFile(name: String, copyExample: Boolean) {
        var list = mutableListOf<Item>()

        if (copyExample) {
            list = getListFromExample()
        }

        //TODO validate name, validate if file exists ...
        val folder = File(context.filesDir, "jsons")
        folder.mkdir()

        val file = File(folder, name + ".json")
        file.createNewFile()
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
