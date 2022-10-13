package edu.uoc.android

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.content.ContextCompat
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.marginStart
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.uoc.android.databinding.FakebotListItemBinding
import edu.uoc.android.models.ChatMessage

class FakeBotAdapter(private val context: Context) : ListAdapter<ChatMessage, FakeBotAdapter.ViewHolder>(DiffCallback){
    companion object DiffCallback : DiffUtil.ItemCallback<ChatMessage>() {
        override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem.timeStamp == newItem.timeStamp
        }

        override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fakebot_list_item, parent, false)
        val binding = FakebotListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chatMessage = getItem(position)
        holder.bind(chatMessage)
    }

    inner class ViewHolder(private val binding: FakebotListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatMessage: ChatMessage) {
            val fakeBotListItemMessage = binding.fbChatMessage
            if (chatMessage.isQuestion) {
                fakeBotListItemMessage.gravity = Gravity.END
                fakeBotListItemMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                fakeBotListItemMessage.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            } else {
                fakeBotListItemMessage.gravity = Gravity.START
                fakeBotListItemMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.grey))
                fakeBotListItemMessage.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            }

            fakeBotListItemMessage.text = chatMessage.message
            binding.executePendingBindings()
        }
    }


}