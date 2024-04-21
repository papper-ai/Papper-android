package com.example.data.datasource

import com.example.data.api.ApiService
import com.example.data.model.ChatPreviewModel
import com.example.data.model.ChatResponse
import com.example.data.model.ChatsPreviewResponse
import com.example.data.model.Message
import com.example.data.model.SendMessageResponse
import com.example.data.utils.BaseResponseImitation
import kotlinx.coroutines.delay
import javax.inject.Inject

class ChatRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun fetchChatsPreview(): ChatsPreviewResponse {
        val list = mutableListOf<ChatPreviewModel>()
        for (i in 1..15) {
            delay(100)
            list.add(
                ChatPreviewModel(
                    id = i.toString(),
                    title = "title $i from remote",
                    lastMessage = "last msg"
                )
            )
        }
        return ChatsPreviewResponse(baseResponse = BaseResponseImitation.execute(), listOfChatsPreview = list)
    }

    suspend fun fetchChatById(id: String): ChatResponse {
        delay(1500)
        val result = ChatResponse(
            baseResponse = BaseResponseImitation.execute(),
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
            //emptyList(),
            storageId = id,
        )
        return result
    }

    suspend fun sendMessage(message: String): SendMessageResponse {
        delay(1000)
        return SendMessageResponse(baseResponse = BaseResponseImitation.execute())
    }

}