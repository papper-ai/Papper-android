package com.example.data.repository

import com.example.data.base.mapToDomainModel
import com.example.data.datasource.remote.ChatRemoteDataSource
import com.example.data.model.chat.mapToDomainModel
import com.example.domain.model.BaseResponse
import com.example.domain.model.chat.ChatModelResult
import com.example.domain.model.chat.ChatsPreviewModelResult
import com.example.domain.model.chat.CreateChatResult
import com.example.domain.model.chat.SendMessageResult
import com.example.domain.repository.ChatRepository
import javax.inject.Inject


class ChatRepositoryImpl @Inject constructor(
    val chatRemoteDataSource: ChatRemoteDataSource,
) : ChatRepository{
    override suspend fun getChatsPreview(): ChatsPreviewModelResult {
        return chatRemoteDataSource.fetchChatsPreview().mapToDomainModel()
    }

    override suspend fun getArchiveChatsPreview(): ChatsPreviewModelResult {
        return chatRemoteDataSource.fetchArchiveChatsPreview().mapToDomainModel()
    }

    override suspend fun getChatById(id: String): ChatModelResult {
        return chatRemoteDataSource.fetchChatById(id).mapToDomainModel()
    }

    override suspend fun deleteChat(id: String): BaseResponse {
        return chatRemoteDataSource.deleteChat(id = id).mapToDomainModel()
    }

    override suspend fun clearChat(id: String): BaseResponse {
        return chatRemoteDataSource.clearChat(id = id).mapToDomainModel()
    }

    override suspend fun archiveChat(id: String): BaseResponse {
        return chatRemoteDataSource.changeArchiveStatus(id = id, archiveStatus = false).mapToDomainModel()
    }

    override suspend fun unarchiveChat(id: String): BaseResponse {
        return chatRemoteDataSource.changeArchiveStatus(id = id, archiveStatus = true).mapToDomainModel()
    }

    override suspend fun renameChat(id: String, name: String): BaseResponse {
        return chatRemoteDataSource.renameChat(id = id, name = name).mapToDomainModel()
    }

    override suspend fun createChat(id: String, title: String): CreateChatResult {
        return chatRemoteDataSource.createChat(vaultId = id, title = title).mapToDomainModel()
    }

    override suspend fun sendMessage(message: String): SendMessageResult {
        return chatRemoteDataSource.sendMessage(message = message).mapToDomainModel()
    }

}