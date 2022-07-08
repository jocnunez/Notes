package com.jocnunez.notes.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.jocnunez.notes.R
import com.jocnunez.notes.menu.MenuItems

class LoginActivity : AppCompatActivity() {
    private var loginContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginContainer = findViewById(R.id.loginContainer)
        val isLocal = intent.getBooleanExtra("isLocal", true)
        if (isLocal) {
            initLocalJSON()
        } else {
            initFirebase()
        }
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        MenuItems().itemHandler(item)
        return super.onOptionsItemSelected(item)
    }


    // Firebase
    fun initFirebase() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.loginContainer, LoginFormFragment())
        transaction.commit()
    }

    fun initLocalJSON() {

    }
}