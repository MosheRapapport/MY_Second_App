package com.example.my_second_app.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_second_app.R;
import com.example.my_second_app.entities.Pack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    private RecyclerView packsRecycleView;
    private List<Pack> allShippedPacks;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        packsRecycleView = root.findViewById(R.id.packRecyclerview);
        packsRecycleView.setHasFixedSize(true);
        packsRecycleView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        allShippedPacks=new ArrayList<Pack>();

        final packsRecycleViewAdapter adapter = new packsRecycleViewAdapter();
        packsRecycleView.setAdapter(adapter);

        LiveData<DataSnapshot> liveData = toolsViewModel.getdataSnapshotLiveData();
        liveData.observe(this, new Observer<DataSnapshot>() {
                    @Override
                    public void onChanged(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {
                            for (DataSnapshot readData : dataSnapshot.getChildren()) {
                                Pack data = readData.getValue(Pack.class);
                                allShippedPacks.add(data);
                            }
                            adapter.setPacks(allShippedPacks);
                        }
                    }
        });
        return root;
    }
    public class packsRecycleViewAdapter extends RecyclerView.Adapter<packsRecycleViewAdapter.PacksViewHolder> {
        List<Pack> packs;



        @Override
        public PacksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext() ).inflate(R.layout.activity_item_frends_view,
                    parent,
                    false);

            return new PacksViewHolder(v);
        }

        @Override
        public void onBindViewHolder(PacksViewHolder holder, int position) {

            Pack pack = packs.get(position);
            holder.HeaderTextView.setText("Package number "+(position+1));
            holder.firstAndLastName.setText("Friend Name: "+pack.getRecipient().getFirstName()+pack.getRecipient().getLastName());
            holder.storageLocation.setText("Storage Location: "+pack.getStorageLocation().getMAddress());
            holder.PackFragileTextView.setText("Package Fragile: "+pack.isPackFragile());
            holder.packTypeTextView.setText("Package Type: "+pack.getPackType().toString());
            holder.packWeightTextView.setText("Package Weight: "+pack.getPackWeight().toString());

        }

        @Override
        public int getItemCount() {
            if(packs==null)
                return 0;
            return packs.size();
        }

        public void setPacks(List<Pack> packs)
        {
            this.packs=packs;
            notifyDataSetChanged();
        }

        public Pack getPackAt(int position) {
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


            PacksViewHolder(View itemView) {
                super(itemView);
                HeaderTextView= itemView.findViewById(R.id.HeaderTextView);
                firstAndLastName = itemView.findViewById(R.id.firstAndLastNameView);
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
                                .setValue("OFFER_TO_COLLECT");
                        FirebaseDatabase.getInstance().getReference("packs").child(id).child("deliveryName")
                                .setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    }
                });

                // itemView.setOnClickListener();
            }
        }
    }

}