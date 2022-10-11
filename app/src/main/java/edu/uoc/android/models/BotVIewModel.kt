package edu.uoc.android.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BotVIewModel : ViewModel() {
    private val _fakeAnswers = MutableLiveData<ArrayList<String>>()
    val fakeAnswers: LiveData<ArrayList<String>>
        get() = _fakeAnswers

    init{
        loadFakeAnswers()
    }

    fun loadFakeAnswers(){
        _fakeAnswers.value?.add("Si")
        _fakeAnswers.value?.add("No")
        _fakeAnswers.value?.add("Pregunta de nuevo")
        _fakeAnswers.value?.add("Es muy probable")
        _fakeAnswers.value?.add("Tal vez")
        _fakeAnswers.value?.add("Estças flipando")
        _fakeAnswers.value?.add("Quien te crees que eres?")
    }
}