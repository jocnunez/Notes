package com.jocnunez.notes.list

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.jocnunez.notes.json.JsonService
import com.jocnunez.notes.list.item.Item

class ListService constructor(val context: Context) {

    lateinit var toDoList:ToDoList

    init {
        getListFromFile()
    }

    fun getListFromFile() {
        val file = JsonService(context).getSelectedFile()
        val json = file.inputStream().bufferedReader().use { it.readText() }

        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<List<Item>>() {}.type
        val list = gson.fromJson<List<Item>>(json, itemType)
        toDoList = ToDoList(file.nameWithoutExtension, list)
    }
}