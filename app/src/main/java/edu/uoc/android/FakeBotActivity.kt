package edu.uoc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    //private lateinit var viewModel: BotVIewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFakeBotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fakebotRecycler.layoutManager = LinearLayoutManager(this)
        val botList = mutableListOf<BotVIewModel>()
//        val chat = BotVIewModel()
//        chat.addTestChat("prueba?", "Respuesta")
//        botList.add(chat)
        val adapter = FakeBotAdapter(botList)

        binding.sendIcon.setOnClickListener {
            val chat = BotVIewModel()
            if (chat.addChat(binding.editQuestion.text.toString())) {
                adapter.fbList.add(chat)
                binding.fakebotRecycler.adapter = adapter
                binding.fakebotRecycler.scrollToPosition(adapter.fbList.size - 1)
            } else {
                Toast.makeText(this,"Please insert a question", Toast.LENGTH_SHORT).show()
            }
            //botList.add(chat)
        }

//        viewModel = ViewModelProvider(this).get(BotVIewModel::class.java)
//        binding.lifecycleOwner = this
//        viewModel.questions.observe(this, Observer {
//        })
//        viewModel.fakeAnswers.observe(this, Observer {
//        })
    }
}