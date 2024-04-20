package com.example.domain.usecases.chat

import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class DeleteChatUseCase @Inject constructor(
    private val repository: ChatRepository,
) {
    suspend fun execute(id: String) {
        repository.deleteChat(id = id)
    }
}