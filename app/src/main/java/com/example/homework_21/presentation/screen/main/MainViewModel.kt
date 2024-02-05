package com.example.homework_21.presentation.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_21.data.common.Resource
import com.example.homework_21.domain.usecase.GetItemsByCategoryUseCase
import com.example.homework_21.domain.usecase.GetItemsUseCase
import com.example.homework_21.presentation.event.MainEvents
import com.example.homework_21.presentation.mapper.item.toPresentation
import com.example.homework_21.presentation.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val getItemsByCategoryUseCase: GetItemsByCategoryUseCase
) : ViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState:SharedFlow<MainState> get() = _mainState

    fun onEvent(event: MainEvents) {
        when (event) {
            is MainEvents.GetItems -> getItems()
            is MainEvents.UpdateErrorMessages -> updateErrorMessages(message = null)
            is MainEvents.RefreshData -> refreshData()
            is MainEvents.GetCategories -> getCategories()
            is MainEvents.GetItemsByCategory -> getItemsByCategory(event.category)
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            getItemsUseCase.invoke().collect { it ->
                when(it){
                    is Resource.Success -> {
                        _mainState.update { currentState ->
                            currentState.copy(
                                items = it.data.map { it.toPresentation() }
                            )
                        }
                    }
                    is Resource.Error -> {
                        updateErrorMessages(it.errorMessage)
                    }
                    is Resource.Loading -> {
                        _mainState.update { currentState ->
                            currentState.copy(
                                isLoading = it.loading
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            getItemsUseCase.invoke().collect { it ->
                when(it){
                    is Resource.Success -> {
                        val categories = it.data.map { it.category }.distinct()
                        _mainState.update { currentState ->
                            currentState.copy(
                                categories = categories
                            )
                        }
                    }
                    is Resource.Error -> {
                        updateErrorMessages(it.errorMessage)
                    }
                    is Resource.Loading -> {
                        _mainState.update { currentState ->
                            currentState.copy(
                                isLoading = it.loading
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getItemsByCategory(category: String) {
        viewModelScope.launch {
            getItemsByCategoryUseCase.invoke(category).collect { it ->
                when(it){
                    is Resource.Success -> {
                        _mainState.update { currentState ->
                            currentState.copy(
                                items = it.data.map { it.toPresentation() }
                            )
                        }
                    }
                    is Resource.Error -> {
                        updateErrorMessages(it.errorMessage)
                    }
                    is Resource.Loading -> {
                        _mainState.update { currentState ->
                            currentState.copy(
                                isLoading = it.loading
                            )
                        }
                    }
                }
            }
        }
    }

    private fun updateErrorMessages(message: String?) {
        _mainState.update { currentState ->
            currentState.copy(
                errorMessage = message
            )
        }
    }

    private fun refreshData(){
        getItems()
    }
}