package com.betulnecanli.sailormoonapp.data.local.dao
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon

@Dao
interface SailorDao {

    @Query("SELECT * FROM sailor_table ORDER BY id ASC ")
    fun gelAllCharacters() : PagingSource<Int, SailorMoon>

    @Query("SELECT * FROM sailor_table WHERE id=:chId")
    fun getSelectedCharacter(chId: Int) : SailorMoon

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<SailorMoon>)

    @Query("DELETE FROM sailor_table")
    suspend fun deleteAllCharacters()

}