package com.unsoed.responsi1mobileh1d023056

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.unsoed.responsi1mobileh1d023056.repository.TeamRepository
import com.unsoed.responsi1mobileh1d023056.ui.ProfileFragment
import com.unsoed.responsi1mobileh1d023056.viewmodel.TeamViewModel
import com.unsoed.responsi1mobileh1d023056.viewmodel.TeamViewModelFactory

class MainActivity : AppCompatActivity() {

    private val repository = TeamRepository()
    private val viewModelFactory = TeamViewModelFactory(repository)
    private val viewModel: TeamViewModel by viewModels { viewModelFactory }

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progress_bar_main)

        // Set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment())
                .commit()
            loadData()
        }

        observeViewModel()
    }

    private fun loadData() {
        // Hanya panggil API jika datanya belum ada
        if (viewModel.teamDetails.value == null) {
            viewModel.fetchTeamDetails()
        }
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    // Fungsi ini akan dipanggil oleh Fragment untuk pindah halaman
    fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Agar bisa tekan "Back"
            .commit()
    }
}