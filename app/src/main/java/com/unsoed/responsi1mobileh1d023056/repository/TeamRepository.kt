package com.unsoed.responsi1mobileh1d023056.repository

import com.unsoed.responsi1mobileh1d023056.network.RetrofitClient

class TeamRepository {
    private val apiService = RetrofitClient.instance

    suspend fun getTeamDetails() = apiService.getTeamDetails()
}