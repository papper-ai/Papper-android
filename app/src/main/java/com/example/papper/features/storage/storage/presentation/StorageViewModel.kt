package com.example.papper.features.storage.storage.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor() : ViewModel(), ContainerHost<StorageState, StorageSideEffects> {

    override val container = container<StorageState, StorageSideEffects>(StorageState())

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
                state.copy(title = "Storage Title", listOfStorages = list)
            }
            //postSideEffect(ChatsSideEffects.ShowError)
            postSideEffect(StorageSideEffects.ShowSuccess)
        }
    }

}