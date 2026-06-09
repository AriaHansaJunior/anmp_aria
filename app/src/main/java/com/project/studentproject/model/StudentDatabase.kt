package com.project.studentproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // populate initial data
                CoroutineScope(Dispatchers.IO).launch {
                    INSTANCE?.studentDao()?.insert(
                        Student(
                            id = "S001",
                            name = "Alice",
                            bod = "2001-01-01",
                            phone = "08123456789",
                            photoUrl = "https://randomuser.me/api/portraits/women/1.jpg"
                        )
                    )
                    INSTANCE?.studentDao()?.insert(
                        Student(
                            id = "S002",
                            name = "Bob",
                            bod = "2002-02-02",
                            phone = "08987654321",
                            photoUrl = "https://randomuser.me/api/portraits/men/1.jpg"
                        )
                    )
                }
            }
        }

        fun buildDatabase(context: Context): StudentDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_database"
                )
                    .addCallback(roomCallback)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}