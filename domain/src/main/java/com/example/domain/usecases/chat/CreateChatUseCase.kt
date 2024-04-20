package com.example.domain.usecases.chat

import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class CreateChatUseCase @Inject constructor(
    private val repository: ChatRepository,
) {

    suspend fun execute() {
        repository.createChat()
    }
}