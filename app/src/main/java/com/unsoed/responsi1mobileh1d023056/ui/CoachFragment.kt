package com.unsoed.responsi1mobileh1d023056.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.unsoed.responsi1mobileh1d023056.R
import com.unsoed.responsi1mobileh1d023056.viewmodel.TeamViewModel

class CoachFragment : Fragment() {

    private val viewModel: TeamViewModel by activityViewModels()

    private lateinit var tvCoachName: TextView
    private lateinit var tvCoachNationality: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coach, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCoachName = view.findViewById(R.id.tv_coach_name)
        tvCoachNationality = view.findViewById(R.id.tv_coach_nationality)

        observeViewModel()
    }

    private fun observeViewModel() {
        // Data pelatih wajib dari API
        viewModel.teamDetails.observe(viewLifecycleOwner) { team ->
            team?.coach?.let { coach ->
                tvCoachName.text = coach.name
                tvCoachNationality.text = "Nationality: ${coach.nationality}"
            }
        }
    }
}