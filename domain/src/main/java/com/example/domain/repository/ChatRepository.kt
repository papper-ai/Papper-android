package com.example.domain.repository

import com.example.domain.model.ChatModel
import com.example.domain.model.ChatModelResult
import com.example.domain.model.ChatsPreviewModel
import com.example.domain.model.ChatsPreviewModelResult

interface ChatRepository {
    suspend fun getChatsPreview(): ChatsPreviewModelResult
    suspend fun getChatById(id: String): ChatModelResult
    suspend fun deleteChat(id: String)
    suspend fun clearChat(id: String)
    suspend fun archiveChat(id: String)
    suspend fun unarchiveChat(id: String)
    suspend fun renameChat(id: String, name: String)
    suspend fun createChat()
    suspend fun sendMessage()
}