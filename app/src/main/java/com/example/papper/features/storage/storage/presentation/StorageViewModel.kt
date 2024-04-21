package com.example.papper.features.storage.storage.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.storage.GetStorageByIdUseCase
import com.example.papper.features.storage.storage.model.mapToPresentationModel
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
class StorageViewModel @Inject constructor(
    private val getStorageByIdUseCase: GetStorageByIdUseCase,
) : ViewModel(), ContainerHost<StorageState, StorageSideEffects> {

    override val container = container<StorageState, StorageSideEffects>(StorageState())

    var id: String? = null
    val storageScreenState = mutableStateOf<StorageScreenState>(StorageScreenState.Loading)

    init {
        getData()
    }

    fun getData() = intent {
        postSideEffect(StorageSideEffects.ShowLoading)
        val result = withContext(AppDispatchers.io) {
            id?.let { getStorageByIdUseCase.execute(id = it).mapToPresentationModel() }
        }
        if (result != null) {
            if (result.isSuccess) {
                reduce {
                    state.copy(title = result.title, listOfStorages = result.listOfFiles)
                }
                postSideEffect(StorageSideEffects.ShowSuccess)
            }
            else {
                postSideEffect(StorageSideEffects.ShowError)
            }
        }
        else {
            postSideEffect(StorageSideEffects.ShowError)
        }
    }

}