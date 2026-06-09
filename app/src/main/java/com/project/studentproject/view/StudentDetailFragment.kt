package com.project.studentproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.studentproject.databinding.FragmentStudentDetailBinding
import com.project.studentproject.viewmodel.DetailViewModel
import androidx.navigation.Navigation

class StudentDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        val args = StudentDetailFragmentArgs.fromBundle(requireArguments())
        val student = args.student

        viewModel.fetch(student.id!!)

        binding.fragment = this
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.student = it
        })
    }

    fun onUpdate() {
        val student = binding.student ?: return
        viewModel.update(
            name = student.name ?: "",
            bod = student.bod ?: "",
            phone = student.phone ?: "",
            id = student.id
        )
        Toast.makeText(context, "Student updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(requireView()).popBackStack()
    }

    fun onCreateNotification() {
        Toast.makeText(context, "Notification created", Toast.LENGTH_SHORT).show()
    }
}