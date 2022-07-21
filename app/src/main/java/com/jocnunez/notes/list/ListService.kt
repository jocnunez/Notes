package com.jocnunez.notes.list

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.jocnunez.notes.config.ConfigService
import com.jocnunez.notes.entities.Note
import com.jocnunez.notes.storage.JsonService
import com.jocnunez.notes.list.item.Item
import com.jocnunez.notes.storage.INotesRepository
import com.jocnunez.notes.storage.StorageTypes
import com.jocnunez.notes.storage.firebase.NotesRepositoryFirebase
import com.jocnunez.notes.storage.local.NotesRepositoryLocal

class ListService constructor(val context: Context) {

    var notes:List<Note>
    var notesRepository: INotesRepository

    private var configService: ConfigService

    init {
        val defaultId: String?
        configService = ConfigService(context)

        when (configService.getSelectedStorage()) {
            StorageTypes.FIREBASE -> {
                notesRepository = NotesRepositoryFirebase(context)
                defaultId = configService.getDefaultFirebaseNode()
            }
            StorageTypes.LOCAL -> {
                notesRepository = NotesRepositoryLocal(context)
                defaultId = configService.getDefaultFileName()
            }
        }
        notes = notesRepository.readNotesById(defaultId!!)
    }
}