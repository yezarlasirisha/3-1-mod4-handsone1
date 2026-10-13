package mod4.project1.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import mod4.project1.data.MemoryDatabase
import mod4.project1.data.MemoryEntity
import mod4.project1.network.ApiClient
import mod4.project1.network.ChatRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = MemoryDatabase.getDatabase(application)
    private val dao = db.memoryDao()

    val messages = dao.getAllMessages()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun sendMessage(text: String) {
        viewModelScope.launch {
            // 1. Save User Message Locally
            dao.insertMessage(MemoryEntity(message = text, isUser = true))
            _isLoading.value = true

            try {
                // 2. Send to Python Backend
                val response = ApiClient.apiService.sendMessage(
                    ChatRequest(user_id = "user_123", message = text)
                )

                // 3. Save AI Response Locally
                dao.insertMessage(MemoryEntity(message = response.reply, isUser = false))
            } catch (e: Exception) {
                dao.insertMessage(MemoryEntity(message = "Error: ${e.message}", isUser = false))
            } finally {
                _isLoading.value = false
            }
        }
    }
}