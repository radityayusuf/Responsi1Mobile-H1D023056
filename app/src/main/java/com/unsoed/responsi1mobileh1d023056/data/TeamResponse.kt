package com.unsoed.responsi1mobileh1d023056.data

// Model data utama
data class TeamResponse(
    val id: Int,
    val name: String,
    val shortName: String,
    val crest: String, // URL logo
    val founded: Int,
    val clubColors: String,
    val venue: String,
    val coach: Coach,
    val squad: List<SquadMember>
)