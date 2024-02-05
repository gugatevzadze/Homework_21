package com.example.homework_21.presentation.screen.main

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_21.databinding.FragmentMainBinding
import com.example.homework_21.presentation.common.BaseFragment
import com.example.homework_21.presentation.event.MainEvents
import com.example.homework_21.presentation.state.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var listAdapter: MainListRecyclerAdapter
    private lateinit var categoryAdapter: MainCategoryRecyclerAdapter

    override fun setUp() {
        setUpListRecyclerView()
        setUpCategoryRecyclerView()
        setupListeners()
    }

    override fun bindObservers() {
        observeMainState()
    }
    private fun setUpListRecyclerView() {
        listAdapter = MainListRecyclerAdapter()
        binding.rvList.apply {
            adapter = listAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        viewModel.onEvent(MainEvents.GetItems)
    }

    private fun setUpCategoryRecyclerView() {
        categoryAdapter = MainCategoryRecyclerAdapter(
            onCategoryClick = { category ->
                viewModel.onEvent(MainEvents.GetItemsByCategory(category))
            }
        )
        binding.rvCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        viewModel.onEvent(MainEvents.GetCategories)
    }
    private fun setupListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onEvent(MainEvents.RefreshData)
        }
    }

    private fun observeMainState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainState.collect {
                    handleMainState(state = it)
                }
            }
        }
    }
    private fun handleMainState(state: MainState) {
        state.items?.let {
            listAdapter.submitList(it)
        }
        state.errorMessage?.let {
            binding.tvError.text = it
            binding.tvError.isVisible = true
            viewModel.onEvent(MainEvents.UpdateErrorMessages)
        }
        binding.swipeRefreshLayout.isRefreshing = state.isLoading
        state.categories?.let {
            val categoriesWithAll: MutableList<String> = it.toMutableList()
            categoriesWithAll.add(0, "All")
            categoryAdapter.submitList(categoriesWithAll)
        }
    }
}