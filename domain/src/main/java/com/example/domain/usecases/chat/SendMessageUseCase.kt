package com.example.domain.usecases.chat

import com.example.domain.model.chat.SendMessageResult
import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: ChatRepository,
) {
    suspend fun execute(message: String, chatId: String, vaultId: String): SendMessageResult {
        return repository.sendMessage(message = message, chatId = chatId, vaultId = vaultId)
    }
}