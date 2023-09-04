package ua.com.kotlin.simplegifviewer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.com.kotlin.simplegifviewer.R
import ua.com.kotlin.simplegifviewer.model.GifData

class GifAdapter(
    private val context: Context,
    private val gifList: MutableList<GifData> = mutableListOf()
) : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_gif, parent, false)
        return GifViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifItem = gifList[position].images.original

        Glide.with(context)
            .asGif()
            .load(gifItem.url)
            .into(holder.gifImageView)
    }

    override fun getItemCount(): Int = gifList.size

    inner class GifViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gifImageView: ImageView = view.findViewById(R.id.gifImageView)
    }

    fun updateData(newData: List<GifData>) {
        gifList.clear()
        gifList.addAll(newData)
        notifyDataSetChanged()
    }
}
