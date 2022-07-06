package com.jocnunez.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jocnunez.notes.list.ListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, ListActivity::class.java).apply {
            Log.d("Debug", "Starting List Activity")
        }
        startActivity(intent)
    }
}