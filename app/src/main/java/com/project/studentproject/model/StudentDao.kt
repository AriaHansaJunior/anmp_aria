package com.project.studentproject.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {

    @Query("SELECT * FROM student")
    fun selectAll(): List<Student>

    @Query("SELECT * FROM student WHERE id = :id")
    fun selectById(id: String): Student

    @Query("UPDATE student SET name=:name, bod=:bod, phone=:phone WHERE id=:id")
    fun update(name: String, bod: String, phone: String, id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Student)
}