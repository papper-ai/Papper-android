package com.example.papper.features.storage.storages.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.storage.GetAllStoragesPreviewUseCase
import com.example.papper.features.storage.storages.model.mapToPresentationModel
import com.example.papper.navigation.ChatsScreen
import com.example.papper.navigation.CreateChatScreen
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
class StoragesViewModel @Inject constructor(
    private val checkNetworkStatus: CheckNetworkStatus,
    private val getAllStoragesPreviewUseCase: GetAllStoragesPreviewUseCase
) : ViewModel(), ContainerHost<StoragesState, StoragesSideEffects> {

    override val container = container<StoragesState, StoragesSideEffects>(StoragesState())

    val storagesScreenState = mutableStateOf<StoragesScreenState>(StoragesScreenState.Loading)


    fun loadData() = intent {
        checkNetworkStatus.isNetworkConnected(
            onSuccess = {
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
            },
            onFail = {
                postSideEffect(StoragesSideEffects.ShowNetworkConnectionError)
            },
        )
    }

    fun refreshData() = intent {
        checkNetworkStatus.isNetworkConnected(
            onSuccess = {
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
            },
            onFail = {
                postSideEffect(StoragesSideEffects.ShowNetworkConnectionError)
            },
        )
    }

    fun onStorageItemClick(id: String, lastDestination: String) = intent {
        val temp = lastDestination.substringBefore("?")

        Log.d("TAG", "onStorageItemClick: ${temp}")
        Log.d("TAG", "onStorageItemClick: ${CreateChatScreen.toString().substringBefore("$")}")
        when (temp) {
            //ЭТО МЕСТО
            ChatsScreen.toString() -> postSideEffect(StoragesSideEffects.NavigateToStorageScreen(id = id))
            CreateChatScreen.toString().substringBefore("$") -> postSideEffect(StoragesSideEffects.NavigateToCreateChatScreen(id = id))
        }
    }

    fun deleteStorage(id: String) = intent {
        val newList = state.listOfStorages.filter { it.id != id }
        reduce {
            state.copy(listOfStorages = newList)
        }
    }

    fun reneameStorage(id: String, title: String) = intent {
        val newList = state.listOfStorages.map { if (it.id == id) it.copy(title = title) else it }
        reduce {
            state.copy(listOfStorages = newList)
        }
    }

    fun navigateToCreateStorageScreen() = intent {
        postSideEffect(StoragesSideEffects.NavigateToCreateStorageScreen)
    }

}