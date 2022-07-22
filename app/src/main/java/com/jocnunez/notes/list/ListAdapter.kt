package com.jocnunez.notes.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jocnunez.notes.databinding.ItemListBinding
import com.jocnunez.notes.entities.Note
import com.jocnunez.notes.list.item.Item
import java.text.SimpleDateFormat

class ListAdapter (
    var list: List<Note>,
    var moveHandler: ((item: Note, pos: Int) -> Unit),
    var editHandler: ((item: Note, pos: Int) -> Unit)
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.root.setOnClickListener{ moveHandler(this, position) }
                binding.title.text = this.title
                binding.description.text = this.description
                binding.checkbox.isChecked = this.check
                binding.date.text = getFormattedDate(this.creationDate)
                binding.editButton.setOnClickListener { editHandler(this, position) }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun getFormattedDate(date: Long):String {
        val format = SimpleDateFormat("dd-MM-yyyy")
        return format.format(date)
    }
}
