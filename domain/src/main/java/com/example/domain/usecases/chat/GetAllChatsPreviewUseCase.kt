package com.example.domain.usecases.chat

import com.example.domain.model.ChatsPreviewModel
import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class GetAllChatsPreviewUseCase @Inject constructor(
    private val repository: ChatRepository,
) {
    suspend fun execute(): List<ChatsPreviewModel> {
        return repository.getChatsPreview()
    }
}