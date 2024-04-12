package com.example.papper.features.storage.storages.presentation

import androidx.compose.runtime.mutableStateOf
import com.example.papper.base.BaseViewModel
import com.example.papper.features.storage.storages.model.StorageDescription
import com.example.papper.state.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class StoragesViewModel @Inject constructor(
    appState: MutableStateFlow<AppState>,
) : BaseViewModel<StoragesSideEffects>(appState) {

    val storagesScreenState = mutableStateOf<StoragesScreenState>(StoragesScreenState.Loading)

    init {
        loadData()
    }

    fun loadData() = intent {
        postSideEffect(StoragesSideEffects.ShowLoading)
        val list = mutableListOf<StorageDescription>()
        for (i in 1..10) {
            list.add(
                StorageDescription(
                    id = i.toString(),
                    title = "title of storage $i"
                )
            )
        }
        reduce {
            state.value = state.value.copy(storagesState = StoragesState(listOfStorages = list))
            state
        }
        postSideEffect(StoragesSideEffects.ShowSuccess)
    }
}