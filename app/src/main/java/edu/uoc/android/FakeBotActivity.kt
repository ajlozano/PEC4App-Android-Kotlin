package edu.uoc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import edu.uoc.android.databinding.ActivityFakeBotBinding
import edu.uoc.android.databinding.ActivityMainBinding
import edu.uoc.android.models.BotVIewModel
import edu.uoc.android.models.ChatMessage

class FakeBotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFakeBotBinding
    private lateinit var adapter: FakeBotAdapter
    private lateinit var viewModel: BotVIewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFakeBotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(BotVIewModel::class.java)
        binding.fakebotRecycler.layoutManager = LinearLayoutManager(this)
        adapter = FakeBotAdapter(this)
        binding.fakebotRecycler.adapter = adapter

        viewModel.chatMessageListLiveData.observe(this, Observer {
            chatMessageList ->
            adapter.submitList(chatMessageList)
            binding.fakebotRecycler.scrollToPosition(chatMessageList.size - 1)

        })

        binding.sendIcon.setOnClickListener {
            chatManagement()
        }
    }

    private fun chatManagement() {
        val message = binding.editQuestion.text.toString()
        if(message.isEmpty()) {
            Toast.makeText(this,"Please insert a question", Toast.LENGTH_SHORT).show()
        } else {
            val chatMessage = ChatMessage(System.currentTimeMillis(), message, true)
            viewModel.addMessage(chatMessage)
            viewModel.createResponse()
            binding.editQuestion.text.clear()
        }
    }
}