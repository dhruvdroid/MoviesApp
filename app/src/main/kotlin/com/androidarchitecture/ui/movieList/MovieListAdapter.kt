package com.androidarchitecture.ui.movieList

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidarchitecture.R
import com.androidarchitecture.domain.models.Movie
import com.androidarchitecture.utilities.ImageLoader
import com.androidarchitecture.utilities.inflate
import kotlinx.android.synthetic.main.adapter_movies.view.*

class MovieListAdapter(
    private val imageLoader: ImageLoader,
    val movieClickListener: (Movie) -> Unit
) :
    ListAdapter<Movie, MovieListAdapter.MovieListViewHolder>(
        DIFF_UTIL
    ) {

    companion object {
        var DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.title == newItem.title &&
                        oldItem.image == newItem.image
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieListViewHolder(parent.inflate(R.layout.adapter_movies))

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val data = currentList[position]
        holder.itemView.apply {
            imageLoader.loadRoundedCornersImage(ivMovie, data.image)
        }
    }

    override fun getItemCount() = currentList.size

    inner class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { movieClickListener(currentList[layoutPosition]) }
        }
    }
}
