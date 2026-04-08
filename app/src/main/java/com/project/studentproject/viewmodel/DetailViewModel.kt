package com.project.studentproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.project.studentproject.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val TAG: String = "VolleyTag"
    var queue: RequestQueue?=null

    fun fetch(id: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMH"
        val list = arrayListOf(
            Student("16055","Nonie","1998/03/28","5718444778",
                "http://dummyimage.com/75x100.jpg/cc0000/ffffff"),
            Student("13312","Rich","1994/12/14","3925444073",
                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff"),
            Student("11204","Dinny","1994/10/07","6827808747",
                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
        )

        val result = list.find { it.id == id }

        result?.let {
            studentLD.value = it
        }
    }
}
