package com.example.my_second_app.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.my_second_app.entities.Pack;
import com.example.my_second_app.entities.PackShow;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PackRepository {
    private PackDao packDao;
    private DatabaseReference packsRef;

    private LiveData<List<PackShow>> allPacks;

    public PackRepository(Application application) {
        PackDatabase database = PackDatabase.getInstance(application);
        // Write a message to the database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        packsRef = firebaseDatabase.getReference("packs");

        packDao = database.packDao();

        allPacks = packDao.getAllPacksShow();
    }

    public void getHistoryParcels(){
        packsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // read from firebase
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Pack pack = snapshot.getValue(Pack.class);
                        PackShow packShow = new PackShow(pack.getPackType(),
                                                            pack.getPackWeight(),
                                                            pack.isPackFragile(),
                                                            pack.getPackStatus(),
                                                            pack.getDeliveryName(),
                                                            pack.getStorageLocation().getMAddress(),
                                                            pack.getAKey());
                        insert(packShow);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void insert(PackShow pack) {
        new InsertPackAsyncTask(packDao).execute(pack);
    }

    public void update(PackShow pack) {
        new UpdatePackAsyncTask(packDao).execute(pack);
    }

    public void delete(PackShow pack) {
        new DeletePackAsyncTask(packDao).execute(pack);
        packsRef.child(pack.getAKey()).removeValue();

    }

    public void deleteAllPacks() {
        new DeleteAllPacksAsyncTask(packDao).execute();
    }

    public LiveData<List<PackShow>> getAllPacks() {
        return allPacks;
    }

    //region AsyncTask implementation

    private static class InsertPackAsyncTask extends AsyncTask<PackShow, Void, Void> {
        private PackDao packDao;

        private InsertPackAsyncTask(PackDao packDao) {
            this.packDao = packDao;
        }

        @Override
        protected Void doInBackground(PackShow... packs) {
            for(PackShow pack:packs){
                packDao.insert(pack);
            }
                return null;
        }

    }

    private static class UpdatePackAsyncTask extends AsyncTask<PackShow, Void, Void> {
        private PackDao packDao;

        private UpdatePackAsyncTask(PackDao packDao) {
            this.packDao = packDao;
        }

        @Override
        protected Void doInBackground(PackShow... packs) {
            packDao.update(packs[0]);
            return null;
        }
    }

    private static class DeletePackAsyncTask extends AsyncTask<PackShow, Void, Void> {
        private PackDao packDao;

        private DeletePackAsyncTask(PackDao packsDao) {
            this.packDao = packsDao;
        }

        @Override
        protected Void doInBackground(PackShow... packs) {
            packDao.delete(packs[0]);

            return null;
        }
    }

    private static class DeleteAllPacksAsyncTask extends AsyncTask<Void, Void, Void> {
        private PackDao packDao;

        private DeleteAllPacksAsyncTask(PackDao packDao) {
            this.packDao = packDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            packDao.deleteAllPacksShow();
            return null;
        }
    }
}
