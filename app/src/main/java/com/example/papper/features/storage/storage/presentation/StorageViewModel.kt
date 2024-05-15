package com.example.papper.features.storage.storage.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.storage.AddFileInStorageById
import com.example.domain.usecases.storage.DeleteFileUseCase
import com.example.domain.usecases.storage.DeleteStorageUseCase
import com.example.domain.usecases.storage.GetStorageByIdUseCase
import com.example.domain.usecases.storage.RenameStorageUseCase
import com.example.papper.features.storage.storage.model.FilePresentationModel
import com.example.papper.features.storage.storage.model.mapToPresentationModel
import com.example.papper.utils.AppDispatchers
import com.example.papper.utils.CheckNetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val checkNetworkStatus: CheckNetworkStatus,
    private val getStorageByIdUseCase: GetStorageByIdUseCase,
    private val addFileInStorageById: AddFileInStorageById,
    private val deleteFileUseCase: DeleteFileUseCase,
    private val deleteStorageUseCase: DeleteStorageUseCase,
    private val renameStorageUseCase: RenameStorageUseCase
) : ViewModel(), ContainerHost<StorageState, StorageSideEffects> {

    override val container = container<StorageState, StorageSideEffects>(StorageState())

    var id: String? = null
    val storageScreenState = mutableStateOf<StorageScreenState>(StorageScreenState.Loading)
    val fileDeleteLoading = mutableStateOf<List<Pair<Boolean, String>>>(emptyList())
    val btnLoading = mutableStateOf<Boolean>(false)

    init {
        getData()
    }

    fun getData() = intent {
        checkNetworkStatus.isNetworkConnected(
            onSuccess = {
                postSideEffect(StorageSideEffects.ShowLoading)
                delay(20)
                val result = withContext(AppDispatchers.io) {
                    id?.let { getStorageByIdUseCase.execute(id = it).mapToPresentationModel() }
                }
                if (result != null) {
                    if (result.isSuccess) {
                        reduce {
                            state.copy(title = result.title, setOfStorages = result.listOfFiles.toSet())
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
            },
            onFail = {
                postSideEffect(StorageSideEffects.ShowNetworkConnectionError)
            },
        )
    }

    fun deleteFile(file: FilePresentationModel) = intent {
        fileDeleteLoading.value = fileDeleteLoading.value.plus(Pair(true, file.id))
        val result = withContext(AppDispatchers.io) {
            deleteFileUseCase.execute(vaultId = id!!, documentId = file.id).mapToPresentationModel()
        }
        if (result.isSuccess) {
            reduce {
                state.copy(setOfStorages = state.setOfStorages.minus(file))
            }
            fileDeleteLoading.value = fileDeleteLoading.value.minus(Pair(true, file.id))
        }
        else {
            fileDeleteLoading.value = fileDeleteLoading.value.minus(Pair(true, file.id))
            postSideEffect(StorageSideEffects.ShowToastDeleteFileError(file.title))
        }

    }

    fun addFile(file: File) = intent {
        btnLoading.value = true
        var flag: Boolean = true
        for (item in state.setOfStorages) {
            if (item.title == file.name) {
                flag = false
                break
            }
        }
        if (flag) {
            val result = withContext(AppDispatchers.io) {
                id?.let {
                    addFileInStorageById.execute(
                        storageId = it,
                        file = file,
                    )
                }?.mapToPresentationModel()
            }
            if (result?.isSuccess == true) {
                reduce {
                    state.copy(
                        setOfStorages = state.setOfStorages.plus(
                            FilePresentationModel(
                                id = result.id,
                                title = file.name,
                                text = result.text,
                            )
                        )
                    )
                }
            }
            else {
                postSideEffect(StorageSideEffects.ShowToastAddFileError(title = file.name))
            }
        }
        else {
            postSideEffect(StorageSideEffects.ShowToastFileAlreadyExist(title = file.name))
        }
        btnLoading.value = false
    }

    fun deleteStorage() = intent {
        val result = withContext(AppDispatchers.io) {
            deleteStorageUseCase.execute(id = id!!)
        }
        if (result.isSuccess) {
            postSideEffect(StorageSideEffects.DeleteStorageAndNavigateToStoragesScreen(id!!))
        }
        else {
            postSideEffect(StorageSideEffects.ShowToastDeleteStorageError)
        }
    }

    fun renameStorage(title: String) = intent {
        val result = withContext(AppDispatchers.io) {
            renameStorageUseCase.execute(id = id!!, title = title)
        }
        if (result.isSuccess) {
            reduce {
                state.copy(title = title)
            }
            postSideEffect(StorageSideEffects.RenameStorage(id!!, title))
        } else {
            postSideEffect(StorageSideEffects.ShowRenameErrorToast)
        }
    }

}