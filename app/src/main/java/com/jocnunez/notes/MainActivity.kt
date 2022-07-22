package com.jocnunez.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jocnunez.notes.config.ConfigService
import com.jocnunez.notes.databinding.ActivityMainBinding
import com.jocnunez.notes.views.listmanagement.JsonActivity
import com.jocnunez.notes.list.ListActivity
import com.jocnunez.notes.views.login.LoginActivity
import com.jocnunez.notes.storage.StorageTypes

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val config = ConfigService(this)
        var defaultList:String? = null

        when (config.getSelectedStorage()) {
            StorageTypes.FIREBASE -> {
                    //TODO: startActivity(Intent(this, LoginActivity::class.java))
                defaultList = config.getDefaultFirebaseNode()
            }
            else -> { //StorageTypes.LOCAL
                defaultList = config.getDefaultFileName()
            }
        }

        if (defaultList.isNullOrBlank()) {
            startActivity(Intent(this, JsonActivity::class.java))
        } else {
            startActivity(Intent(this, ListActivity::class.java))
        }

/*        val database = Firebase.database("https://jocnunez-notes-default-rtdb.europe-west1.firebasedatabase.app/")
        val reference = database.getReference("message")
        reference.setValue("Goodbye World!")
*/
    }
}