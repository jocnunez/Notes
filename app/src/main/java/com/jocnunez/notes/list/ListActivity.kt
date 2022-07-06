package com.jocnunez.notes.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import com.jocnunez.notes.R
import com.jocnunez.notes.item.Item

class ListActivity : AppCompatActivity() {
    var list: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //Load list
        val listLayout = findViewById<LinearLayout>(R.id.listLayout)
        val listService = ListService(this)
        list = listService.list
        addListToLayout(listLayout)

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

    private fun addListToLayout(layout: LinearLayout) {
        list.forEach {
            var itemView = LayoutInflater.from(this).inflate(R.layout.item_layout, null, false)
            itemView.findViewById<CheckBox>(R.id.checkbox).isChecked = it.check
            itemView.findViewById<TextView>(R.id.title).text = it.title
            itemView.findViewById<TextView>(R.id.description).text = it.description
            itemView.findViewById<TextView>(R.id.date).text = it.getFormattedDate()
            layout.addView(itemView)
        }
    }

    private fun addItemToLayout(text: String) {
        val textView = TextView(this)
        textView.text = text

        val listLayout = findViewById<LinearLayout>(R.id.listLayout)
        listLayout.addView(textView)
    }
}