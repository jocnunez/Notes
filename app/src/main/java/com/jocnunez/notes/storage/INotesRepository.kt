package com.jocnunez.notes.storage

import android.app.Service
import android.content.Context
import com.jocnunez.notes.entities.Note

interface INotesRepository  {
    fun createNotes(id: String, copyExample: Boolean)
    fun readNotes():List<String>
    fun readNotesById(id: String):List<Note>
    fun updateNotes(id: String, notes: List<Note>)
    fun deleteNotes(id: String)
}