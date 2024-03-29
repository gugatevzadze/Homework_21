package com.example.homework_21.presentation.screen.main

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
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

    override fun setUp() {
        setUpRecyclerView()
    }

    override fun bindObservers() {
        observeMainState()
    }
    private fun setUpRecyclerView() {
        listAdapter = MainListRecyclerAdapter()
        binding.rvList.apply {
            adapter = listAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        viewModel.onEvent(MainEvents.GetItems)
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
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(MainEvents.UpdateErrorMessages)
        }
        binding.progressBar.isVisible = state.isLoading
    }
}