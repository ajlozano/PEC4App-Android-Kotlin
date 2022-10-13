package edu.uoc.android

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uoc.android.models.BotVIewModel
import org.w3c.dom.Text

class FakeBotAdapter(val fbList: MutableList<BotVIewModel>) : RecyclerView.Adapter<FakeBotAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fakebot_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewHolder = fbList[position]
        holder.question.text = itemsViewHolder.questions.value
        holder.answer.text = itemsViewHolder.fakeAnswers.value
    }

    override fun getItemCount(): Int = fbList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val question = view.findViewById<TextView>(R.id.fb_text_question)
        val answer = view.findViewById<TextView>(R.id.fb_text_answer)
    }


}