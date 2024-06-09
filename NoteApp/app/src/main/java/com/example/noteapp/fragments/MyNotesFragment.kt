package com.example.noteapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.NoteListDirections
import com.example.noteapp.R
import com.example.noteapp.viewmodel.NotesViewModel


class MyNotesFragment : Fragment(R.layout.fragment_mynotes) {

    private val viewModel: NotesViewModel by viewModels()

    private val ARG_OBJECT = "object"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {

           val recyclerView: RecyclerView = view.findViewById(R.id.rv)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            viewModel.notes.observe(viewLifecycleOwner) {
                recyclerView.adapter = MyAdapterList(it)
            }

            viewModel.fetchNotes("TODO")



        }
    }

}