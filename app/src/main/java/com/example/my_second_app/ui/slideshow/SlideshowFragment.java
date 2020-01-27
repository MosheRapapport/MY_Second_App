package com.example.my_second_app.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_second_app.R;
import com.example.my_second_app.entities.PackShow;
import com.example.my_second_app.ui.packRecycleViewAdapter;

import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView packsRecycleView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        packsRecycleView = root.findViewById(R.id.packRecyclerview);
        packsRecycleView.setHasFixedSize(true);
        packsRecycleView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        final packRecycleViewAdapter adapter = new packRecycleViewAdapter();
        packsRecycleView.setAdapter(adapter);

        slideshowViewModel.getAll_OFFER_TO_COLLECT_PacksShow().observe(this, new Observer<List<PackShow>>() {
            @Override
            public void onChanged(@Nullable List<PackShow> packs) {
                adapter.setPacks(packs);
            }
        });
        return root;
    }
}