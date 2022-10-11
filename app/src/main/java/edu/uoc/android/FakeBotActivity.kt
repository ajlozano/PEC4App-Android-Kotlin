package edu.uoc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import edu.uoc.android.databinding.ActivityFakeBotBinding
import edu.uoc.android.databinding.ActivityMainBinding

class FakeBotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFakeBotBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}