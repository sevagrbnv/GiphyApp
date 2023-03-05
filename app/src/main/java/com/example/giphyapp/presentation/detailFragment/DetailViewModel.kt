package com.example.giphyapp.presentation.detailFragment

import androidx.lifecycle.ViewModel
import com.example.giphyapp.domain.usecases.GetItemFromListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getItemFromListUseCase: GetItemFromListUseCase
) : ViewModel() {
    fun getItem(itemId: Int) = getItemFromListUseCase.execute(itemId)
}