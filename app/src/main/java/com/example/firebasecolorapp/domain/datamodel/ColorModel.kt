package com.example.firebasecolorapp.domain.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.firebasecolorapp.utils.Contants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ColorModel(
    @PrimaryKey
    val color: String,
    val date: String,
)
