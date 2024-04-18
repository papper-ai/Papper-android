package com.example.data.datasource

import com.example.data.model.ChatPreviewModel
import com.example.data.model.ChatResponse
import com.example.data.model.ChatsPreviewResponse
import com.example.data.model.Message
import kotlinx.coroutines.delay

class ChatRemoteDataSource {

    suspend fun fetchChatsPreview(): ChatsPreviewResponse {
        val list = mutableListOf<ChatPreviewModel>()
        for (i in 1..15) {
            delay(100)
            list.add(
                ChatPreviewModel(
                    id = i.toString(),
                    title = "$i title from remote",
                    lastMessage = "last msg"
                )
            )
        }
        return ChatsPreviewResponse(listOfChatsPreview = list)
    }

    suspend fun fetchChatById(id: String): ChatResponse {
        delay(1500)
        val result = ChatResponse(
            id = id,
            title = "title $id from remote",
            listOfMessages = listOf(
                Message(text = "some text from bot", from = "Bot"),
                Message(text = "some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot", from = "Bot"),
                Message(text = "some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from usersome text from usersome text from user", from = "User"),
                Message(text = "text", from = "Bot"),
                Message(text = "text from user", from = "User"),
                Message(text = "some text from bot", from = "Bot"),
                Message(text = "some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot", from = "Bot"),
                Message(text = "some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from usersome text from usersome text from user", from = "User"),
                Message(text = "text", from = "Bot"),
                Message(text = "text from user", from = "User"),
                Message(text = "some text from bot", from = "Bot"),
                Message(text = "some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot some text from bot", from = "Bot"),
                Message(text = "some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from user some text from usersome text from usersome text from user", from = "User"),
                Message(text = "text", from = "Bot"),
                Message(text = "text from user", from = "User"),
            ),
            storageId = id
        )
        return result
    }

}