package com.jocnunez.notes.list.item

import java.text.SimpleDateFormat
import java.time.Instant

data class Item(
    val title: String,

    val type: ItemTypes = ItemTypes.basic,
    val check: Boolean = false,
    val creationDate: Long = Instant.now().toEpochMilli(),
    val modificationDate: Long = Instant.now().toEpochMilli(),

    val description: String?,
    val location: String?,
    val scheduledDate: Long?,
    val labels: List<String>,

    val media: String?,
    val list: List<SubItem>?
) {

    fun getFormattedDate(date: Long):String {
        val format = SimpleDateFormat("dd-MM-yyyy")
        return format.format(date)
    }
}
