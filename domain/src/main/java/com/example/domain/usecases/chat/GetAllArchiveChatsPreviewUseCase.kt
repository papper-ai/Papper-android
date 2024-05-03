package com.example.domain.usecases.chat

import com.example.domain.model.chat.ChatsPreviewModelResult
import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class GetAllArchiveChatsPreviewUseCase @Inject constructor(
    private val repository: ChatRepository,
) {
    suspend fun execute(): ChatsPreviewModelResult {
        return repository.getArchiveChatsPreview()
    }
}