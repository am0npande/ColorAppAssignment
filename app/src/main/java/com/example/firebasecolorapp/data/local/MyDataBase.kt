package com.example.firebasecolorapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.firebasecolorapp.domain.datamodel.ColorModel


//it is abstract because room will implement it for us

@Database(entities = [ColorModel::class], version = 1)

abstract class MyDataBase: RoomDatabase() {
    abstract val myDao: MyDao

}
