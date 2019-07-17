package com.toeii.roomsimpleexample

import android.app.Application
import androidx.room.Room
import com.toeii.roomsimpleexample.db.AppDatabase

class App: Application() {

    companion object{
        var instance: App? = null
    }

    var db: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()

        instance = this

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "db_book"
        ).build()

    }



}