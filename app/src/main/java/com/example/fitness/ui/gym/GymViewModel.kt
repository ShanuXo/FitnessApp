package com.example.fitness.ui.gym

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GymViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gym Fragment"
    }
    val text: LiveData<String> = _text
}