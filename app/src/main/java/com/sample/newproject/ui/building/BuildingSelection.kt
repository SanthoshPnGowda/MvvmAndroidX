package com.sample.newproject.ui.building

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.newproject.R
import com.sample.newproject.databinding.ActivityBuildingSelectionBinding
import com.sample.newproject.injection.ViewModelFactory
import com.sample.newproject.ui.home.HomeViewModel

class BuildingSelection : AppCompatActivity() {

    private lateinit var binding: ActivityBuildingSelectionBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_building_selection)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(HomeViewModel::class.java)
        binding.model = viewModel

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


    }
}
