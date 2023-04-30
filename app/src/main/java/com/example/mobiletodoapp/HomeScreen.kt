package com.example.mobiletodoapp

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mobiletodoapp.databinding.ScreenHomeBinding
import kotlinx.coroutines.launch

class HomeScreen : Fragment(R.layout.screen_home) {
    private val adapter = TodoAdapter()
    private lateinit var dao: TodoDao
    private lateinit var binding: ScreenHomeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenHomeBinding.bind(view)

        dao = TodoDatabase.getInstance(requireContext()).getTodoDao()

        binding.rcTodo.adapter = adapter


        lifecycleScope.launch {
            adapter.models = dao.getAllNotes()
        }

        adapter.setOnItemClickListener {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToEditScreen(
                it.id,it.title,it.body
            ))
        }

        binding.icSearch.setOnClickListener {
            if (binding.lExpandableLayout.isExpanded) {
                binding.icSearch.setImageResource(R.drawable.ic_search)
                binding.lExpandableLayout.collapse()
                binding.etSearch.setText("")
            } else {
                binding.icSearch.setImageResource(R.drawable.ic_close)
                binding.lExpandableLayout.expand()
            }
        }

        binding.etSearch.doAfterTextChanged {
            if (binding.etSearch.text.toString().isEmpty()) {
                hideKeyboard()
                lifecycleScope.launch {
                    adapter.models = dao.getAllNotes()
                }
            } else {
                lifecycleScope.launch {
                    adapter.models = dao.searchTodo(binding.etSearch.text.toString())
                }
            }
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToAddScreen())
        }


    }
}