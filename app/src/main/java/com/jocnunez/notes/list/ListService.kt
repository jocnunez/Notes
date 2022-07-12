package com.jocnunez.notes.list

import android.content.Context
import android.util.Log
import com.jocnunez.notes.list.item.Item
import org.json.JSONArray
import java.io.IOException

class ListService constructor(context: Context) {

    var list = mutableListOf<Item>()

    init {
        try {
            val json = JSONArray(context.assets.open("example.json").bufferedReader().use { it.readText() })
            list = parseJson(json)
        } catch (ioException: IOException) {
            Log.d("Error", ioException.toString())
        }
    }

    fun parseJson(json: JSONArray):MutableList<Item> {
        var list = mutableListOf<Item>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val item = Item(
                jsonObject.optString("title"),
                jsonObject.optString("description"),
                jsonObject.getLong("creationDate"),
                jsonObject.getBoolean("check")
            )
            list.add(item)
        }
        return list
    }
}