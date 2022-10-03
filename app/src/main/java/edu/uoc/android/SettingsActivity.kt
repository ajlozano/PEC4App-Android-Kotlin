package edu.uoc.android

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import edu.uoc.android.databinding.ActivitySettingsBinding

private const val REQUEST_IMAGE_CAPTURE = 2000

class SettingsActivity : AppCompatActivity() {
//    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//    val getContent = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
//        bitmap ->
//
//    }
    private lateinit var imageView: ImageView
    private lateinit var textImage: TextView
    private var imageBitmap: Bitmap? = null
    val getContent = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        bitmap ->  imageBitmap = bitmap
        textImage.visibility = GONE
        imageView.setImageBitmap(imageBitmap)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageView = binding.image
        textImage = binding.imageText

        if (imageView.drawable != null) {

        }
        binding.button.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        getContent.launch(null)
    }
}