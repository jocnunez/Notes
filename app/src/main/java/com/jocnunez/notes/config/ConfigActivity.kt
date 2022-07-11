package com.jocnunez.notes.config

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jocnunez.notes.R
import com.jocnunez.notes.databinding.ActivityConfigBinding

class ConfigActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityConfigBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}