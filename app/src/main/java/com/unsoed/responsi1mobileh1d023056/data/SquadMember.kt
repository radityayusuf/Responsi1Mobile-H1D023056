package com.unsoed.responsi1mobileh1d023056.data

// Model untuk Pemain
data class SquadMember(
    val id: Int,
    val name: String,
    val position: String?, // Posisi bisa null
    val nationality: String
)