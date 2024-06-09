package com.example.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.databinding.NewNoteBinding
import com.example.noteapp.viewmodel.NoteViewModel
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 */
class Login : Fragment() {

    private val viewModel: NoteViewModel by viewModels()

    private lateinit var _binding: NewNoteBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addAccountButton : Button = view.findViewById(R.id.AddAccountButton)
        val loginButton : Button = view.findViewById(R.id.LoginButton)
        val emailEdit : TextInputEditText = view.findViewById(R.id.LoginEmailEdit)
        val loginErrorMessage : TextView = view.findViewById(R.id.LoginErrorMessage)

        val navController=findNavController()

        addAccountButton.setOnClickListener{
            navController.navigate(R.id.login_to_signIn)
        }


        viewModel.loginErrorColor.observe(viewLifecycleOwner) { color ->
            loginErrorMessage.setTextColor(color)
        }

        loginButton.setOnClickListener{
            val email = emailEdit.text.toString()
            // Call the viewModel function that checks if the email exists in Strapi
            viewModel.isEmailExist(email)
            viewModel.isEmailExistMLV.observe(viewLifecycleOwner) { isExist ->
                if (isExist) {
                    navController.navigate(R.id.login_to_notelist)
                } else {
                    viewModel.setLoginErrorColor()
                    // Handle case where email does not exist
                    // For example, display an error message to the user
                }
            }
        }
    }
}
