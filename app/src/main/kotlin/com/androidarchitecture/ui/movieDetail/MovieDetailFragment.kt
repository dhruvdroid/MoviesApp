package com.androidarchitecture.ui.movieDetail

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidarchitecture.R
import com.androidarchitecture.base.BaseFragment
import com.androidarchitecture.domain.models.Movie
import com.androidarchitecture.ui.CustomGridLayoutManager
import com.androidarchitecture.ui.ListDecorator
import com.androidarchitecture.ui.movieList.MovieListAdapter
import com.androidarchitecture.utilities.ImageLoader
import com.androidarchitecture.utilities.expand
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.movie_details.*
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel>() {

    @Inject
    lateinit var imageLoader: ImageLoader

    lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        // calling the cast API
        viewModel.getCastDetails()
        // calling the Similar API
        viewModel.getSimilarMovies()
    }

    /**
     * Initialising views
     */
    private fun initView() {
        showDetails()
        // setting up the bottom sheet for the movie details
        bottomSheetBehavior = BottomSheetBehavior.from(flBottomSheet)
        rvActors.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // assign layout manager
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)

            adapter = ActorListAdapter(imageLoader)
            // specify an viewAdapter (see also next example)
            addItemDecoration(ListDecorator())
        }

        // setting up the Recycler view
        rvSimilarMovies.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // assign layout manager
            layoutManager =
                CustomGridLayoutManager(
                    context,
                    3
                )

            adapter =
                MovieListAdapter(imageLoader) {
//                    val action =
//                        MovieListFragmentDirections.showMoviesDetails(data)
//                    findNavController().navigate(action)
                }
            // specify an viewAdapter (see also next example)
            addItemDecoration(ListDecorator())
        }

        btBook.setOnClickListener {
            Toast.makeText(context, "WIP", Toast.LENGTH_SHORT).show()
        }

        btDetails.setOnClickListener {
            bottomSheetBehavior.expand()
        }

    }

    override fun getLayoutId() = R.layout.fragment_movie_detail

    override fun subscribeToObservers() {
        viewModel.apply {
            loading.observe(this@MovieDetailFragment, Observer { handleLoaderVisibility(it) })
            error.observe(this@MovieDetailFragment, Observer { showMessage(it) })

            actorData.observe(this@MovieDetailFragment, Observer {
                if (it.isEmpty()) {
                    tvActor.visibility = View.GONE
                    rvActors.visibility = View.GONE
                    return@Observer
                }

                tvActor.visibility = View.VISIBLE
                rvActors.visibility = View.VISIBLE

                (rvActors.adapter as ActorListAdapter).submitList(it)
            })

            similarMovies.observe(this@MovieDetailFragment, Observer {
                if (it.isEmpty()) {
                    tvSimilar.visibility = View.GONE
                    rvSimilarMovies.visibility = View.GONE
                    return@Observer
                }
                tvSimilar.visibility = View.VISIBLE
                rvSimilarMovies.visibility = View.VISIBLE
                (rvSimilarMovies.adapter as MovieListAdapter).submitList(it)
            })

        }
    }

    private fun showDetails(/*it: Movie*/) {
        val movieData = (viewModel.data as Movie)
        tvMovieTitle.text = movieData.title
        tvSynopsis.text = movieData.overview
        imageLoader.loadImage(
            ivMoviePoster,
            movieData.backdropImage
        )
    }

    override fun handleLoaderVisibility(isVisible: Boolean) {
        // TODO("Not yet implemented")
    }

    override fun createViewModel() = MovieDetailViewModel::class.java
}
