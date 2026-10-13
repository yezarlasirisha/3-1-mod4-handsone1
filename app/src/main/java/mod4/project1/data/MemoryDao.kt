package mod4.project1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryDao {
    @Query("SELECT * FROM chat_memory ORDER BY timestamp ASC")
    fun getAllMessages(): Flow<List<MemoryEntity>>

    @Insert
    suspend fun insertMessage(message: MemoryEntity)

    @Query("DELETE FROM chat_memory")
    suspend fun clearMemory()
}