package com.gundogan.taskmanager.ui.workingplaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gundogan.taskmanager.databinding.FragmentMycardsBinding;

public class WorkingPlacesFragment extends Fragment {

    private FragmentMycardsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WorkingPlacesViewModel homeViewModel =
                new ViewModelProvider(this).get(WorkingPlacesViewModel.class);

        binding = FragmentMycardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}