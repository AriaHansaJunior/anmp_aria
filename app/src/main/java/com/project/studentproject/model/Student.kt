package com.project.studentproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Student(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @SerializedName("student_name")
    @ColumnInfo(name = "name")
    var name: String?,

    @SerializedName("birth_of_date")
    @ColumnInfo(name = "bod")
    var bod: String?,

    @ColumnInfo(name = "phone")
    var phone: String?,

    @SerializedName("photo_url")
    @ColumnInfo(name = "photo_url")
    var photoUrl: String?
) : Serializable