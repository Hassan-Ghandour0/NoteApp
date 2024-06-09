package com.example.noteapp.data

data class StrapiResult<T>(val data: T)
data class StrapiUpdate<T>(val data: T)
data class StrapiCheckEmail<T>(
    val data: T,
    val isSuccess : Boolean
)

