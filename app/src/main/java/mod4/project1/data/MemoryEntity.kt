package mod4.project1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_memory")
data class MemoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val message: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)