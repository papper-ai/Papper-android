package com.example.domain.repository

import com.example.domain.model.BaseResponse
import com.example.domain.model.chat.ChatModelResult
import com.example.domain.model.chat.ChatsPreviewModelResult
import com.example.domain.model.chat.CreateChatResult
import com.example.domain.model.chat.SendMessageResult

interface ChatRepository {
    suspend fun getChatsPreview(): ChatsPreviewModelResult
    suspend fun getArchiveChatsPreview(): ChatsPreviewModelResult
    suspend fun getChatById(id: String): ChatModelResult
    suspend fun deleteChat(id: String): BaseResponse
    suspend fun clearChat(id: String): BaseResponse
    suspend fun archiveChat(id: String): BaseResponse
    suspend fun unarchiveChat(id: String): BaseResponse
    suspend fun renameChat(id: String, name: String): BaseResponse
    suspend fun createChat(id: String, title: String): CreateChatResult
    suspend fun sendMessage(message: String) : SendMessageResult
}