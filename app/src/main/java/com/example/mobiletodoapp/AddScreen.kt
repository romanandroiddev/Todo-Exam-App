package com.example.mobiletodoapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobiletodoapp.databinding.ScreenAddBinding
import com.example.mobiletodoapp.databinding.ScreenEditBinding
import kotlinx.coroutines.launch

class AddScreen : Fragment(R.layout.screen_add) {
    private lateinit var dao: TodoDao
    private lateinit var binding: ScreenAddBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenAddBinding.bind(view)

        dao = TodoDatabase.getInstance(requireContext()).getTodoDao()


        binding.btnAdd.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val body = binding.etBody.text.toString()

            if (title.isNotEmpty() && body.isNotEmpty()) {
                lifecycleScope.launch {
                    dao.addTodoData(TodoData(0, title, body))
                }
                showSnackbar("Successfully added")
                findNavController().popBackStack()
            } else {
                showSnackbar(getString(R.string.please_fill_all_fields))
            }
        }


        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}