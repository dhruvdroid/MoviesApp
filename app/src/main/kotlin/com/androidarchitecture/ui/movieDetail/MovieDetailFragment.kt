package com.androidarchitecture.ui.movieDetail

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
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
import kotlinx.android.synthetic.main.movie_detail_fragment.*
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
        viewModel.getCastDetails()
        viewModel.getSimilarMovies()
    }

    private fun initView() {
        showDetails()
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

        }

        btDetails.setOnClickListener {
            bottomSheetBehavior.expand()
        }

    }

    override fun getLayoutId() = R.layout.movie_detail_fragment

    override fun subscribeToObservers() {
        viewModel.apply {
            loading.observe(this@MovieDetailFragment, Observer { handleLoaderVisibility(it) })
            error.observe(this@MovieDetailFragment, Observer { showMessage(it) })

            actorData.observe(this@MovieDetailFragment, Observer {
                (rvActors.adapter as ActorListAdapter).submitList(it)
            })

            similarMovies.observe(this@MovieDetailFragment, Observer {
                if (it.isEmpty()) {
                    tvSimilar.visibility = View.GONE
                    rvSimilarMovies.visibility = View.GONE
                    return@Observer
                }

                (rvSimilarMovies.adapter as MovieListAdapter).submitList(it)
            })

        }
    }

    private fun showDetails(/*it: Movie*/) {
        val movieData = (viewModel.data as Movie)
        tvMovieTitle.text = movieData.title
        imageLoader.loadImage(
            ivMoviePoster,
            movieData.backdropImage
        )
    }

    private fun handleLoaderVisibility(isVisible: Boolean) {
        // loader.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun createViewModel() = MovieDetailViewModel::class.java
}
