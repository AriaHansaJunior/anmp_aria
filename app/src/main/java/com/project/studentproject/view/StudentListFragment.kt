package com.project.studentproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.studentproject.databinding.FragmentStudentListBinding
import com.project.studentproject.viewmodel.ListViewModel

class StudentListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())
    private lateinit var binding: FragmentStudentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recViewStudent.layoutManager = LinearLayoutManager(context)
        binding.recViewStudent.adapter = studentListAdapter

        binding.refreshLayout.setOnRefreshListener {
//            binding.recViewStudent.visibility = View.GONE
//            binding.txtError.visibility = View.GONE
//            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()

        }
        observeViewModel()
    }

    //        fun observeViewModel() {
////        LD= live data
//        viewModel.studentsLD.observe(viewLifecycleOwner) {
//            studentListAdapter.updateStudentList(it)
//        }
//
//        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner) {
//            if (it == true) {
//                binding.txtError.visibility = View.VISIBLE
//            } else {
//                binding.txtError.visibility = View.GONE
//            }
//        }
//
//        viewModel.loadingLD.observe(viewLifecycleOwner) {
//            if (it == true) {
//                binding.recViewStudent.visibility = View.GONE
//                binding.progressLoad.visibility = View.VISIBLE
//            } else {
//                binding.recViewStudent.visibility = View.VISIBLE
//                binding.progressLoad.visibility = View.GONE
//            }
//        }
//    }
    fun observeViewModel() {
//  sama saja dengan atasnya, hanya saja observer ditulis secara eksplisit. sedangkan yang atas implisit
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
            binding.refreshLayout.isRefreshing = false
        })

        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.recViewStudent.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recViewStudent.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })
    }
}