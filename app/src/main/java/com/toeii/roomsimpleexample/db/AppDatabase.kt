package com.toeii.roomsimpleexample.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookBean::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    //...Other Dao

}