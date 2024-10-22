package com.example.firebasecolorapp.presentation.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasecolorapp.domain.datamodel.ColorModel
import com.example.firebasecolorapp.domain.repository.MyRepository
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MyRepository,
    private val databaseReference: DatabaseReference,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {



    val enableButton = MutableStateFlow(true)

    private var _syncNumber = MutableStateFlow(0)
    var syncNumber = _syncNumber.asStateFlow()

    private var _colorList = MutableStateFlow<List<ColorModel>>(emptyList())
    var colorList = _colorList.asStateFlow()

    init {
        getAllColors()
        _syncNumber.value = sharedPreferences.getInt("sync_number", 0)
    }

    private fun getRandomColor(): String {

        val red = Random.nextInt(256)
        val green = Random.nextInt(256)
        val blue = Random.nextInt(256)

        return String.format("#%02X%02X%02X", red, green, blue)
    }

    private fun getTodayDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val today = Date()
        return dateFormat.format(today)
    }

    fun insertColor() {
        viewModelScope.launch {
            repository.insertColor(getRandomColor(), getTodayDate())
            getAllColors()
            _syncNumber.value++
            saveSyncNumber()
        }
    }

    private fun getAllColors() {
        viewModelScope.launch {
            repository.listOfColor().collect {
                _colorList.value = it
            }
        }
    }

    fun uploadColorsToFirebase() {

        if (_syncNumber.value <= 0) return

        enableButton.value = false

        viewModelScope.launch {
            // Fetching all colors from Room
            val allColors = colorList.value.reversed()

            for (colorModel in allColors) {

                if (_syncNumber.value <= 0) break

                val key = databaseReference.child("colors").push().key // Generate a unique key
                key?.let {
                    databaseReference.child("colors").child(it).setValue(colorModel)
                    _syncNumber.value--
                    saveSyncNumber()
                    delay(300)
                }
            }
            enableButton.value = true
        }
    }

    private fun saveSyncNumber() {
        sharedPreferences.edit().putInt("sync_number", _syncNumber.value).apply()
    }
}


