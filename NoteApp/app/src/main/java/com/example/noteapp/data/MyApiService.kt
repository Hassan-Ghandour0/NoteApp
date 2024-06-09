package com.example.noteapp.data

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


const val Strapitoken: String =
    "9515dc1f20126893101d899b5497fbba6be68cc329036767eb1555fe2b55016c0e4d977f155d735551b75a861aca9b4c58e30d2bf75e25a312f7325a96295d9c099df8f0f6276dacbd74e7837e814d510c32a1f5f54d87925632d0b91a205d3df2bb45e0fd0defaa683b593ef5485a89b3db0da327d88bf8c92400c8c43c7137"

interface MyApiService {

    @Headers("Authorization: Bearer $Strapitoken")

    /// GET http://localhost:1337/Note
    @GET("notes")
    suspend fun getNotes(@Query("userEmail") userEmail: String): StrapiResult<List<Note>>


    /// GET http://localhost:1337/Note/1
    @Headers("Authorization: Bearer $Strapitoken")
    @GET("notes/{id}")
    suspend fun fetchNote(@Path("id") id: Int): StrapiResult<Note>

//    @Headers("Authorization: Bearer $Strapitoken")
//    @GET("notes/{id}")
//    suspend fun getUserByEmail(@Path("id") email: String): StrapiCheckEmail<Note>

    @Headers("Authorization: Bearer $Strapitoken")
    @GET("notes") // Assuming you have an endpoint to retrieve users
    suspend fun getUserByEmail(@Query("filters[UserEmail][\$containsi]") email: String): StrapiCheckEmail<List<Note>>

    @Headers("Authorization: Bearer $Strapitoken")
    @POST("notes")
    suspend fun createNote(@Body data: StrapiUpdate<NoteAttributes>): StrapiResult<List<Note>>



    @PUT("notes/{id}")
    suspend fun updateNote(
        @Path("id") id: Int,
        @Body data: StrapiUpdate<NoteUpdateRequest>): StrapiResult<Note>


    @DELETE("notes")
    suspend fun deleteNotes(@Query("userEmail") userEmail: String)
}
