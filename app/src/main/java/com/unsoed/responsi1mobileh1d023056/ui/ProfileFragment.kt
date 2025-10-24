package com.unsoed.responsi1mobileh1d023056.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.unsoed.responsi1mobileh1d023056.MainActivity
import com.unsoed.responsi1mobileh1d023056.R
import com.unsoed.responsi1mobileh1d023056.viewmodel.TeamViewModel

class ProfileFragment : Fragment() {

    private val viewModel: TeamViewModel by activityViewModels()

    // Deklarasikan semua view baru
    private lateinit var imgStadiumBackground: ImageView
    private lateinit var imgCrest: ImageView
    private lateinit var tvClubName: TextView
    private lateinit var tvBriefHistory: TextView
    private lateinit var btnClubHistory: MaterialCardView
    private lateinit var btnHeadCoach: MaterialCardView
    private lateinit var btnTeamSquad: MaterialCardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hubungkan view dengan ID
        imgStadiumBackground = view.findViewById(R.id.img_stadium_background)
        imgCrest = view.findViewById(R.id.img_club_crest)
        tvClubName = view.findViewById(R.id.tv_club_name)
        tvBriefHistory = view.findViewById(R.id.tv_brief_history)
        btnClubHistory = view.findViewById(R.id.btn_club_history)
        btnHeadCoach = view.findViewById(R.id.btn_head_coach)
        btnTeamSquad = view.findViewById(R.id.btn_team_squad)

        observeViewModel()
        setupClickListeners()
    }

    private fun observeViewModel() {
        viewModel.teamDetails.observe(viewLifecycleOwner) { team ->
            team?.let {
                // Gunakan Glide untuk memuat logo klub
                Glide.with(this)
                    .load(it.crest)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imgCrest)

                // (Opsional) Anda bisa ganti placeholder stadion dengan logo klub jika mau
                // Glide.with(this).load(it.crest).into(imgStadiumBackground)

                tvClubName.text = it.name

                // Buat sejarah singkat dari data API
                val briefInfo = "Founded in ${it.founded}. " +
                        "Plays its home matches at ${it.venue}. " +
                        "Club colors: ${it.clubColors}."
                tvBriefHistory.text = briefInfo
            }
        }
    }

    private fun setupClickListeners() {
        // Panggil fungsi navigateTo di MainActivity saat tombol diklik

        btnClubHistory.setOnClickListener {
            (activity as? MainActivity)?.navigateTo(HistoryFragment())
        }

        btnHeadCoach.setOnClickListener {
            (activity as? MainActivity)?.navigateTo(CoachFragment())
        }

        btnTeamSquad.setOnClickListener {
            (activity as? MainActivity)?.navigateTo(SquadFragment())
            }
    }
}