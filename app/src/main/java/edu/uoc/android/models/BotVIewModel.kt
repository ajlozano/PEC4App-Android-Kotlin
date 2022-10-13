package edu.uoc.android.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Random

class BotVIewModel : ViewModel() {
    private val answers =  listOf<String>(
        "Si",
        "No",
        "Pregunta de nuevo",
        "Es muy probable",
        "Tal vez",
        "Estás flipando!",
        "Quén te crees que eres?"
    )

    private val _fakeAnswers = MutableLiveData<String>()
    val fakeAnswers: LiveData<String>
        get() = _fakeAnswers

    private val _questions = MutableLiveData<String>()
    val questions: LiveData<String>
        get() = _questions

    init{
        _questions.value = ""
        _fakeAnswers.value = ""
    }

    fun addChat(question: String) : Boolean{
        if(question.isEmpty())
            return false
        _questions.value = question
        _fakeAnswers.value = answers.random()
        return true
    }

    fun addTestChat(question: String, answer: String) {
        _questions.value = question
        _fakeAnswers.value = answer
    }
}