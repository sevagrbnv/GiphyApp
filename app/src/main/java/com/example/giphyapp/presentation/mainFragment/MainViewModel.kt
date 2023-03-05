package com.example.giphyapp.presentation.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyapp.data.api.NetworkResult
import com.example.giphyapp.domain.usecases.GetLIstOfGifsUseCase
import com.example.giphyapp.domain.model.Gif
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLIstOfGifsUseCase: GetLIstOfGifsUseCase
) : ViewModel() {

    private val _gifList = MutableLiveData<NetworkResult<List<Gif>>>()
    val gifList: LiveData<NetworkResult<List<Gif>>>
        get() = _gifList

    private val _errorInput = MutableLiveData<Boolean>()
    val errorInput: LiveData<Boolean>
        get() = _errorInput

    fun getListOfGifs(query: String) {
        viewModelScope.launch {
            getLIstOfGifsUseCase.execute(parseString(query)).let {
                _gifList.value = it
            }
        }
    }

    fun test() {}

    private fun isValidInput(inputDesc: String): Boolean {
        var result = true
        if (inputDesc.isBlank()) {
            _errorInput.value = true
            result = false
        }
        return result
    }

    private fun parseString(inputDesc: String?): String = inputDesc?.trim() ?: ""

    fun resetErrorInputDesc() {
        _errorInput.value = false
    }

    fun checkCorrectInput(query: String): Boolean {
        val parseInput = parseString(query)
        return isValidInput(parseInput)
    }
}