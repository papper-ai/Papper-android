package com.example.domain.usecases.chat

import com.example.domain.model.BaseResponse
import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class ArchiveChatUseCase @Inject constructor(
    private val repository: ChatRepository,
) {
    suspend fun execute(id: String): BaseResponse {
        return repository.archiveChat(id = id)
    }
}