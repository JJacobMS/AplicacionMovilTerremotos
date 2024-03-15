package com.example.desapp008;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.desapp008.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EqAdapter adapter = new EqAdapter();

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getEarthquake();
        binding.recyclerView.setAdapter(adapter);
        viewModel.geteqList().observe(this, eqList->{
           adapter.submitList(eqList);
        });
        adapter.setOnItemClickListener(new EqAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Earthquake earthquake) {
                Toast.makeText(getApplicationContext(), earthquake.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}