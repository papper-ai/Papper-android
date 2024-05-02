package com.example.domain.repository

import com.example.domain.model.BaseResponse
import com.example.domain.model.chat.ChatModelResult
import com.example.domain.model.chat.ChatsPreviewModelResult
import com.example.domain.model.chat.SendMessageResult

interface ChatRepository {
    suspend fun getChatsPreview(): ChatsPreviewModelResult
    suspend fun getChatById(id: String): ChatModelResult
    suspend fun deleteChat(id: String)
    suspend fun clearChat(id: String)
    suspend fun archiveChat(id: String)
    suspend fun unarchiveChat(id: String)
    suspend fun renameChat(id: String, name: String): BaseResponse
    suspend fun createChat()
    suspend fun sendMessage(message: String) : SendMessageResult
}