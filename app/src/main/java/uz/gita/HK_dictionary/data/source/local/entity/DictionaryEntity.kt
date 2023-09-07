package uz.gita.HK_dictionary.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class DictionaryEntity(
    @PrimaryKey
    val id: String,
    val english: String,
    val type: String,
    val transcript: String,
    val uzbek: String,
    val countable: String,
    var favourite: Int
)
