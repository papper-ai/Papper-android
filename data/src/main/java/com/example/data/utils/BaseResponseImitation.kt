package com.example.data.utils

import com.example.data.base.BaseResponse

object BaseResponseImitation{
    fun execute(): BaseResponse {
        val isSuccess = ((0..4).random() != 4)
        //val isSuccess = ((0..2).random() != 2)
        return BaseResponse(
            isSuccess = isSuccess,
            code = if (isSuccess) "200" else "404",
            msg = if (isSuccess) "ok" else "some exception",
        )
    }
}