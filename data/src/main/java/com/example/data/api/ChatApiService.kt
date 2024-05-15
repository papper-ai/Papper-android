package com.example.data.api

import com.example.data.model.chat.ChangeArchiveStatusChatBody
import com.example.data.model.chat.ChatPreviewItem
import com.example.data.model.chat.CreateChatBody
import com.example.data.model.chat.CreateChatResponse
import com.example.data.model.chat.GetChatResponse
import com.example.data.model.chat.RenameChatBody
import com.example.data.model.chat.SendMessageBody
import com.example.data.model.chat.SendMessageResponse
import com.example.data.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatApiService {

    @POST(Constants.CREATE_CHAT)
    @Headers("accept: application/json", "Content-Type: application/json")
    suspend fun createChat(
        @Body body: CreateChatBody,
    ): Response<CreateChatResponse>

    @GET(Constants.GET_CHATS_PREVIEW)
    @Headers("Accept: application/json")
    suspend fun getChatsPreview(): Response<List<ChatPreviewItem>>

    @GET(Constants.GET_ARCHIVE_CHATS_PREVIEW)
    @Headers("Accept: application/json")
    suspend fun getArchiveChatsPreview(): Response<List<ChatPreviewItem>>

    @GET("${Constants.GET_CHAT_BY_ID}/{id}")
    suspend fun getChatById(
        @Path("id") id: String,
    ): Response<GetChatResponse>

    @PATCH(Constants.RENAME_CHAT)
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun renameChat(
        @Body body: RenameChatBody
    ): Response<Any>

    @PATCH(Constants.CHANGE_ARCHIVE_STATUS_CHAT)
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun archiveChat(
        @Body body: ChangeArchiveStatusChatBody
    ): Response<Any>

    @POST("${Constants.CLEAR_CHAT}/{id}")
    suspend fun clearChat(
        @Path("id") id: String
    ): Response<Any>

    @DELETE("${Constants.DELETE_CHAT}/{id}")
    suspend fun deleteChat(
        @Path("id") id: String
    ): Response<Any>

    @POST(Constants.SEND_MESSAGE)
    suspend fun sendMessage(
        @Body body: SendMessageBody,
    ): Response<SendMessageResponse>

}