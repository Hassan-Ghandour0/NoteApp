package com.example.noteapp.data

import com.google.gson.annotations.SerializedName

data class Note(
    val id: Int,
    @SerializedName("attributes") val attributes: NoteAttributes,
)

data class NoteAttributes(
    @SerializedName("UserEmail") val userEmail: String,
    @SerializedName("NoteTitle") var noteTitle: String,
    @SerializedName("NoteBody") val noteBody: String
//    @SerializedName("Private") val privateBoolean: Boolean
)



data class NoteUpdateRequest(
    @SerializedName("NoteTitle") val noteTitle: String,
    @SerializedName("NoteBody") val noteBody: String)