package edu.uoc.android

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.uoc.android.databinding.QuizzListItemBinding
import edu.uoc.android.models.Quizz

class QuizzListAdapter() :
    ListAdapter<Quizz, QuizzListAdapter.QuizzViewHolder>(QuizzDiffCallback()) {

    class QuizzDiffCallback: DiffUtil.ItemCallback<Quizz>() {
        override fun areItemsTheSame(oldItem: Quizz, newItem: Quizz): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Quizz, newItem: Quizz): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizzViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quizz_list_item, parent, false)
        return QuizzViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizzViewHolder, position: Int) {
        val quizz = getItem(position)
        with(holder){
            binding.quizzTitle.text = quizz.title
            binding.quizzChoice1.text = quizz.choice1
            binding.quizzChoice2.text = quizz.choice2
            binding.quizzChoice3.text = quizz.choice3
            Picasso.get().load(quizz.img).into(holder.image)
            when (quizz.rightChoice.toInt()) {
                1 -> binding.quizzChoice1.setTypeface(null, Typeface.BOLD)
                2 -> binding.quizzChoice2.setTypeface(null, Typeface.BOLD)
                3 -> binding.quizzChoice3.setTypeface(null, Typeface.BOLD)
            }

        }
    }

    inner class QuizzViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = QuizzListItemBinding.bind(view)
        val title = view.findViewById<TextView>(R.id.quizz_title)
        val image = view.findViewById<ImageView>(R.id.quizz_img)
        val choice1 = view.findViewById<TextView>(R.id.quizz_choice1)
        val choice2 = view.findViewById<TextView>(R.id.quizz_choice2)
        val choice3 = view.findViewById<TextView>(R.id.quizz_choice3)
    }

}