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

class FakeBotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFakeBotBinding
    private val botList = mutableListOf<BotVIewModel>()
    private val adapter = FakeBotAdapter(botList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFakeBotBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fakebotRecycler.layoutManager = LinearLayoutManager(this)
        binding.sendIcon.setOnClickListener {
            chatManagement()

        }
    }

    private fun chatManagement() {
        val chat = BotVIewModel()
        if (chat.addChat(binding.editQuestion.text.toString())) {
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed(Runnable {
                adapter.fbList.add(chat)
                binding.fakebotRecycler.adapter = adapter
                binding.fakebotRecycler.scrollToPosition(adapter.fbList.size - 1)
            }, 1000)
            binding.editQuestion.text.clear()
        } else {
            Toast.makeText(this,"Please insert a question", Toast.LENGTH_SHORT).show()
        }
    }
}