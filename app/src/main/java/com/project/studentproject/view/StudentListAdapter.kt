package com.project.studentproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.project.studentproject.databinding.StudentListItemBinding
import com.project.studentproject.model.Student

class StudentListAdapter(val studentList: ArrayList<Student>) :
    RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(),
    StudentCardListener {

    private lateinit var currentView: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.student = studentList[position]
        holder.binding.listener = this
        currentView = holder.binding.root
    }

    override fun getItemCount() = studentList.size

    override fun onDetailClick(student: Student) {
        val action = StudentListFragmentDirections.actionStudentDetail(student)
        Navigation.findNavController(currentView).navigate(action)
    }

    class StudentViewHolder(var binding: StudentListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}