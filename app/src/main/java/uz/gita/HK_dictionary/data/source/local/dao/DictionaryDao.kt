package uz.gita.HK_dictionary.data.source.local.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.gita.HK_dictionary.data.source.local.entity.DictionaryEntity

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary")
    fun getAllEnglishWords(): Cursor

    @Insert
    fun insert(dictionaryEntity: DictionaryEntity)

    @Query("SELECT * FROM dictionary ORDER BY uzbek GLOB '[A-Za-z]*' DESC, uzbek")
    fun getAllUzbekWords(): Cursor

    @Update
    fun updateDictionary(dictionary: DictionaryEntity)
    @Query("SELECT * FROM dictionary WHERE english LIKE '%' || :searchEnglish || '%'")
    fun searchEnglishWord(searchEnglish: String): Cursor

    @Query("SELECT * FROM dictionary WHERE uzbek LIKE '%' || :searchEnglish || '%'")
    fun searchUzbekWord(searchEnglish: String): Cursor

    @Query("SELECT * FROM dictionary WHERE favourite = 1")
    fun getAllFavourites(): Cursor
}