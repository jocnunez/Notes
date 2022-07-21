package com.jocnunez.notes.storage.local

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.jocnunez.notes.config.ConfigService
import com.jocnunez.notes.entities.Note
import com.jocnunez.notes.storage.INotesRepository
import java.io.File
import java.io.InputStream

class NotesRepositoryLocal(val context:Context): INotesRepository {
    private val folderName = "jsons"
    private val fileExtension = ".json"

    override fun createNotes(id: String, copyExample: Boolean) {
        val folder = File(context.filesDir, folderName)
        folder.mkdir()

        val file = File(folder, id + fileExtension)
        file.createNewFile()

        var json = "[]"
        if (copyExample) {
            val example = context.assets.open("example.json")
            val gson = GsonBuilder().setPrettyPrinting().create()
            val list = parseFile(example)
            json = gson.toJson(list)
        }
        file.writeText(json)
    }

    override fun readNotes(): List<String> {
        val files = mutableListOf<String>()

        val folder = File(context.filesDir, folderName)
        folder.walk().forEach {
            if (!it.isDirectory) {
                files.add(it.nameWithoutExtension)
            }
        }
        return files
    }

    override fun readNotesById(id: String): List<Note> {
        val fileName = id + fileExtension
        val folder = File(context.filesDir, folderName)
        val file = File(folder, fileName)
        return parseFile(file.inputStream())
    }

    override fun updateNotes(id: String, notes: List<Note>) {
        TODO("Not yet implemented")
    }

    override fun deleteNotes(id: String) {
        val folder = File(context.filesDir, folderName)
        val file = File(folder, id + fileExtension)
        file.delete()
        val configService = ConfigService(context)
        if (configService.getDefaultFileName() == id) {
            configService.setDefaultFileName("")
        }
    }

    private fun parseFile(file: InputStream): List<Note> {
        val json = file.bufferedReader().use { it.readText() }
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<List<Note>>() {}.type
        return gson.fromJson(json, itemType)
    }
}