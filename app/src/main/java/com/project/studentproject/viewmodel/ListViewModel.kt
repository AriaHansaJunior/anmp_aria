package com.project.studentproject.viewmodel

import android.app.Application
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.studentproject.model.Student
import kotlin.concurrent.thread

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val Tag = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh() {
        loadingLD.value = true
        studentLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                // VOLLEY SUCCESS
                val sType = object : TypeToken<List<Student>>() {}.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                studentsLD.value = result as ArrayList<Student>
                loadingLD.value = false
            },
            {
                // VOLLEY ERROR
                Log.e("showVolley", it.toString())
                studentLoadErrorLD.value = true
                loadingLD.value = false
            }
        )

        stringRequest.tag = Tag
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(Tag)

    }
}