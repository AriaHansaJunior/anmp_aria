package com.project.studentproject.viewmodel

import android.adservices.ondevicepersonalization.MutableKeyValueStore
import android.app.Application
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

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val errorLD = MutableLiveData<Boolean>()
    val TAG: String = "VolleyTag"
    var queue: RequestQueue?=null

    fun fetch(id: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMH"
        errorLD.value = false

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<List<Student>>() {}.type
                val result = Gson().fromJson<List<Student>>(response, sType)

                val student = result.find { it.id == id }
                student?.let {
                    studentLD.value = it
                }

                errorLD.value = false
            },
            { error ->
                Log.e("Volley_error", error.toString())
                errorLD.value = true
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}