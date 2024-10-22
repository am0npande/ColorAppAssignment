package com.example.firebasecolorapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firebasecolorapp.domain.datamodel.ColorModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColor(data: ColorModel)

    @Query("SELECT * FROM Color_table")
    fun colorList(): Flow<List<ColorModel>>
}