package com.example.my_second_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.my_second_app.R;
import com.example.my_second_app.entities.PackShow;
import com.example.my_second_app.entities.enums.PackStatus;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    List<PackShow> allPacks;
    private TextView userMail;
    private TextView header;
    private TextView all_packs_detals;
    private TextView shipped_pack_detals;
    private TextView offer_to_collect_pack_detals;
    private TextView on_the_way_pack_detals;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        userMail = root.findViewById(R.id.user_mail);
        userMail.setText("Welcome "+user);

        header = root.findViewById(R.id.heder);
        header.setText("Your overall data on the system is");
        all_packs_detals = root.findViewById(R.id.all_packs_detals);
        shipped_pack_detals = root.findViewById(R.id.shipped_pack_detals);
        offer_to_collect_pack_detals = root.findViewById(R.id.offer_to_collect_pack_detals);
        on_the_way_pack_detals = root.findViewById(R.id.on_the_way_pack_detals);
        homeViewModel.getAllPacks().observe(this, new Observer<List<PackShow>>() {
            @Override
            public void onChanged(@Nullable List<PackShow> s) {
                updateUI(s);
            }
        });
        return root;
    }
    public void updateUI(List<PackShow> packShows)
    {
        int allPacks = 0, shippedPacks=0,offerToCollectPacks=0,onTheWayPacks=0;
        allPacks=packShows.size();
        for (PackShow p:packShows) {
            if(p.getPackStatus()== PackStatus.SHIPPED)
                shippedPacks++;
            if(p.getPackStatus()== PackStatus.OFFER_TO_COLLECT)
                offerToCollectPacks++;
            if(p.getPackStatus()== PackStatus.ON_THE_WAY)
                onTheWayPacks++;

        }
        all_packs_detals.setText(allPacks+" packs, such as:");
        shipped_pack_detals.setText(shippedPacks+" shipped packs.");
        offer_to_collect_pack_detals.setText(offerToCollectPacks+" offer to collect packs.");
        on_the_way_pack_detals.setText(onTheWayPacks+" on the way packs.");

    }
}