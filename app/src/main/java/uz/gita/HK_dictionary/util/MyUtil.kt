package uz.gita.HK_dictionary.util

import androidx.room.TypeConverter
import java.util.*

fun <T> T.myApply(block: T.() -> Unit) {
    block(this)
}

object Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}