package com.example.data.api

import com.example.data.model.storage.AddFileIntoStorageResponse
import com.example.data.model.storage.CreateStorageResponse
import com.example.data.model.storage.RenameVaultBody
import com.example.data.model.storage.StoragePreviewResponse
import com.example.data.model.storage.StorageResponse
import com.example.data.utils.Constants
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface StorageApiService {

    @Multipart
    //@Headers("Content-Type: multipart/form-data")
    @POST(Constants.CREATE_VAULT)
    suspend fun createVault(
        @Part("create_vault_credentials") createVaultCredentials: RequestBody,
        @Part files: List<MultipartBody.Part>
    ): Response<CreateStorageResponse>

    @Multipart
    @POST(Constants.ADD_FILE_TO_VAULT)
    suspend fun addFileToVault(
        @Part("vault_id") vaultId: RequestBody,
        @Part file: MultipartBody.Part,
    ): Response<AddFileIntoStorageResponse>

    @GET(Constants.GET_VAULT_PREVIEWS)
    @Headers("accept: application/json")
    suspend fun getVaultPreviews(): Response<List<StoragePreviewResponse>>

    @GET("${Constants.GET_VAULT_BY_ID}/{id}")
    @Headers("accept: application/json")
    suspend fun getVaultByID(
        @Path("id") id: String
    ): Response<StorageResponse>

    @DELETE("${Constants.DELETE_VAULT}/{id}")
    suspend fun deleteVaultByID(
        @Path("id") id: String
    ): Response<Unit>

    @DELETE("${Constants.DELETE_FILE_FROM_VAULT}/{vaultId}/{documentId}")
    suspend fun deleteFileFromVaultByID(
        @Path("vaultId") vaultId: String,
        @Path("documentId") documentId: String,
    ): Response<Unit>

    @PATCH(Constants.RENAME_VAULT)
    @Headers("Content-Type: application/json")
    suspend fun renameVault(
        @Body body: RenameVaultBody,
    ): Response<Any>

}