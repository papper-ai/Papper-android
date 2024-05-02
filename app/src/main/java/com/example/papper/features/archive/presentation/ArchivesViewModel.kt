package com.example.papper.features.archive.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papper.features.chat.chats.model.ChatDescription
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ArchivesViewModel @Inject constructor() : ViewModel(), ContainerHost<ArchivesState, ArchivesSideEffects> {

    override val container = container<ArchivesState, ArchivesSideEffects>(ArchivesState())

    val archivesScreenState = mutableStateOf<ArchivesScreenState>(ArchivesScreenState.Loading)

    init {
        getData()
    }

    private fun getData() = intent {
        val list = mutableListOf<ChatDescription>()
        postSideEffect(ArchivesSideEffects.ShowLoading)
        viewModelScope.launch {
            for (i in 1..15) {
                delay(100)
                list.add(
                    ChatDescription(
                        id = i.toString(),
                        title = "archive title",
//                        lastMsg = "last msg"
                    )
                )
                Log.d("Test", "getData: ${i}")
            }
            reduce {
                state.copy(listOfChats = list)
            }
            //postSideEffect(ArchivesSideEffects.ShowError)
            postSideEffect(ArchivesSideEffects.ShowSuccess)
        }
    }

}