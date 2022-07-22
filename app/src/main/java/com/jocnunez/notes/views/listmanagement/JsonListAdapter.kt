package com.jocnunez.notes.views.listmanagement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jocnunez.notes.databinding.ItemJsonListBinding
import com.jocnunez.notes.storage.JsonItem
import com.jocnunez.notes.storage.ListService

class JsonListAdapter(
    var jsonList: List<String>,
    var selected: String?,
    var itemHandler: ((id: String, pos: Int) -> Unit),
    var deleteHandler: ((id: String, pos: Int) -> Unit)
) : RecyclerView.Adapter<JsonListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemJsonListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemJsonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(jsonList[position]) {
                binding.root.setOnClickListener{ itemHandler(this, position) }
                binding.jsonItemName.text = this
                binding.jsonItemSelected.isChecked = this == selected
                binding.jsonItemRemoveBtn.setOnClickListener { deleteHandler(this, position) }
            }
        }
    }

    override fun getItemCount(): Int {
        return jsonList.size
    }

}