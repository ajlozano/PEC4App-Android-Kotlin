package edu.uoc.android

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View.GONE
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import edu.uoc.android.databinding.ActivitySettingsBinding
import java.io.File

const val FILENAME = "imageapp"

class SettingsActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var textImage: TextView
    private var imageBitmap: Bitmap? = null
    private var picturePath = ""

    val getContent = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        success ->
        if (success) {
            checkPicturePath()
            Toast.makeText(this, "Image saved!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageView = binding.image
        textImage = binding.imageText
//        getBitmapFromStorage()
//        imageView.setImageBitmap(imageBitmap)
        binding.button.setOnClickListener {
            openCamera()
        }
    }

//    private fun getBitmapFromStorage() {
//        val photoPath = Environment.getExternalStorageDirectory().toString() + "/" + FILENAME + ".jpg"
//        if (photoPath.isNotEmpty()) {
//            imageBitmap = BitmapFactory.decodeFile(photoPath)
//            Toast.makeText(this, "HAS FILE", Toast.LENGTH_LONG).show()
//
//        }
//    }

    private fun openCamera() {
        val file = createImageFile(checkFile())
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(this, "$packageName.provider", file)
        } else  {
            Uri.fromFile(file)
        }
        getContent.launch(uri)
    }

    private fun checkFile(): File {
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES + "/UOCImageApp")
        if (fileDirectory != null) {
            if (fileDirectory.listFiles() != null) {
                Toast.makeText(
                    this, "Filename: ${fileDirectory.absolutePath}",
                    Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "HAS FILE", Toast.LENGTH_LONG).show()
            }
        }
        return fileDirectory!!
    }

    private fun checkPicturePath() {
        if (picturePath.isNotEmpty()) {
            imageBitmap = BitmapFactory.decodeFile(picturePath)
            textImage.visibility = GONE
            imageView.setImageBitmap(imageBitmap)
        } else {
            Toast.makeText(this, "No Image selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createImageFile(fileDirectory: File?): File {
        val fileName = FILENAME
        val file = File.createTempFile(fileName, ".jpg", fileDirectory)
        picturePath = file.absolutePath
        return file
    }
}