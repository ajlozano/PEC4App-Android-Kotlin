package edu.uoc.android

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.uoc.android.models.Element
import com.squareup.picasso.Picasso
import kotlin.math.log


class museumsAdapter : ListAdapter<Element, museumsAdapter.MuseumsViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Element>() {
        override fun areItemsTheSame(oldItem: Element, newItem: Element): Boolean {
            return oldItem.puntId == newItem.puntId
        }

        override fun areContentsTheSame(oldItem: Element, newItem: Element): Boolean {
            return oldItem.equals(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuseumsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.museums_list_item, parent, false)
        return MuseumsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MuseumsViewHolder, position: Int) {
        val elements = getItem(position)

        Picasso.get().load(elements.imatge[0]).into(holder.image)
        holder.text.text = elements.adrecaNom.toString()
    }

    inner class MuseumsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.museum_img)
        val text = view.findViewById<TextView>(R.id.museum_textname)
    }
}