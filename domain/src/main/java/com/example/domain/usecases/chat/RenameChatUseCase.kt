package com.example.domain.usecases.chat

import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class RenameChatUseCase @Inject constructor(
    private val repository: ChatRepository,
) {
    suspend fun execute(id: String, title: String) {
        repository.renameChat(id = id, name = title)
    }
}