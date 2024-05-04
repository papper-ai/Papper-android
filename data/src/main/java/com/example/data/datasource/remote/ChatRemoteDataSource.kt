package com.example.data.datasource.remote

import com.example.data.api.ChatApiService
import com.example.data.base.BaseResponse
import com.example.data.model.chat.ChangeArchiveStatusChatBody
import com.example.data.model.chat.GetChatResponseResult
import com.example.data.model.chat.ChatsPreviewResponseResult
import com.example.data.model.chat.CreateChatBody
import com.example.data.model.chat.CreateChatResponseResult
import com.example.data.model.chat.RenameChatBody
import com.example.data.model.chat.SendMessageResponse
import com.example.data.utils.BaseResponseImitation
import kotlinx.coroutines.delay
import javax.inject.Inject

class ChatRemoteDataSource @Inject constructor(
    private val apiService: ChatApiService,
) {

    suspend fun createChat(vaultId: String, title: String): CreateChatResponseResult {
        lateinit var result: CreateChatResponseResult

        val resultFromApi = apiService.createChat(
            body = CreateChatBody(
                id = vaultId,
                title = title
            )
        )

        if (resultFromApi.isSuccessful) {
            result = CreateChatResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = true,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message()
                ),
                id = resultFromApi.body()?.id ?: ""
            )
        } else {
            result = CreateChatResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message()
                ),
                id = ""
            )
        }

        delay(1000)
        return result
    }

    suspend fun fetchChatsPreview(): ChatsPreviewResponseResult {
        lateinit var result: ChatsPreviewResponseResult
        val resultFromApi = apiService.getChatsPreview()
        if (resultFromApi.isSuccessful) {
            result = ChatsPreviewResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = true,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message()
                ),
                listOfChatsPreview = resultFromApi.body() ?: emptyList()
            )
        } else {
            result = ChatsPreviewResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message()
                ),
                listOfChatsPreview = emptyList()
            )
        }

        return result
    }

    suspend fun fetchArchiveChatsPreview(): ChatsPreviewResponseResult {
        lateinit var result: ChatsPreviewResponseResult
        val resultFromApi = apiService.getArchiveChatsPreview()
        if (resultFromApi.isSuccessful) {
            result = ChatsPreviewResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = true,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message()
                ),
                listOfChatsPreview = resultFromApi.body() ?: emptyList()
            )
        } else {
            result = ChatsPreviewResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message()
                ),
                listOfChatsPreview = emptyList()
            )
        }

        return result
    }

    suspend fun fetchChatById(id: String): GetChatResponseResult {
        lateinit var result: GetChatResponseResult
        val resultFromApi = apiService.getChatById(id)
        if (resultFromApi.isSuccessful) {
            result = GetChatResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = true,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message()
                ),
                id = resultFromApi.body()?.id ?: "",
                title = resultFromApi.body()?.title ?: "",
                listOfMessages = resultFromApi.body()?.chatHistory?.history ?: emptyList(),
                storageId = resultFromApi.body()?.vaultId ?: "",
                isArchived = resultFromApi.body()?.isArchived ?: false
            )
        } else {
            result = GetChatResponseResult(
                baseResponse = BaseResponse(
                    isSuccess = false,
                    code = resultFromApi.code().toString(),
                    msg = resultFromApi.message()
                ),
                id = "",
                title = "",
                listOfMessages = emptyList(),
                storageId = "",
                isArchived = false
            )
        }
        return result
    }

    suspend fun renameChat(id: String, name: String): BaseResponse {
        lateinit var result: BaseResponse
        val resultFromApi = apiService.renameChat(RenameChatBody(chatId = id, name = name))
        if (resultFromApi.isSuccessful) {
            result = BaseResponse(
                isSuccess = true,
                code = resultFromApi.code().toString(),
                msg = resultFromApi.message()
            )
        } else {
            result = BaseResponse(
                isSuccess = false,
                code = resultFromApi.code().toString(),
                msg = resultFromApi.message()
            )
        }
        return result
    }

    suspend fun changeArchiveStatus(id: String, archiveStatus: Boolean): BaseResponse {
        lateinit var result: BaseResponse
        val resultFromApi = apiService.archiveChat(ChangeArchiveStatusChatBody(chatId = id, archiveAction = if (archiveStatus) "unarchive" else "archive"))
        if (resultFromApi.isSuccessful) {
            result = BaseResponse(
                isSuccess = true,
                code = resultFromApi.code().toString(),
                msg = resultFromApi.message()
            )
        } else {
            result = BaseResponse(
                isSuccess = false,
                code = resultFromApi.code().toString(),
                msg = resultFromApi.message()
            )
        }

        return result
    }

    suspend fun clearChat(id: String): BaseResponse {
        lateinit var result: BaseResponse
        val resultFromApi = apiService.clearChat(id = id)
        if (resultFromApi.isSuccessful) {
            result = BaseResponse(
                isSuccess = true,
                code = resultFromApi.code().toString(),
                msg = resultFromApi.message()
            )
        } else {
            result = BaseResponse(
                isSuccess = false,
                code = resultFromApi.code().toString(),
                msg = resultFromApi.message()
            )
        }

        return result
    }

    suspend fun deleteChat(id: String): BaseResponse {
        lateinit var result: BaseResponse
        val resultFromApi = apiService.deleteChat(id = id)
        if (resultFromApi.isSuccessful) {
            result = BaseResponse(
                isSuccess = true,
                code = resultFromApi.code().toString(),
                msg = resultFromApi.message()
            )
        } else {
            result = BaseResponse(
                isSuccess = false,
                code = resultFromApi.code().toString(),
                msg = resultFromApi.message()
            )
        }

        return result
    }

    suspend fun sendMessage(message: String): SendMessageResponse {
        delay(1000)
        return SendMessageResponse(baseResponse = BaseResponseImitation.execute())
    }

}