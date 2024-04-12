package com.example.papper.features.archive.presentation

sealed class ArchivesScreenState {
    object  Loading: ArchivesScreenState()
    object  Success: ArchivesScreenState()
    object  Error: ArchivesScreenState()
}