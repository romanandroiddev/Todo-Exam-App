package com.example.mobiletodoapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobiletodoapp.databinding.ScreenEditBinding
import kotlinx.coroutines.launch

class EditScreen : Fragment(R.layout.screen_edit) {
    private lateinit var dao: TodoDao
    private lateinit var binding: ScreenEditBinding
    private val args: EditScreenArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenEditBinding.bind(view)

        dao = TodoDatabase.getInstance(requireContext()).getTodoDao()


        binding.etTitle.setText(args.title)
        binding.etBody.setText(args.body)


        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val body = binding.etBody.text.toString()

            if (title.isNotEmpty() && body.isNotEmpty()) {
                lifecycleScope.launch {
                    dao.updateTodo(TodoData(args.id, title, body))
                }
                showSnackbar("Successfully updated")
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