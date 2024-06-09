package com.example.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.noteapp.databinding.NewNoteBinding
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.viewmodel.NotesViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class NewNote : Fragment() {

    private lateinit var _binding: NewNoteBinding
    private val viewModel: NoteViewModel by viewModels()
    private val args: NewNoteArgs by navArgs()


//    val newObserver = Observer<String> {newName ->
//        titleInputEditText.text = newName
//    }


    private lateinit var titleInputLayout: TextInputLayout
    private lateinit var titleInputEditText: TextInputEditText
    private lateinit var bodyInputLayout: TextInputLayout
    private lateinit var bodyInputEditText: TextInputEditText
    private lateinit var textSizeUpButton: Button
    private lateinit var textSizeDownButton: Button

    private var textSize = 16.0f // Taille de la police initiale


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewNoteBinding.inflate(LayoutInflater.from(context))
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleInputLayout = _binding.TitleInput

        titleInputEditText = view.findViewById(R.id.titleEditText)
        bodyInputLayout = view.findViewById(R.id.BodyInput)
        bodyInputEditText = view.findViewById(R.id.bodyEditText)
        textSizeUpButton = view.findViewById(R.id.TextSizeUp)
        textSizeDownButton = view.findViewById(R.id.TextSizeDown)

        val saveButton : Button = view.findViewById(R.id.SaveButton)

        //Increase and decrease Body text size with viewModel
        textSizeUpButton.setOnClickListener {
            viewModel.increaseTextSize()
            bodyInputEditText.textSize = viewModel.textSize
        }
        textSizeDownButton.setOnClickListener {
            viewModel.decreaseTextSize()
            bodyInputEditText.textSize = viewModel.textSize
        }


        _binding.lifecycleOwner = viewLifecycleOwner
        _binding.viewModel = viewModel

        viewModel.fetchNote(args.NOTEID)


        //Save the note in Strapi when you click the save button with View Model
        saveButton.setOnClickListener {
            viewModel.updateNote(_binding.titleEditText.text.toString(), _binding.bodyEditText.text.toString())
        }
    }
}