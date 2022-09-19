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

        binding.rellayMaps.setOnClickListener { _ ->
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
        binding.rellayMuseums.setOnClickListener { _ ->
            val intent = Intent(this, MuseumsActivity::class.java)
            startActivity(intent)
        }
    }
}