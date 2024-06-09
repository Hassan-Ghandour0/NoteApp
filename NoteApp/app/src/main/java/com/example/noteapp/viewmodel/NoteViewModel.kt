package com.example.noteapp.viewmodel

import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.MyApiService
import com.example.noteapp.data.Note
import com.example.noteapp.data.NoteUpdateRequest
import com.example.noteapp.data.RetrofitClient
import com.example.noteapp.data.StrapiUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel : ViewModel() {

    //Increase and Decrease the size of the text
    var textSize = 16.0f // Taille de police initiale
    fun increaseTextSize() {
        textSize += 4.0f
    }
    fun decreaseTextSize() {
        textSize -= 4.0f
    }

    //_note va prendre NoteTitle et NoteBody
    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note

    private val _isEmailExistMLV = MutableLiveData<Boolean>()
    val isEmailExistMLV: LiveData<Boolean>
        get() = _isEmailExistMLV


    private val _loginErrorColor = MutableLiveData<Int>()
    val loginErrorColor: LiveData<Int> get() = _loginErrorColor


    private val myApiService = RetrofitClient.myApiService

    //Cette fonction prends NoteTitle et NoteBody de Strapi et les mets dans _note
    fun fetchNote(noteid: Int) {
        viewModelScope.launch {
            try {
                val result = myApiService.fetchNote(noteid)
                //update _note with NoteTitle and NoteBody for later coding use (ie. Showing on screen)
                withContext(Dispatchers.Main) {
                    _note.value = result.data!!
                }
            } catch (e: Exception) {
                // Gérez les erreurs ici
            }
        }
    }

    //Cette fonction update la database avec le texte présent
    fun updateNote(noteTitle: String, noteBody: String) {
        viewModelScope.launch {
            try {
                val updateNoteRequest = NoteUpdateRequest(noteTitle, noteBody)
                val updatedNote =
                    myApiService.updateNote(_note.value!!.id, StrapiUpdate(updateNoteRequest))

                // Show in log the new value of noteBody as a proof of modification
                withContext(Dispatchers.Main) {
                    Log.d(
                        "NotesViewModel",
                        "Nouvelle valeur de textTest: ${updatedNote.data.attributes.noteBody}"
                    )
                }
            } catch (e: Exception) {
                // Gérez les erreurs ici
                println(e)
            }
        }
    }

    //Cette fonction ne marche pas
    //Elle est censé regarder si email est présent dans Strapi, sous UserEmail
    fun isEmailExist(email: String) {
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response = myApiService.getUserByEmail(email)
                withContext(Dispatchers.Main) {
                    if (!response.data.isNullOrEmpty()) {
                        _isEmailExistMLV.postValue(true)
                    }
                }
            } catch (e: Exception) {
                _isEmailExistMLV.postValue(false)
            }
        }
    }

    //Fonction non finie
    //Hassan a fait ceci. Je pense qu'il voulait stocker une adresse mail pour la mettre dans Strapi après
    private val _email = MutableStateFlow<String?>(null)
    fun setEmail(email: String) {
        _email.value = email
    }
    val email: StateFlow<String?> = _email

    //Change la couleur du message d'erreur
    fun setLoginErrorColor() {
        _loginErrorColor.value = Color.parseColor("#f00")
    }

}
