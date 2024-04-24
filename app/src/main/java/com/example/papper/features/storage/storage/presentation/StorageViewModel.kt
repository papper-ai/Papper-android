package com.example.papper.features.storage.storage.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.storage.AddFileInStorageById
import com.example.domain.usecases.storage.GetStorageByIdUseCase
import com.example.papper.features.storage.storage.model.FilePresentationModel
import com.example.papper.features.storage.storage.model.mapToPresentationModel
import com.example.papper.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val getStorageByIdUseCase: GetStorageByIdUseCase,
    private val addFileInStorageById: AddFileInStorageById,
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
    }

    fun deleteFile(file: FilePresentationModel) = intent {
        reduce {
            state.copy(setOfStorages = state.setOfStorages.minus(file))
        }
    }

    fun addFile(file: File) = intent {
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
        /*Добавить логику
        два set`a
        один который хранит все добавленные файлы
        второй хранит файлы, уже посланные на сервер

        Алгоритм добавления файла
        -Сравнить имена файлов из state и первого set`a
            -Если имя не совпдает ни с одним элементом добавляем в set
            -Если совпадает, игнорируем item


        * Запрос сохранения файла на сервер
        * Проверка, все ли успешно
            * Если все успешно - reduce файл
            * Иначе toast с ошибкой
        * */

    }

}