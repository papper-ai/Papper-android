package com.example.domain.usecases.chat

import com.example.domain.model.chat.ChatModelResult
import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatByIdUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun execute(id: String) : ChatModelResult {
        return repository.getChatById(id)
    }
}