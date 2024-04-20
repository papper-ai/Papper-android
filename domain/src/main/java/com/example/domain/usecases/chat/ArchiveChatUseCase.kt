package com.example.domain.usecases.chat

import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class ArchiveChatUseCase @Inject constructor(
    private val repository: ChatRepository,
) {
    suspend fun execute(id: String) {
        repository.archiveChat(id = id)
    }
}