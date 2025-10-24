package com.unsoed.responsi1mobileh1d023056.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobileh1d023056.R
import com.unsoed.responsi1mobileh1d023056.data.SquadMember
import com.google.android.material.card.MaterialCardView

class SquadAdapter(private var players: List<SquadMember>) :
    RecyclerView.Adapter<SquadAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: MaterialCardView = view.findViewById(R.id.card_player)
        val tvName: TextView = view.findViewById(R.id.tv_player_name)
        val tvPosition: TextView = view.findViewById(R.id.tv_player_position)
        val tvNationality: TextView = view.findViewById(R.id.tv_player_nationality)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_card, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        val context = holder.itemView.context

        holder.tvName.text = player.name
        holder.tvPosition.text = player.position ?: "N/A" // Handle jika posisi null
        holder.tvNationality.text = player.nationality

        // Logika untuk mewarnai kartu berdasarkan posisi
        val cardColor = when (player.position) {
            "Goalkeeper" -> ContextCompat.getColor(context, R.color.player_goalkeeper)
            "Defender", "Defence" -> ContextCompat.getColor(context, R.color.player_defender) // Tambahkan "Defence"
            "Midfielder", "Midfield" -> ContextCompat.getColor(context, R.color.player_midfielder) // Jaga-jaga
            "Attacker", "Forward" -> ContextCompat.getColor(context, R.color.player_forward)
            else -> ContextCompat.getColor(context, R.color.white)
        }

        holder.card.setCardBackgroundColor(cardColor)
    }

    // Fungsi untuk update data di adapter
    fun updateData(newPlayers: List<SquadMember>) {
        players = newPlayers
        notifyDataSetChanged()
    }
}