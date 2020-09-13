package com.androidarchitecture.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionManager
import android.view.View
import android.view.View.*
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import com.androidarchitecture.R
import com.androidarchitecture.base.BaseFragment
import com.androidarchitecture.ui.CustomGridLayoutManager
import com.androidarchitecture.ui.ListDecorator
import com.androidarchitecture.utilities.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject


//
// Created by Dhruv on 12/09/20.
//
@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchFragmentViewModel>() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun subscribeToObservers() {
        viewModel.loading.observe(this, Observer { handleLoaderVisibility(it) })

        viewModel.data.observe(this, Observer {
            if (it.isNotEmpty()) {
                rvSearch.visibility = VISIBLE
                tvNoResults.visibility = GONE
                (rvSearch.adapter as SearchListAdapter).submitList(it)
            } else {
                tvNoResults.visibility = VISIBLE
                rvSearch.visibility = GONE
            }
        })
    }

    override fun createViewModel() = SearchFragmentViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        intiView()
        (rvSearch.adapter as SearchListAdapter).submitList(emptyList())
    }

    private fun intiView() {
        // set up adapter and recycler view
        rvSearch.apply {
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
                SearchListAdapter(imageLoader) { data ->
//                    val action =
//                        MovieListFragmentDirections.showMoviesDetails(data)
//                    findNavController().navigate(action)
                }
            // specify an viewAdapter (see also next example)
            addItemDecoration(ListDecorator())
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // TODO("Not yet implemented")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s?.length ?: 0 > 2) {
                    viewModel.searchMovies(s.toString())
//                }
            }
        })

        addAnimationOperations()
    }

    private fun addAnimationOperations() {
        val constraint1 = ConstraintSet()
        constraint1.clone(searchParent)
        val constraint2 = ConstraintSet()
        constraint2.clone(context, R.layout.fragment_search_set)

        etSearch.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            TransitionManager.beginDelayedTransition(searchParent)
            constraint2.applyTo(searchParent)
        }
    }

    override fun handleLoaderVisibility(isVisible: Boolean) {
        loader.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}