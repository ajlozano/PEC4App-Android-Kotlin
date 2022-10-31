package edu.uoc.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import edu.uoc.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the FirebaseAnalytics instance.
        analytics = Firebase.analytics

        val bundle: Bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Event.SCREEN_VIEW, "Google Maps custom event")
        SendCustomEvent(bundle)

        binding.rellayMaps.setOnClickListener {
            val bundle: Bundle = Bundle()
            bundle.putString("GoggleMaps", "Google Maps custom event")
            SendCustomEvent(bundle)

            val intent = Intent(this, GoogleMapsActivity::class.java)
            startActivity(intent)
        }
        binding.rellayMuseums.setOnClickListener {
            val bundle: Bundle = Bundle()
            bundle.putString("Museus", "Museus custom event")
            SendCustomEvent(bundle)

            val intent = Intent(this, MuseumsActivity::class.java)
            startActivity(intent)
        }
        binding.rellayQuizzes.setOnClickListener {
            val bundle: Bundle = Bundle()
            bundle.putString("Quizz", "Quizz custom event")
            SendCustomEvent(bundle)

            val intent = Intent(this, QuizzActivity::class.java)
            startActivity(intent)
        }
        binding.rellaySettings.setOnClickListener {
            val bundle: Bundle = Bundle()
            bundle.putString("Settings", "Settings custom event")
            SendCustomEvent(bundle)

            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        binding.rellayFakebot.setOnClickListener {
            val bundle: Bundle = Bundle()
            bundle.putString("FakeBot", "Fake Bot custom event")
            SendCustomEvent(bundle)

            val intent = Intent(this, FakeBotActivity::class.java)
            startActivity(intent)
        }
    }

    private fun SendCustomEvent(bundle: Bundle) {
        analytics.logEvent("Custom_Event", bundle)
    }
}