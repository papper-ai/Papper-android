package com.example.papper.features.storage.storage.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.papper.base.BaseViewModel
import com.example.papper.state.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import java.io.File
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    appState: MutableStateFlow<AppState>,
) : BaseViewModel<StorageSideEffects>(appState) {

    val storageScreenState = mutableStateOf<StorageScreenState>(StorageScreenState.Loading)

    fun getData(id: String) = intent {
        val list = mutableListOf<File>()
        postSideEffect(StorageSideEffects.ShowLoading)
        viewModelScope.launch {
            for (i in 1..15) {
                delay(100)
                list.add(
                    File("$i example example example example.pdf")
                )
                Log.d("Test", "getData: $i")
            }
            reduce {
                state.value = state.value.copy(storageState = state.value.storageState.copy(title = "Storage Title", listOfStorages = list))
                state
            }
            //postSideEffect(ChatsSideEffects.ShowError)
            postSideEffect(StorageSideEffects.ShowSuccess)
        }
    }

}