package com.unsoed.responsi1mobileh1d023056.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobileh1d023056.R
import com.unsoed.responsi1mobileh1d023056.viewmodel.TeamViewModel

class SquadFragment : Fragment() {

    private val viewModel: TeamViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var squadAdapter: SquadAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_squad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view_squad)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        squadAdapter = SquadAdapter(emptyList())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = squadAdapter
        }
    }

    private fun observeViewModel() {
        // Data pemain wajib dari API
        viewModel.teamDetails.observe(viewLifecycleOwner) { team ->
            team?.squad?.let { squadList ->
                squadAdapter.updateData(squadList)
            }
        }
    }
}