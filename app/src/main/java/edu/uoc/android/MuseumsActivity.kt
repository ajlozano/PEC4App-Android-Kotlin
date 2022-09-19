package edu.uoc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uoc.android.databinding.ActivityMainBinding
import edu.uoc.android.databinding.ActivityMuseumsBinding

class MuseumsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMuseumsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}