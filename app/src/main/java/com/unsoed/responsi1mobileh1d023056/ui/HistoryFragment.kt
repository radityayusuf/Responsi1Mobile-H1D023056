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

class HistoryFragment : Fragment() {

    private val viewModel: TeamViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvFullHistory: TextView = view.findViewById(R.id.tv_full_history)

        // Kita gunakan string sejarah panjang dari strings.xml
        tvFullHistory.text = getString(R.string.club_history_placeholder)
    }
}