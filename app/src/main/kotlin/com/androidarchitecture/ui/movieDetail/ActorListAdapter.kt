package com.androidarchitecture.ui.movieDetail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidarchitecture.R
import com.androidarchitecture.domain.models.Actor
import com.androidarchitecture.utilities.ImageLoader
import com.androidarchitecture.utilities.inflate
import kotlinx.android.synthetic.main.adapter_actors.view.*

class ActorListAdapter(
    private val imageLoader: ImageLoader
) :
    ListAdapter<Actor, ActorListAdapter.ActorListViewHolder>(
        DIFF_UTIL
    ) {

    companion object {
        var DIFF_UTIL = object : DiffUtil.ItemCallback<Actor>() {

            override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.name == newItem.name &&
                        oldItem.image == newItem.image
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ActorListViewHolder(parent.inflate(R.layout.adapter_actors))

    override fun onBindViewHolder(holder: ActorListViewHolder, position: Int) {
        val data = currentList[position]
        holder.itemView.apply {
            imageLoader.loadCircularImage(
                ivActor,
                data.image
            )
        }
    }

    override fun getItemCount() = currentList.size

    inner class ActorListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        init {
//            itemView.setOnClickListener { movieClickListener(currentList[layoutPosition]) }
//        }
    }
}
