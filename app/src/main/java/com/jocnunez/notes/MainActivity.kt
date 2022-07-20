package com.jocnunez.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jocnunez.notes.config.ConfigService
import com.jocnunez.notes.databinding.ActivityMainBinding
import com.jocnunez.notes.json.JsonActivity
import com.jocnunez.notes.list.ListActivity
import com.jocnunez.notes.login.LoginActivity
import com.jocnunez.notes.storage.StorageTypes
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val config = ConfigService(this)

        when (config.getSelectedStorage()) {
            StorageTypes.FIREBASE ->
                startActivity(Intent(this, LoginActivity::class.java))
            else -> {
                val defaultFile = config.getDefaultFileName()
                if (defaultFile.isNullOrBlank()) {
                    startActivity(Intent(this, JsonActivity::class.java))
                } else {
                    val intent = Intent(this, ListActivity::class.java)
                    intent.putExtra("fileName", defaultFile)
                    startActivity(intent)
                }
            }
        }
/*
        val database = Firebase.database("https://jocnunez-notes-default-rtdb.europe-west1.firebasedatabase.app/")
        val reference = database.getReference("message")
        reference.setValue("Goodbye World!")
 */
    }
}