package com.project.studentproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.project.studentproject.databinding.StudentListItemBinding
import com.project.studentproject.model.Student

class StudentListAdapter(val studentList: ArrayList<Student>) :
    RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: StudentViewHolder, position: Int
    ) {
        holder.binding.txtID.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name

        holder.binding.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections
                .actionStudentDetail(studentList[position])

            Navigation.findNavController(it).navigate(action)
        }
    }

    //    override fun getItemCount(): Int {
//        return studentList.size
//    }
//    dipersingkat
    override fun getItemCount() = studentList.size

    class StudentViewHolder(var binding: StudentListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}
