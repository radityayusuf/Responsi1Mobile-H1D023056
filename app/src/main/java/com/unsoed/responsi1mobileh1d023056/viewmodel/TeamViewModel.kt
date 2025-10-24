package com.unsoed.responsi1mobileh1d023056.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsoed.responsi1mobileh1d023056.data.TeamResponse
import com.unsoed.responsi1mobileh1d023056.repository.TeamRepository
import kotlinx.coroutines.launch

class TeamViewModel(private val repository: TeamRepository) : ViewModel() {

    private val _teamDetails = MutableLiveData<TeamResponse?>()
    val teamDetails: LiveData<TeamResponse?> = _teamDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun fetchTeamDetails() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getTeamDetails()
                if (response.isSuccessful) {
                    _teamDetails.postValue(response.body())
                } else {
                    _errorMessage.postValue("Failed to fetch data: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}