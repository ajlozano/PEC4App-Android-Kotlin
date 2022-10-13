package edu.uoc.android.models

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class BotVIewModel : ViewModel() {
    private val answers =  listOf(
        "Si",
        "No",
        "Pregunta de nuevo",
        "Es muy probable",
        "Tal vez",
        "Estás flipando!",
        "Quén te crees que eres?"
    )

    private val _chatMessageListLiveData = MutableLiveData<MutableList<ChatMessage>>()
    val chatMessageListLiveData: LiveData<MutableList<ChatMessage>>
        get() = _chatMessageListLiveData

    private var handler: Handler = Handler()

    init{
        _chatMessageListLiveData.value = mutableListOf()
    }

    fun addMessage(chatMessage: ChatMessage)
    {
        val mutableList = _chatMessageListLiveData.value!!
        mutableList.add(chatMessage)
        _chatMessageListLiveData.value = mutableList
    }

    fun createResponse() {
        val runnable = Runnable {
            val random = Random().nextInt(answers.size)
            val answer = answers[random]
            val chatMessage = ChatMessage(System.currentTimeMillis(), answer, false)
            val mutableList = _chatMessageListLiveData.value!!
            mutableList.add(chatMessage)
            _chatMessageListLiveData.value = mutableList
        }

        handler.postDelayed(runnable, 2000)
    }
}