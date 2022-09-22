package edu.uoc.android

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import edu.uoc.android.databinding.ActivityMuseumsBinding
import edu.uoc.android.models.Museums
import edu.uoc.android.rest.RetrofitFactory.museumAPI
import retrofit2.Response

class MuseumsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMuseumsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callbackManager(binding)
    }

    private fun callbackManager(binding: ActivityMuseumsBinding) {
        binding.museumRecycler.layoutManager = LinearLayoutManager(this)
        val call = museumAPI.museums("1","25")
        call?.enqueue(object : retrofit2.Callback<Museums?> {
            override fun onResponse(call: retrofit2.Call<Museums?>, response: Response<Museums?>) {
                if (response.code() == 200) {
                    showProgress(false, binding)
                    val museums = response.body()!!
                    val adapter = museumsAdapter()
                    binding.museumRecycler.adapter = adapter
                    adapter.submitList(museums.elements)
                }
            }
            override fun onFailure(call: retrofit2.Call<Museums?>, t: Throwable) {
                 Log.d(TAG, "Retrofit2.Call Museums: Failled to enqueue")
            }
        })
    }

    private fun showProgress(state: Boolean, binding: ActivityMuseumsBinding) {
        if(state) {
            binding.museumProgress.visibility = View.VISIBLE
        } else {
            binding.museumProgress.visibility = View.GONE
        }
    }
}

