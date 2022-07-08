package com.jocnunez.notes.menu

import android.content.Intent
import android.util.Log
import android.view.MenuItem
import com.jocnunez.notes.R
import com.jocnunez.notes.config.ConfigActivity
import com.jocnunez.notes.list.ListActivity

class MenuItems {
    fun itemHandler(item: MenuItem) {
        when (item.itemId) {
            R.id.configMenu -> openConfig()
            R.id.listMenu -> openList()
            R.id.loginMenu -> openLogin()
        }
    }

    private fun openConfig() {
        Log.d("Debug", "Menu: Config")
    }

    private fun openList() {
        Log.d("Debug", "Menu: List")
    }

    private fun openLogin() {
        Log.d("Debug", "Menu: Login")
    }
}