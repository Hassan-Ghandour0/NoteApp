package com.example.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.viewmodel.NoteViewModel
import com.google.android.material.textfield.TextInputEditText

class SignIn : Fragment() {

    private val viewModel: NoteViewModel by viewModels()

    private lateinit var emailEditText: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailEditText = view.findViewById(R.id.SignIn_EmailTextBox)

        val confirmButton : Button = view.findViewById(R.id.SignIn_ConfirmButton)

        confirmButton.setOnClickListener{
            val email = emailEditText.text.toString()
            if (email.isNotEmpty()) {
                viewModel.setEmail(email)
                findNavController().navigate(R.id.signin_to_notelist)
            } else {
                // Show a message if email is empty
            }
        }
    }
}
