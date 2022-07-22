package com.jocnunez.notes.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jocnunez.notes.R
import com.jocnunez.notes.databinding.ActivityListBinding
import com.jocnunez.notes.entities.Note
import com.jocnunez.notes.list.item.Item
import com.jocnunez.notes.menu.MenuHandler

class ListActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityListBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ListService(this).notes
        val listAdapter = ListAdapter(
            list,
            { note, pos -> moveHandler(note, pos) },
            { note, pos -> editHandler(note, pos) }
        )

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.listContainer.layoutManager = layoutManager
        binding.listContainer.adapter = listAdapter

        //New Item Fragment
        /*val newItem = findViewById<Button>(R.id.newButton)
        newItem.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val fragment = ItemDetailFragment()
            fragmentTransaction.replace(R.id.detailLayout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }*/
    }

    private fun editHandler(note: Note, pos: Int) {

    }

    private fun moveHandler(note: Note, pos: Int) {

    }


    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var menuHandler = MenuHandler(this,"list")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
    }

}