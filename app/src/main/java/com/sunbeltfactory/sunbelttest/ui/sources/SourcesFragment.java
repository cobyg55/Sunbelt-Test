package com.sunbeltfactory.sunbelttest.ui.sources;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.sunbeltfactory.sunbelttest.R;

public class SourcesFragment extends Fragment {

    private SourcesViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(SourcesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sources, container, false);
        dashboardViewModel.getText().observe(this, s -> {

        });
        return root;
    }
}