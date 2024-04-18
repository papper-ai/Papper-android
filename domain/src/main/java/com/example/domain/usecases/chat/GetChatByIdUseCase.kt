package com.example.domain.usecases.chat

import com.example.domain.model.ChatModel
import com.example.domain.repository.ChatRepository

class GetChatByIdUseCase(
    val repository: ChatRepository
) {
    suspend fun execute(id: String) : ChatModel {
        return repository.getChatById(id)
    }
}