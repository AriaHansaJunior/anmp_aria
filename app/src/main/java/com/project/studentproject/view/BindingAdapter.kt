package com.project.studentproject.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadPhotoURL(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        val picasso = Picasso.Builder(imageView.context)
        picasso.listener { _, _, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(url).into(imageView)
    }
}