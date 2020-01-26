package com.example.my_second_app.ui.gallery;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView packsRecycleView;
    List<PackShow> allPacks;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        packsRecycleView = root.findViewById(R.id.packRecyclerview);
        packsRecycleView.setHasFixedSize(true);
        packsRecycleView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        final packRecycleViewAdapter adapter = new packRecycleViewAdapter();
        packsRecycleView.setAdapter(adapter);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference packsRef = firebaseDatabase.getReference("packs");


        galleryViewModel.getAllPacks().observe(this, new Observer<List<PackShow>>() {
            @Override
            public void onChanged(@Nullable List<PackShow> packs) {
                adapter.setPacks(packs);
            }
        });
        return root;
    }
}