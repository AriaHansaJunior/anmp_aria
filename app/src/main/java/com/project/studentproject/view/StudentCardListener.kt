package com.project.studentproject.view

import com.project.studentproject.model.Student

interface StudentCardListener {
    fun onDetailClick(student: Student)
}