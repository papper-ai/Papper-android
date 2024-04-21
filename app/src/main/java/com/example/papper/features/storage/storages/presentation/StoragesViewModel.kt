package com.example.papper.features.storage.storages.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.storage.GetAllStoragesPreviewUseCase
import com.example.papper.features.storage.storages.model.mapToPresentationModel
import com.example.papper.navigation.Screens
import com.example.papper.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StoragesViewModel @Inject constructor(
    private val getAllStoragesPreviewUseCase: GetAllStoragesPreviewUseCase
) : ViewModel(), ContainerHost<StoragesState, StoragesSideEffects> {

    override val container = container<StoragesState, StoragesSideEffects>(StoragesState())

    val storagesScreenState = mutableStateOf<StoragesScreenState>(StoragesScreenState.Loading)

    init {
        loadData()
    }

    fun loadData() = intent {
        postSideEffect(StoragesSideEffects.ShowLoading)
        val result = withContext(AppDispatchers.io) {
            getAllStoragesPreviewUseCase.execute().mapToPresentationModel()
        }
        if (result.isSuccess) {
            reduce {
                state.copy(listOfStorages = result.list)
            }
            postSideEffect(StoragesSideEffects.ShowSuccess)
        }
        else {
            postSideEffect(StoragesSideEffects.ShowError)
        }

    }

    fun onStorageItemClick(id: String, lastDestination: String) = intent {
        when (lastDestination) {
            Screens.ChatsScreen.route -> postSideEffect(StoragesSideEffects.NavigateToStorageScreen(id = id))
            Screens.CreateChatScreen.route -> postSideEffect(StoragesSideEffects.NavigateToCreateChatScreen(id = id))
        }
    }
}