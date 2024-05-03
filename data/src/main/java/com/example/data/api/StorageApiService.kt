package com.example.data.api

import com.example.data.model.storage.CreateStorageResponse
import com.example.data.model.storage.StoragePreviewResponse
import com.example.data.utils.Constants
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface StorageApiService {

    @Multipart
    //@Headers("Content-Type: multipart/form-data")
    @POST(Constants.CREATE_VAULT)
    suspend fun createVault(
        @Part("create_vault_credentials") createVaultCredentials: RequestBody,
        @Part files: List<MultipartBody.Part>
    ): Response<CreateStorageResponse>

    @GET(Constants.GET_VAULT_PREVIEWS)
    @Headers("accept: application/json")
    suspend fun getStoragePreviews(): Response<List<StoragePreviewResponse>>

}