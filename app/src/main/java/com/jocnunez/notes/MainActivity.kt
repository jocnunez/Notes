package com.jocnunez.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.jocnunez.notes.list.ListActivity
import com.jocnunez.notes.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: Check configuration and pass parameters to Login Activity if necessary
        val isLogged = false
        if (isLogged) {
            startActivity(Intent(this, ListActivity::class.java).apply { })
        } else {
            // TODO: Is firebase or local?
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("isLocal", false)
            startActivity(intent)
        }
    }
}