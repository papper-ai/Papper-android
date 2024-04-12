package com.example.papper.features.archive.presentation

sealed class ArchivesSideEffects {
    object ShowLoading: ArchivesSideEffects()
    object ShowError: ArchivesSideEffects()
    object ShowSuccess: ArchivesSideEffects()
}