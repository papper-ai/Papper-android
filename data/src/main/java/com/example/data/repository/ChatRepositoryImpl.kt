package com.example.data.repository

import com.example.data.datasource.ChatRemoteDataSource
import com.example.data.model.mapToDomainModel
import com.example.domain.model.ChatModel
import com.example.domain.model.ChatModelResult
import com.example.domain.model.ChatsPreviewModel
import com.example.domain.model.ChatsPreviewModelResult
import com.example.domain.repository.ChatRepository
import javax.inject.Inject


class ChatRepositoryImpl @Inject constructor(
    val chatRemoteDataSource: ChatRemoteDataSource,
) : ChatRepository{
    override suspend fun getChatsPreview(): ChatsPreviewModelResult {
        return chatRemoteDataSource.fetchChatsPreview().mapToDomainModel()
    }

    override suspend fun getChatById(id: String): ChatModelResult {
        return chatRemoteDataSource.fetchChatById(id).mapToDomainModel()
    }

    override suspend fun deleteChat(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearChat(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun archiveChat(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun unarchiveChat(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun renameChat(id: String, name: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createChat() {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage() {
        TODO("Not yet implemented")
    }

}