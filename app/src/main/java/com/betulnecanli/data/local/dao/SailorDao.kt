package com.betulnecanli.data.local.dao
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.betulnecanli.domain.model.Characters

@Dao
interface SailorDao {

    @Query("SELECT * FROM sailor_table ORDER BY id ASC ")
    fun gelAllCharacters() : PagingSource<Int, Characters>

    @Query("SELECT * FROM sailor_table WHERE id=:chId")
    fun getSelectedCharacter(chId: Int) : Characters

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<Characters>)

    @Query("DELETE FROM sailor_table")
    suspend fun deleteAllCharacters()

}