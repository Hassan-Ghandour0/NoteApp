package com.example.noteapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.Note
import com.example.noteapp.data.NoteAttributes
import com.example.noteapp.data.NoteUpdateRequest
import com.example.noteapp.data.RetrofitClient
import com.example.noteapp.data.StrapiUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes : LiveData<List<Note>>
        get() = _notes

    private val myApiService = RetrofitClient.myApiService

    //Cette fonction n'est pas encore faite
    fun fetchNotes(userEmail: String, isPrivate: Boolean = true) {
        viewModelScope.launch {
            try {

                val result = if (isPrivate) {
                    myApiService.getNotes(userEmail)
                } else {
                    // TODO fetch public notes
                    myApiService.getNotes(userEmail)
                }
                withContext(Dispatchers.Main) {
                    Log.d(
                        "NotesViewModel",
                        "Nouvelle valeur de textTest: ${result.data.first().attributes.noteTitle}"
                    )
                    _notes.value = result.data ?: listOf()
                }
            } catch (e: Exception) {
                // Gérez les erreurs ici
            }
        }
    }


    //Cette fonction créée la nouvelle note, mais il reste un problème d'affichage
    fun createNote() {
        viewModelScope.launch {
            try {
                // Créez une instance de NoteAttributes avec les valeurs souhaitées
                val newNoteAttributes = NoteAttributes(
                    userEmail = "alfred.couvreur@student.junia.com",
                    noteTitle = "New Title",
                    noteBody = "New Body"
                )

                // Créez une instance de StrapiUpdate avec les nouvelles attributs de note
                val newNoteRequest = StrapiUpdate(data = newNoteAttributes)

                // Appelez la fonction createNote avec la nouvelle note en tant que paramètre
                val result = myApiService.createNote(newNoteRequest)

                withContext(Dispatchers.Main) {
                    Log.d(
                        "NotesViewModel",
                        "Nouvelle valeur de textTest: ${result.data.first().attributes.noteBody}"
                    )
                }
            } catch (e: Exception) {
                // Gérez les erreurs ici
                println(e)
            }
        }
    }


//      Cette fonction n'a pas encore été faite
//    fun deleteNotes(userEmail: String) {
//        viewModelScope.launch {
//            try {
//                myApiService.deleteNotes(userEmail)
//                // Faites quelque chose après la suppression si nécessaire
//            } catch (e: Exception) {
//                // Gérez les erreurs ici
//            }
//        }
//    }



}