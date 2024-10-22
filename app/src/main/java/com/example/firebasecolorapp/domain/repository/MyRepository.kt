package com.example.firebasecolorapp.domain.repository

import com.example.firebasecolorapp.data.local.MyDao
import com.example.firebasecolorapp.domain.datamodel.ColorModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MyRepository @Inject constructor(
    private val dao: MyDao
) {
    suspend fun insertColor(color: String, date: String) {
        dao.insertColor(ColorModel(color, date))
    }
    fun listOfColor(): Flow<List<ColorModel>> {
        return dao.colorList()
    }
}