package com.unsoed.responsi1mobileh1d023056.network

import com.unsoed.responsi1mobileh1d023056.data.TeamResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    // Mengambil data klub, pelatih, dan pemain sekaligus
    @GET("v4/teams/59")
    suspend fun getTeamDetails(): Response<TeamResponse> //
}