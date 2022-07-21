package com.jocnunez.notes.storage.firebase

import android.content.Context
import com.jocnunez.notes.entities.Note
import com.jocnunez.notes.storage.INotesRepository

class NotesRepositoryFirebase(val context: Context): INotesRepository {
    override fun createNotes(id: String, copyExample: Boolean) {
        TODO("Not yet implemented")
    }

    override fun readNotes(): List<String> {
        TODO("Not yet implemented")
    }

    override fun readNotesById(id: String): List<Note> {
        TODO("Not yet implemented")
    }

    override fun updateNotes(id: String, notes: List<Note>) {
        TODO("Not yet implemented")
    }

    override fun deleteNotes(id: String) {
        TODO("Not yet implemented")
    }
}