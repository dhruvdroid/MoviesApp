package com.androidarchitecture.ui.movieList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.androidarchitecture.R
import com.androidarchitecture.base.BaseFragment
import com.androidarchitecture.ui.CustomGridLayoutManager
import com.androidarchitecture.ui.ListDecorator
import com.androidarchitecture.utilities.ImageLoader
import com.androidarchitecture.utilities.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_list_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : BaseFragment<MoviesListViewModel>() {

    @Inject
    lateinit var imageLoader: ImageLoader

    lateinit var scrollListener: PaginationScrollListener

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        viewModel.getMovies()


    }

    /**
     * initialising views
     */
    private fun initViews() {
        rvMovies.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // assign layout manager
            layoutManager =
                CustomGridLayoutManager(
                    context,
                    2
                )

            adapter =
                MovieListAdapter(imageLoader) { data ->
                    val action =
                        MovieListFragmentDirections.showMoviesDetails(data)
                    findNavController().navigate(action)
                }
            // specify an viewAdapter (see also next example)
            addItemDecoration(ListDecorator())
        }

        scrollListener = object :
            PaginationScrollListener(rvMovies.layoutManager as CustomGridLayoutManager) {
            override fun loadMore() {
                viewModel.getMovies()
            }

            override fun isAllLoaded(): Boolean {
                return !viewModel.shouldPaginate
            }
        }

        rvMovies.addOnScrollListener(scrollListener)
    }

    override fun getLayoutId() = R.layout.movie_list_fragment

    override fun subscribeToObservers() {
        viewModel.loading.observe(this, Observer { handleLoaderVisibility(it) })
        viewModel.data.observe(
            this,
            Observer {
                (rvMovies.adapter as MovieListAdapter).submitList(it)
                scrollListener.loadCompleted()
            }
        )
        viewModel.errorMessage.observe(this, Observer { showMessage(it) })
    }

    override fun handleLoaderVisibility(isVisible: Boolean) {
        loader.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun createViewModel() = MoviesListViewModel::class.java
}
