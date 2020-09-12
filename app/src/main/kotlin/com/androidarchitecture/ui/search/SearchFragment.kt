package com.androidarchitecture.ui.search

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View.OnFocusChangeListener
import androidx.constraintlayout.widget.ConstraintSet
import com.androidarchitecture.R
import com.androidarchitecture.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.*


//
// Created by Dhruv on 12/09/20.
//

class SearchFragment : BaseFragment<SearchFragmentViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun subscribeToObservers() {
        // TODO("Not yet implemented")
    }

    override fun createViewModel() = SearchFragmentViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        intiView()
    }

    private fun intiView() {
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
}