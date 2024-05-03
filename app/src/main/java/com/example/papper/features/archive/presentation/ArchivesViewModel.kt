package com.example.papper.features.archive.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.chat.GetAllArchiveChatsPreviewUseCase
import com.example.papper.features.chat.chats.model.mapToPresentationModel
import com.example.papper.utils.AppDispatchers
import com.example.papper.utils.CheckNetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ArchivesViewModel @Inject constructor(
    private val checkNetworkStatus: CheckNetworkStatus,
    private val getAllArchiveChatsPreviewUseCase: GetAllArchiveChatsPreviewUseCase
) : ViewModel(), ContainerHost<ArchivesState, ArchivesSideEffects> {

    override val container = container<ArchivesState, ArchivesSideEffects>(ArchivesState())

    val archivesScreenState = mutableStateOf<ArchivesScreenState>(ArchivesScreenState.Loading)

    init {
        getData()
    }

    fun getData() = intent {
        postSideEffect(ArchivesSideEffects.ShowLoading)
        checkNetworkStatus.isNetworkConnected(
            onSuccess = {
                val result = withContext(AppDispatchers.io) {
                    getAllArchiveChatsPreviewUseCase.execute().mapToPresentationModel()
                }
                if (result.isSuccess) {
                    reduce {
                        state.copy(listOfChats = result.list)
                    }
                    postSideEffect(ArchivesSideEffects.ShowSuccess)
                } else {
                    postSideEffect(ArchivesSideEffects.ShowError)
                }
            },
            onFail = {
                postSideEffect(ArchivesSideEffects.ShowNetworkConnectionError)
            }
        )

    }

}