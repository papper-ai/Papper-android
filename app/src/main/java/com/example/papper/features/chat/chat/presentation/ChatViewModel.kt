package com.example.papper.features.chat.chat.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.chat.ArchiveChatUseCase
import com.example.domain.usecases.chat.ClearChatUseCase
import com.example.domain.usecases.chat.DeleteChatUseCase
import com.example.domain.usecases.chat.GetChatByIdUseCase
import com.example.domain.usecases.chat.RenameChatUseCase
import com.example.domain.usecases.chat.SendMessageUseCase
import com.example.domain.usecases.chat.UnarchiveChatUseCase
import com.example.papper.features.chat.chat.model.Message
import com.example.papper.features.chat.chat.model.MessageSender
import com.example.papper.features.chat.chat.model.mapToPresentationModel
import com.example.papper.features.chat.chat.view.success_data.bottom_bar.MessageStatus
import com.example.papper.utils.AppDispatchers
import com.example.papper.utils.CheckNetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val networkStatus: CheckNetworkStatus,
    private val getChatByIdUseCase: GetChatByIdUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val renameChatUseCase: RenameChatUseCase,
    private val archiveChatUseCase: ArchiveChatUseCase,
    private val unarchiveChatUseCase: UnarchiveChatUseCase,
    private val clearChatUseCase: ClearChatUseCase,
    private val deleteChatUseCase: DeleteChatUseCase,
) : ViewModel(), ContainerHost<ChatState, ChatSideEffects> {

    override val container: Container<ChatState, ChatSideEffects> = container<ChatState, ChatSideEffects>(ChatState())

    var id: String? = null
    val chatScreenState = mutableStateOf<ChatScreenState>(ChatScreenState.Loading)
    val successState = mutableStateOf<SuccessState>(SuccessState.EmptyChat)
    val isSendingMessage = mutableStateOf<MessageStatus>(MessageStatus(false, false))

    init {
        loadData()
    }

    fun loadData() = intent {
        //delay для того, чтобы успел подтянуться id с прошлого экрана
        delay(10)
        postSideEffect(ChatSideEffects.ShowLoading)
        val result = withContext(AppDispatchers.io) {
            id?.let { getChatByIdUseCase.execute(it).mapToPresentationModel() }
        }
        if (result != null) {
            if (result.isSuccess) {
                reduce {
                    state.copy(
                        title = result.title,
                        listOfMessages = result.listOfMessages,
                        storageId = result.storageId,
                        isArchived = result.isArchived,
                    )
                }
                if (state.listOfMessages.isNotEmpty()) {
                    successState.value = SuccessState.NotEmptyChat
                } else {
                    successState.value = SuccessState.EmptyChat
                }
                postSideEffect(ChatSideEffects.ShowSuccess)
            } else {
                postSideEffect(ChatSideEffects.ShowError)
            }
        } else {
            postSideEffect(ChatSideEffects.ShowError)
        }
    }

    fun updateMessage(message: String) = intent {
        reduce {
            state.copy(message = message)
        }
    }

    fun renameChat(title: String) = intent {
        val result = withContext(AppDispatchers.io) {
            renameChatUseCase.execute(id = id!!, title = title)
        }
        if (result.isSuccess) {
            reduce {
                state.copy(title = title)
            }
            postSideEffect(ChatSideEffects.RenameChat(id = id!!, title = title))
        } else {
            postSideEffect(ChatSideEffects.ShowErrorRenameToast)
        }

    }

    fun sendMessage(text: String) = intent {
        isSendingMessage.value = MessageStatus(isSendingMsg = true, isSuccess = false)

        val result = withContext(AppDispatchers.io) {
            sendMessageUseCase.execute(message = text)
        }

        if (result.isSuccess) {
            if (state.listOfMessages.isEmpty()) {
                successState.value = SuccessState.NotEmptyChat
            }
            reduce {
                state.copy(listOfMessages = state.listOfMessages.plus(Message(text = text, from = MessageSender.User)), message = "")
            }
            isSendingMessage.value = MessageStatus(isSendingMsg = false, isSuccess = true)
        } else {
            isSendingMessage.value = MessageStatus(isSendingMsg = false, isSuccess = false)
            postSideEffect(ChatSideEffects.ShowErrorSendMsgToast)
        }
    }


    //todo расскоментить, когда появиться возможность прикреплять файл в чате
//    fun navigateToStorageScreenForResult() = intent {
//        postSideEffect(ChatSideEffects.NavigateToStorageScreen(storageId = state.storageId))
//    }

    fun navigateToStorageScreen() = intent {
        postSideEffect(ChatSideEffects.NavigateToStorageScreen(storageId = state.storageId))
    }

    fun changeArchiveStatus() = intent {
        val result = withContext(AppDispatchers.io) {
            if (state.isArchived) {
                unarchiveChatUseCase.execute(id =  id!!)
            } else {
                archiveChatUseCase.execute(id =  id!!)
            }
        }
        if (result.isSuccess) {
            reduce {
                state.copy(isArchived = !state.isArchived)
            }
        } else {
            //todo сделать toast
        }
    }

    fun clearChatHistory() = intent {
        val result = withContext(AppDispatchers.io) {
            clearChatUseCase.execute(id = id!!)
        }
        if (result.isSuccess) {
            reduce {
                state.copy(listOfMessages = emptyList())
            }
            successState.value = SuccessState.EmptyChat
        } else {
            //todo сделать toast
        }


    }

    fun deleteChat() = intent {
        networkStatus.isNetworkConnected(
            onSuccess = {
                val result = withContext(AppDispatchers.io) {
                    deleteChatUseCase.execute(id = id!!)
                }
                if (result.isSuccess) {
                    postSideEffect(ChatSideEffects.DeleteChatAndNavigateToChatsScreen(id = id!!))
                } else {
                    postSideEffect(ChatSideEffects.ShowErrorDeleteChatToast)
                }
            },
            onFail = {
                postSideEffect(ChatSideEffects.ShowErrorDeleteChatToast)
            }
        )
    }

}