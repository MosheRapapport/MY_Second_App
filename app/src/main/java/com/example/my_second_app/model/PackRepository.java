package com.example.my_second_app.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.my_second_app.entities.Pack;
import com.example.my_second_app.entities.PackShow;
import com.example.my_second_app.entities.enums.PackStatus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PackRepository {
    private PackDao packDao;
    private DatabaseReference packsRef;

    private LiveData<List<PackShow>> allPacks;
    private List<Pack> allShippedPacks;
  //  private MutableLiveData<List<Pack>> allShippedPack;

    public PackRepository(Application application) {
        PackDatabase database = PackDatabase.getInstance(application);
        // Write a message to the database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        packsRef = firebaseDatabase.getReference("packs");

        packDao = database.packDao();

        allPacks = packDao.getAllPacksShow();
        allShippedPacks = new ArrayList<Pack>();
      //  allShippedPack = new MutableLiveData<>();
    }

    public void getHistoryParcels(){
        packsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // read from firebase
                if (dataSnapshot.exists()) {
                    deleteAllPacks();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Pack pack = snapshot.getValue(Pack.class);

                        //get freinds pack from firebase
                        if(pack.getPackStatus()== PackStatus.SHIPPED)
                        {
                            allShippedPacks.add(pack);
                        //    allShippedPack.setValue(allShippedPacks);
                        }
                        //get freinds pack from firebase


                        String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                        String email = pack.getRecipient().getEmail();
                        if (user.equals(email))
                        {
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        packsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Pack pack = dataSnapshot.getValue(Pack.class);

                //get freinds pack from firebase
                if(pack.getPackStatus()== PackStatus.SHIPPED)
                {
                    allShippedPacks.add(pack);
            //        allShippedPack.setValue(allShippedPacks);
                }
                //get freinds pack from firebase


                String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String email = pack.getRecipient().getEmail();
                if (user.equals(email))
                {
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

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Pack pack = dataSnapshot.getValue(Pack.class);

                //get freinds pack from firebase
                if(pack.getPackStatus()== PackStatus.SHIPPED)
                {
                    for (Pack p:allShippedPacks) {
                        if(p.getAKey()==pack.getAKey())
                        {
                            allShippedPacks.remove(p);
                            allShippedPacks.add(pack);
                        }
                    }
          //          allShippedPack.setValue(allShippedPacks);
                }
                //get freinds pack from firebase


                deletePack(pack.getAKey());
                String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String email = pack.getRecipient().getEmail();
                if (user.equals(email))
                {
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

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                deletePack(dataSnapshot.getValue(Pack.class).getAKey());

                //get freinds pack from firebase
                if(dataSnapshot.getValue(Pack.class).getPackStatus()==PackStatus.SHIPPED)
                {
                    allShippedPacks.remove(dataSnapshot.getValue(Pack.class));
         //           allShippedPack.setValue(allShippedPacks);
                }

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //get freinds pack from firebase
 //   public LiveData<List<Pack>> getAllShippedPacks(){
 //       return allShippedPack;
 //   }
    //get freinds pack from firebase


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

    public void deletePack(String AKey) {
        new DeletePackWithAkeyAsyncTask(packDao).execute(AKey);
    }

    public LiveData<List<PackShow>> getAllPacks() {
        return packDao.getAllPacksShow();
    }

    public LiveData<List<PackShow>> getAll_OFFER_TO_COLLECT_PacksShow() {
        return packDao.getAll_OFFER_TO_COLLECT_PacksShow();
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

    private static class DeletePackWithAkeyAsyncTask extends AsyncTask<String, Void, Void> {
        private PackDao packDao;

        private DeletePackWithAkeyAsyncTask(PackDao packDao) {
            this.packDao = packDao;
        }

        @Override
        protected Void doInBackground(String... Akey) {

            packDao.deletePacksShow(Akey[0]);
            return null;
        }
    }
}
