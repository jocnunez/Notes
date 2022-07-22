package com.jocnunez.notes.views.listmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jocnunez.notes.R
import com.jocnunez.notes.databinding.ActivityJsonBinding
import com.jocnunez.notes.storage.JsonItem
import com.jocnunez.notes.storage.JsonService
import com.jocnunez.notes.menu.MenuHandler
import com.jocnunez.notes.storage.ListService

class JsonActivity : AppCompatActivity() {
    private var _binding: ActivityJsonBinding? = null
    private val binding get() = _binding!!
    private lateinit var jsonListAdapter: JsonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityJsonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listService = ListService(this)
        val jsonList = listService.allList
        val selected:String? = listService.defaultId
        jsonListAdapter = JsonListAdapter(
            jsonList,
            selected,
            { id, pos -> itemHandler(id, pos) },
            { id, pos -> deleteHandler(id, pos) }
        )

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.fileListContainer.layoutManager = layoutManager
        binding.fileListContainer.adapter = jsonListAdapter

        binding.createJsonBtn.setOnClickListener {
            createJsonHandler()
        }
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuHandler = MenuHandler(this,"json")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun itemHandler(id: String, pos: Int) {
        val jsonService = JsonService(this)
        val prev = jsonService.getSelectedIndex(jsonListAdapter.jsonList)
        jsonService.selectFile(jsonListAdapter.jsonList, jsonItem)
        jsonListAdapter.notifyItemChanged(prev)
        jsonListAdapter.notifyItemChanged(pos)
    }

    private fun deleteHandler(id: String, pos: Int) {
        val listService = ListService(this)
        listService.notesRepository.deleteNotes(id)
        jsonListAdapter.jsonList = listService.allList
        jsonListAdapter.notifyItemRemoved(pos)
    }

    private fun createJsonHandler() {
        val jsonService = JsonService(this)
        binding.jsonName.setText("")
        val lastPos = jsonListAdapter.itemCount
        jsonListAdapter.jsonList = jsonService.readFileList()
        jsonListAdapter.notifyItemInserted(lastPos)
    }
}