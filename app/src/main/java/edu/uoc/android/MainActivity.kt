package edu.uoc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uoc.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rellayMaps.setOnClickListener {
            val intent = Intent(this, GoogleMapsActivity::class.java)
            startActivity(intent)
        }
        binding.rellayMuseums.setOnClickListener {
            val intent = Intent(this, MuseumsActivity::class.java)
            startActivity(intent)
        }
        binding.rellayQuizzes.setOnClickListener {
            val intent = Intent(this, QuizzActivity::class.java)
            startActivity(intent)
        }
        binding.rellaySettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        binding.rellayFakebot.setOnClickListener {
            val intent = Intent(this, FakeBotActivity::class.java)
            startActivity(intent)
        }
    }
}