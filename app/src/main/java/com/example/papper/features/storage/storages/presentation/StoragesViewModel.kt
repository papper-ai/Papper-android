package com.example.papper.features.storage.storages.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.data.datasource.StorageRemoteDataSource
import com.example.data.repository.StorageRepositoryImpl
import com.example.domain.usecases.chat.GetAllStoragesPreviewUseCase
import com.example.papper.features.storage.storages.model.mapToPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StoragesViewModel @Inject constructor() : ViewModel(), ContainerHost<StoragesState, StoragesSideEffects> {

    override val container = container<StoragesState, StoragesSideEffects>(StoragesState())

    val storagesScreenState = mutableStateOf<StoragesScreenState>(StoragesScreenState.Loading)

    init {
        loadData()
    }

    fun loadData() = intent {
        val getAllStoragesPreviewUseCase = GetAllStoragesPreviewUseCase(
            repository = StorageRepositoryImpl(
                storageDataSource = StorageRemoteDataSource()
            )
        )
        val result = getAllStoragesPreviewUseCase.execute().mapToPresentationModel()
        postSideEffect(StoragesSideEffects.ShowLoading)
        reduce {
            state.copy(listOfStorages = result)
        }
        postSideEffect(StoragesSideEffects.ShowSuccess)
    }
}