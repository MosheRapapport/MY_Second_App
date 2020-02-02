package com.example.my_second_app.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_second_app.R;
import com.example.my_second_app.entities.PackShow;
import com.google.firebase.database.FirebaseDatabase;

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

        final packssRecycleViewAdapter adapter = new packssRecycleViewAdapter();
        packsRecycleView.setAdapter(adapter);

        slideshowViewModel.getAll_OFFER_TO_COLLECT_PacksShow().observe(this, new Observer<List<PackShow>>() {
            @Override
            public void onChanged(@Nullable List<PackShow> packs) {
                adapter.setPacks(packs);
            }
        });
        return root;
    }

    public class packssRecycleViewAdapter extends RecyclerView.Adapter<packssRecycleViewAdapter.PacksViewHolder> {
        List<PackShow> packs;



        @Override
        public PacksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext() ).inflate(R.layout.activity_item_offer_to_collect_view,
                    parent,
                    false);

            return new PacksViewHolder(v);
        }

        @Override
        public void onBindViewHolder(PacksViewHolder holder, int position) {

            PackShow pack = packs.get(position);
            holder.HeaderTextView.setText("Package number "+(position+1));
            holder.storageLocation.setText("Storage Location: "+pack.getAddress());
            holder.PackFragileTextView.setText("Package Fragile: "+pack.getPackFragile());
            holder.packTypeTextView.setText("Package Type: "+pack.getPackType().toString());
            holder.packWeightTextView.setText("Package Weight: "+pack.getPackWeight().toString());
            holder.firstAndLastName.setText(pack.getDeliveryName()+" offered to take this package. Do you want to approve it?");

        }

        @Override
        public int getItemCount() {
            if(packs==null)
                return 0;
            return packs.size();
        }

        public void setPacks(List<PackShow> packs)
        {
            this.packs=packs;
            notifyDataSetChanged();
        }

        public PackShow getPackAt(int position) {
            return packs.get(position);
        }

        class PacksViewHolder extends RecyclerView.ViewHolder {

            TextView HeaderTextView;
            TextView firstAndLastName;
            TextView storageLocation;
            TextView PackFragileTextView;
            TextView packTypeTextView;
            TextView packWeightTextView;
            Button acceptButton;
            Button rejectButton;


            PacksViewHolder(View itemView) {
                super(itemView);
                HeaderTextView= itemView.findViewById(R.id.HeaderTextView);
                firstAndLastName = itemView.findViewById(R.id.deliveryNameView);
                storageLocation = itemView.findViewById(R.id.storageLocationView);
                PackFragileTextView = itemView.findViewById(R.id.packFregileView);
                packTypeTextView = itemView.findViewById(R.id.packTypeView);
                packWeightTextView = itemView.findViewById(R.id.packWeightView);
                acceptButton = itemView.findViewById(R.id.acceptButton);
                acceptButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        String id = packs.get(position).getAKey();
                        //                     String status= packs.get(position).getPackStatus().toString();
                        FirebaseDatabase.getInstance().getReference("packs").child(id).child("packStatus")
                                .setValue("ON_THE_WAY");
                     //   FirebaseDatabase.getInstance().getReference("packs").child(id).child("deliveryName")
                      //          .setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    }
                });
                rejectButton = itemView.findViewById(R.id.rejectButton);
                rejectButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        String id = packs.get(position).getAKey();
                        //                     String status= packs.get(position).getPackStatus().toString();
                        FirebaseDatabase.getInstance().getReference("packs").child(id).child("packStatus")
                                .setValue("SHIPPED");
                           FirebaseDatabase.getInstance().getReference("packs").child(id).child("deliveryName")
                                  .setValue("NO");
                    }
                });

                // itemView.setOnClickListener();
            }
        }
    }

}