package com.jocnunez.notes.list

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.jocnunez.notes.json.JsonService
import com.jocnunez.notes.list.item.Item
import org.json.JSONArray
import java.io.IOException

class ListService constructor(val context: Context) {

    lateinit var list:ToDoList

    init {
        getListFromFile()
    }

    fun getListFromFile() {
        val file = JsonService(context).getSelectedFile()
        val json = file.inputStream().bufferedReader().use { it.readText() }

        val gson = GsonBuilder().create()
        list = gson.fromJson(json, ToDoList::class.java)
    }
}