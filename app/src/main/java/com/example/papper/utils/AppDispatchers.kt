package com.example.papper.utils

import kotlinx.coroutines.Dispatchers

object AppDispatchers {
    val ui = Dispatchers.Main
    val default = Dispatchers.Default
    val io = Dispatchers.IO
    val unconfined = Dispatchers.Unconfined
}