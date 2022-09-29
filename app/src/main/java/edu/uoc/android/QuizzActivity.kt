package edu.uoc.android

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import edu.uoc.android.databinding.ActivityQuizzBinding
import edu.uoc.android.models.Quizz


class QuizzActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityQuizzBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.quizzRecycler.adapter
        //setDataBase()
        manageDataBase(binding)
    }

    private fun setDataBase() {
        // Create a new user with a first and last name
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    TAG,
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
    }

    private fun manageDataBase(binding : ActivityQuizzBinding) {
        binding.quizzRecycler.layoutManager = LinearLayoutManager(this)
        db.collection("Quizzes")
            .get()
            .addOnCompleteListener() { task->
                Log.e(TAG, "Sucessful getting documents.")
                if (task.isSuccessful) {
                    val quizzList : MutableList<Quizz> = mutableListOf()
                    for (document in task.result) {
                        val quizz = Quizz(
                            document.data.get("id").toString(),
                            document.data.get("title").toString(),
                            document.data.get("image").toString(),
                            document.data.get("choice1").toString(),
                            document.data.get("choice2").toString(),
                            document.data.get("choice3").toString(),
                            document.data.get("rightchoice").toString()
                            )
                        quizzList.add(quizz)
                        //Toast.makeText(this, document.data.get("rightchoice").toString(), Toast.LENGTH_LONG).show()
                    }
                    val adapter = QuizzListAdapter()
                    binding.quizzRecycler.adapter = adapter
                    adapter.submitList(quizzList)
                } else {
                    Log.e(TAG, "Error getting documents.", task.exception)
                }
            }
    }
}