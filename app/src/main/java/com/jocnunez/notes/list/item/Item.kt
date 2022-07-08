package com.jocnunez.notes.list.item

import java.text.SimpleDateFormat
import java.time.Instant

data class Item(
    val title: String,
    val description: String = "",
    val date: Long = Instant.now().toEpochMilli(),
    val check: Boolean = false
) {
    fun getFormattedDate():String {
        val format = SimpleDateFormat("dd-MM-yyyy")
        return format.format(date)
    }
}
