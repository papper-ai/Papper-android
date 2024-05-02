package com.example.data.api

import com.example.data.model.chat.ChatPreviewItem
import com.example.data.model.chat.GetChatResponse
import com.example.data.model.chat.RenameChatBody
import com.example.data.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ChatApiService {

    @GET(Constants.GET_CHATS_PREVIEW)
    @Headers("Accept: application/json")
    suspend fun getChatsPreview(): Response<List<ChatPreviewItem>>


    @GET("${Constants.GET_CHAT_BY_ID}/{id}")
    suspend fun getChatById(
        @Path("id") id: String,
    ): Response<GetChatResponse>

    @PATCH(Constants.RENAME_CHAT)
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun renameChat(
        @Body body: RenameChatBody
    ): Response<Any>
}