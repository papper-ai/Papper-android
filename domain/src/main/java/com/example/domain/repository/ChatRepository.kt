package com.example.domain.repository

import com.example.domain.model.ChatModel
import com.example.domain.model.ChatsPreviewModel

interface ChatRepository {
    suspend fun getChatsPreview(): List<ChatsPreviewModel>
    suspend fun getChatById(id: String): ChatModel
    suspend fun deleteChat(id: String)
    suspend fun clearChat(id: String)
    suspend fun archiveChat(id: String)
    suspend fun sendMessage()
}