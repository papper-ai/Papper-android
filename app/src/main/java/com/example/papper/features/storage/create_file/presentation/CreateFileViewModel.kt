package com.example.papper.features.storage.create_file.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toFile
import androidx.lifecycle.ViewModel
import com.example.domain.model.storage.ConvertPhotoModel
import com.example.domain.usecases.storage.ConvertPhotoIntoTextUseCase
import com.example.papper.features.storage.create_file.model.PhotoModel
import com.example.papper.features.storage.create_storage.view.attach_files.CreateStorageBtnStatus
import com.example.papper.utils.CheckNetworkStatus
import com.example.papper.utils.toBase64
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreateFileViewModel @Inject constructor(
    private val networkStatus: CheckNetworkStatus,
    private val convertPhotoIntoTextUseCase: ConvertPhotoIntoTextUseCase,
): ViewModel(), ContainerHost<CreateFileState, CreateFileSideEffects> {

    override val container = container<CreateFileState, CreateFileSideEffects>(CreateFileState())
    val createFileScreenState = mutableStateOf<CreateFileScreenState>(CreateFileScreenState.TypingTitle)
    val convertPhotoBtnStatus = mutableStateOf<CreateStorageBtnStatus>(CreateStorageBtnStatus(isLoading = false, isEnable = true))
    val createFileBtnStatus = mutableStateOf<CreateStorageBtnStatus>(CreateStorageBtnStatus(isLoading = false, isEnable = false))

    fun updateTitle(title: String) = intent {
        reduce {
            state.copy(title = title)
        }
    }

    fun toAttachPhotos() = intent {
        postSideEffect(CreateFileSideEffects.ShowAttachPhotos)
    }

    fun addPhoto(image: Uri) = intent {
        val photo = PhotoModel(
            id = state.listOfPhotos.size+1,
            imageUri = image,
            text = "",
        )

        reduce {
            state.copy(listOfPhotos = state.listOfPhotos.plus(photo))
        }
    }

    fun deletePhoto(photo: PhotoModel) = intent {
        val result = mutableListOf<PhotoModel>()
        var index = 1
        for (item in state.listOfPhotos.minus(photo)) {
            result.add(
                PhotoModel(
                    id = index,
                    imageUri = item.imageUri,
                    text = item.text,
                )
            )
            index++
        }

        reduce {
            state.copy(listOfPhotos = result)
        }
    }

    fun replacePhoto(oldPhoto: PhotoModel, newPhoto: PhotoModel) = intent {
        val result: MutableList<PhotoModel> = state.listOfPhotos.toMutableList()
        result[result.indexOf(oldPhoto)] = newPhoto

        reduce {
            state.copy(listOfPhotos = result)
        }
    }

    fun convertPhotos() = intent {
        networkStatus.isNetworkConnected(
            onSuccess = {
                convertPhotoBtnStatus.value = CreateStorageBtnStatus(isLoading = true, isEnable = false)
                val resultFromApi = convertPhotoIntoTextUseCase.execute(
                    listOfPhoto = state.listOfPhotos.map {
                        ConvertPhotoModel(
                            id = it.id,
                            //photo = BitmapFactory.decodeFile(it.imageUri.toFile().path).toBase64(),
                            photo = it.imageUri.toString()
                        )
                    }
                )
                if (resultFromApi.isSuccess) {
                    val newList = state.listOfPhotos.toMutableList()
                    for (convertedPhoto in resultFromApi.list) {
                        newList[convertedPhoto.id-1] = PhotoModel(
                            id = convertedPhoto.id,
                            imageUri = newList[convertedPhoto.id-1].imageUri,
                            text = convertedPhoto.text,
                        )
                    }

                    reduce {
                        state.copy(listOfPhotos = newList)
                    }
                    for (text in state.listOfPhotos) {
                        Log.e("Test", "convertPhotos: ${text.text}\n")
                    }

                    postSideEffect(CreateFileSideEffects.ShowConfirmCreating)
                } else {

                }
                convertPhotoBtnStatus.value = CreateStorageBtnStatus(isLoading = false, isEnable = true)
            },
            onFail = {
                // TODO: сделать тост отсутствия интернета
            }
        )
    }

    fun reconvertPhoto(oldPhoto: PhotoModel, newPhoto: PhotoModel) = intent {
        networkStatus.isNetworkConnected(
            onSuccess = {
                val resultFromApi = convertPhotoIntoTextUseCase.execute(
                    listOfPhoto = listOf(newPhoto).map {
                        ConvertPhotoModel(
                            id = it.id,
                            //photo = BitmapFactory.decodeFile(it.imageUri.toFile().path).toBase64(),
                            photo = it.imageUri.toString()
                        )
                    }
                )
                if (resultFromApi.isSuccess) {
                    val newList = state.listOfPhotos
                    val element = newList.filter { photoModel ->
                        photoModel.id == oldPhoto.id
                    }
                    val indexOfPhoto = newList.indexOf(element[0])
                    newList[indexOfPhoto].imageUri = newPhoto.imageUri
                    newList[indexOfPhoto].text = resultFromApi.list[0].text

                    reduce {
                        state.copy(listOfPhotos = newList)
                    }
                } else {

                }
            },
            onFail = {

            },
        )
    }

    fun editConvertedText(photo: PhotoModel, text: String) = intent {
        val newList = state.listOfPhotos
        newList[newList.indexOf(photo)].text = text
        reduce {
            state.copy(listOfPhotos = newList)
        }
    }

    fun clearConvertedText() = intent {
        val newList = state.listOfPhotos
        for (photo in newList) {
            photo.text = ""
        }

        reduce {
            state.copy(listOfPhotos = newList)
        }
    }

}