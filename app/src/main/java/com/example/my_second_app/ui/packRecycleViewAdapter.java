package com.example.my_second_app.ui;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.my_second_app.R;
import com.example.my_second_app.entities.PackShow;

import java.util.List;

public class packRecycleViewAdapter extends RecyclerView.Adapter<packRecycleViewAdapter.PackViewHolder> {
    List<PackShow> packs;



    @Override
    public PackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext() ).inflate(R.layout.activity_item_view,
                parent,
                false);

        return new PackViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PackViewHolder holder, int position) {

        PackShow pack = packs.get(position);
        holder.HeaderTextView.setText("Package number "+(position+1));
        holder.PackStatusTextView.setText("Package Status: "+pack.getPackStatus().toString());
        holder.PackFragileTextView.setText("Package Fragile: "+pack.getPackFragile());
        holder.DeliveryNameTextView.setText("Delivery Name: "+pack.getDeliveryName());
        holder.PackTypeTextView.setText("Package Type: "+pack.getPackType().toString());
        holder.PackWeightTextView.setText("Package Weight: "+pack.getPackWeight().toString());
        holder.StorageLocationTextView.setText("Storage Location: "+pack.getAddress());

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

    class PackViewHolder extends RecyclerView.ViewHolder {

        TextView HeaderTextView;
        TextView PackTypeTextView;
        TextView PackWeightTextView;
        TextView PackStatusTextView;
        TextView PackFragileTextView;
        TextView DeliveryNameTextView;
        TextView StorageLocationTextView;


        PackViewHolder(View itemView) {
            super(itemView);
            HeaderTextView= itemView.findViewById(R.id.HeaderTextView);
            PackStatusTextView = itemView.findViewById(R.id.firstNameView);
            PackFragileTextView = itemView.findViewById(R.id.lastNameView);
            DeliveryNameTextView = itemView.findViewById(R.id.phoneView);
            StorageLocationTextView = itemView.findViewById(R.id.storageLocationView);
            PackTypeTextView = itemView.findViewById(R.id.packTypeView);
            PackWeightTextView = itemView.findViewById(R.id.packWeightView);

            // itemView.setOnClickListener();
            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.setHeaderTitle("Select Action");

                    MenuItem show = menu.add(Menu.NONE, 1, 1, "Show");

                    show.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            int position = getAdapterPosition();
                            String id = packs.get(position).getAKey();


                            return true;
                        }
                    });
                }
            });
        }
    }
}
