package com.project.studentproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.studentproject.databinding.FragmentStudentDetailBinding
import com.project.studentproject.viewmodel.DetailViewModel

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

        val args = StudentDetailFragmentArgs.fromBundle(requireArguments())
        val student = args.student

        binding.txtStudentID.setText(student.id)
        binding.txtStudentName.setText(student.name)
        binding.txtStudentBod.setText(student.bod)
        binding.txtStudentPhone.setText(student.phone)
    }
}

//        private fun observeViewModel() {
//        viewModel.studentLD.observe(viewLifecycleOwner) { student ->
//            binding.txtStudentID.setText(student.id)
//            binding.txtStudentName.setText(student.name)
//            binding.txtStudentBod.setText(student.bod)
//            binding.txtStudentPhone.setText(student.phone)
//        }
//    }
//}
//        private fun observeViewModel() {
//            viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//                binding.txtStudentID.setText(it.id)
//                binding.txtStudentName.setText(it.name)
//                binding.txtStudentBod.setText(it.bod)
//                binding.txtStudentPhone.setText(it.phone)
//            })
//        }
//    }