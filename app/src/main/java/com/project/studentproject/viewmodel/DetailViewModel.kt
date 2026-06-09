package com.project.studentproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.project.studentproject.model.Student
import com.project.studentproject.model.StudentDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    val studentLD = MutableLiveData<Student>()
    val errorLD = MutableLiveData<Boolean>()

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(id: String) {
        errorLD.postValue(false)
        launch {
            val db = StudentDatabase.buildDatabase(getApplication())
            val student = db.studentDao().selectById(id)
            studentLD.postValue(student)
        }
    }

    fun update(name: String, bod: String, phone: String, id: String) {
        launch {
            val db = StudentDatabase.buildDatabase(getApplication())
            db.studentDao().update(name, bod, phone, id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}