package com.jocnunez.notes.views.config

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.jocnunez.notes.R
import com.jocnunez.notes.config.ConfigService
import com.jocnunez.notes.databinding.ActivityConfigBinding
import com.jocnunez.notes.menu.MenuHandler
import com.jocnunez.notes.storage.StorageTypes

class ConfigActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityConfigBinding
    private val binding get() = _binding
    private lateinit var configService:ConfigService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configService = ConfigService(this)
        refreshSelectedStorage()

        binding.localDataFlag.setOnClickListener { toggleSelectedStorage() }
        binding.firebaseDataFlag.setOnClickListener { toggleSelectedStorage() }
    }

    private fun refreshSelectedStorage() {
        val selectedStorage = configService.getSelectedStorage()
        binding.localDataFlag.isChecked = selectedStorage == StorageTypes.LOCAL
        binding.firebaseDataFlag.isChecked = selectedStorage == StorageTypes.FIREBASE
    }

    private fun toggleSelectedStorage() {
        val selectedStorage =  configService.getSelectedStorage()
        if (selectedStorage == StorageTypes.FIREBASE) {
            configService.setSelectedStorage(StorageTypes.LOCAL)
        }  else {
            configService.setSelectedStorage(StorageTypes.FIREBASE)
        }
        refreshSelectedStorage()
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuHandler = MenuHandler(this,"config")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
    }
}