package mod4.project1.network

import retrofit2.http.Body
import retrofit2.http.POST

data class ChatRequest(val user_id: String, val message: String)
data class ChatResponse(val reply: String, val context_used: List<String>)

interface ApiService {
    // 10.0.2.2 is the special IP for localhost from Android Emulator
    @POST("chat")
    suspend fun sendMessage(@Body request: ChatRequest): ChatResponse
}