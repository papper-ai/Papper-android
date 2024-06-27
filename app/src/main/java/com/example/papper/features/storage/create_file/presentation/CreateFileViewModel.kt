package com.example.papper.features.storage.create_file.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.model.llm.ImageToConvert
import com.example.domain.usecases.llm.ConvertImageToTextUseCase
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
    //private val convertPhotoIntoTextUseCase: ConvertPhotoIntoTextUseCase,
    private val convertImageToTextUseCase: ConvertImageToTextUseCase,
): ViewModel(), ContainerHost<CreateFileState, CreateFileSideEffects> {

    override val container = container<CreateFileState, CreateFileSideEffects>(CreateFileState())
    val convertPhotoBtnStatus = mutableStateOf<CreateStorageBtnStatus>(CreateStorageBtnStatus(isLoading = false, isEnable = true))
    val createFileBtnStatus = mutableStateOf<CreateStorageBtnStatus>(CreateStorageBtnStatus(isLoading = false, isEnable = false))

    fun navigateBack() = intent {
        postSideEffect(CreateFileSideEffects.NavigateBack)
    }

    fun updateTitle(title: String) = intent {
        reduce {
            state.copy(title = title)
        }
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

    fun convertPhotos(context: Context) = intent {
        networkStatus.isNetworkConnected(
            onSuccess = {
                convertPhotoBtnStatus.value = CreateStorageBtnStatus(isLoading = true, isEnable = false)

                val list = state.listOfPhotos.map {
                    ImageToConvert(
                        uuid = it.id.toString(),
                        image = it.imageUri.toBase64(context),
                    )
                }

                val resultFromApi = convertImageToTextUseCase.execute(list = list)
                if (resultFromApi.isSuccess) {
                    val newList = state.listOfPhotos.toMutableList()
                    for (convertedPhoto in resultFromApi.list) {
                        newList[convertedPhoto.uuid.toInt() - 1] = PhotoModel(
                            id = convertedPhoto.uuid.toInt(),
                            imageUri = newList[convertedPhoto.uuid.toInt() - 1].imageUri,
                            text = convertedPhoto.text,
                        )
                    }

                    reduce {
                        state.copy(listOfPhotos = newList)
                    }
                    for (text in state.listOfPhotos) {
                        Log.e("Test", "convertPhotos: ${text.text}\n")
                    }
                } else {
                    Log.e("TEST", "convertPhotos: попал в false", )
                }
                convertPhotoBtnStatus.value = CreateStorageBtnStatus(isLoading = false, isEnable = true)
            },
            onFail = {
                // TODO: сделать тост отсутствия интернета
            }
        )
    }

    fun reconvertPhoto(context: Context, oldPhoto: PhotoModel, newPhoto: PhotoModel) = intent {
        networkStatus.isNetworkConnected(
            onSuccess = {
                val resultFromApi = convertImageToTextUseCase.execute(list = listOf(
                    ImageToConvert(
                        uuid = newPhoto.id.toString(),
                        image = newPhoto.imageUri.toBase64(context),
                    )
                ))

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

    fun navigatePopBack() = intent {
        var text = ""
        for (content in state.listOfPhotos) {
            text += " ${content.text}"
        }
        postSideEffect(CreateFileSideEffects.NavigatePopBack(state.title, text))
    }

}