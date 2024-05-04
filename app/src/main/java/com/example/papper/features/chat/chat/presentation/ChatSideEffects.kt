package com.example.papper.features.chat.chat.presentation

sealed class ChatSideEffects {
    object ShowLoading : ChatSideEffects()
    object ShowSuccess : ChatSideEffects()
    object ShowError : ChatSideEffects()
    object ShowErrorSendMsgToast : ChatSideEffects()
    object ShowErrorRenameToast: ChatSideEffects()
    object ShowErrorDeleteChatToast: ChatSideEffects()
    object RenameChat: ChatSideEffects()
    data class DeleteChatAndNavigateToChatsScreen(val id: String) : ChatSideEffects()
    data class NavigateToStorageScreen(val storageId: String): ChatSideEffects()
    data class ChangeArchiveStatus(val status: Boolean): ChatSideEffects()

}