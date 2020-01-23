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
import com.example.my_second_app.entities.Pack;

import java.util.List;

public class packRecycleViewAdapter extends RecyclerView.Adapter<packRecycleViewAdapter.PackViewHolder> {
    List<Pack> packs;



    @Override
    public PackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext() ).inflate(R.layout.activity_item_view,
                parent,
                false);

        return new PackViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PackViewHolder holder, int position) {

        Pack pack = packs.get(position);
        holder.HeaderTextView.setText("Package number "+(position+1));
        holder.FirstNameTextView.setText("Recipient First Name: "+pack.getRecipient().getFirstName());
        holder.LastNameTextView.setText("Recipient Last Name: "+pack.getRecipient().getLastName());
        holder.PhoneTextView.setText("Recipient Phone Number: "+pack.getRecipient().getPhoneNumber());
        holder.PackTypeTextView.setText("Package Type: "+pack.getPackType().toString());
        holder.PackWeightTextView.setText("Package Weight: "+pack.getPackWeight().toString());
        holder.StorageLocationTextView.setText("Storage Location: "+pack.getStorageLocation().getMAddress());

    }

    @Override
    public int getItemCount() {
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

    class PackViewHolder extends RecyclerView.ViewHolder {

        TextView HeaderTextView;
        TextView FirstNameTextView;
        TextView LastNameTextView;
        TextView PhoneTextView;
        TextView PackTypeTextView;
        TextView PackWeightTextView;
        TextView StorageLocationTextView;


        PackViewHolder(View itemView) {
            super(itemView);
            HeaderTextView= itemView.findViewById(R.id.HeaderTextView);
            FirstNameTextView = itemView.findViewById(R.id.firstNameView);
            LastNameTextView = itemView.findViewById(R.id.lastNameView);
            PhoneTextView = itemView.findViewById(R.id.phoneView);
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
