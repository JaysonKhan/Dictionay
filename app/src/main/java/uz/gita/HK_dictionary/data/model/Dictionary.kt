package uz.gita.HK_dictionary.data.model

data class Dictionary(
    val id: String,
    val english: String,
    val type: String,
    val transcript: String,
    val uzbek: String,
    val countable: String,
    val favourite: Int
)
