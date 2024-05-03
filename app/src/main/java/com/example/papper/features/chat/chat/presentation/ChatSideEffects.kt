package com.example.papper.features.chat.chat.presentation

sealed class ChatSideEffects {
    object ShowLoading : ChatSideEffects()
    object ShowSuccess : ChatSideEffects()
    object ShowError : ChatSideEffects()
    object ShowErrorSendMsgToast : ChatSideEffects()
    object ShowErrorRenameToast: ChatSideEffects()
    object ShowErrorDeleteChatToast: ChatSideEffects()
    data class RenameChat(val id: String, val title: String) : ChatSideEffects()
    data class DeleteChatAndNavigateToChatsScreen(val id: String) : ChatSideEffects()
    data class NavigateToStorageScreen(val storageId: String): ChatSideEffects()

}