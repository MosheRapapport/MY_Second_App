package com.example.my_second_app.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.my_second_app.entities.Pack;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PackRepository {
    private PackDao packDao;
    private DatabaseReference packsRef;

    private LiveData<List<Pack>> allPacks;

    public PackRepository(Application application) {
        PackDatabase database = PackDatabase.getInstance(application);
        // Write a message to the database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        packsRef = firebaseDatabase.getReference("packsllist");

        packDao = database.packDao();

        allPacks = packDao.getAllPacks();
    }

    public void insert(Pack pack) {
        new InsertPackAsyncTask(packDao).execute(pack);
        packsRef.push().setValue(pack);
    }

    public void update(Pack pack) {
        new UpdatePackAsyncTask(packDao).execute(pack);
    }

    public void delete(Pack pack) {
        new DeletePackAsyncTask(packDao).execute(pack);
    }

    public void deleteAllPacks() {
        new DeleteAllPacksAsyncTask(packDao).execute();
    }

    public LiveData<List<Pack>> getAllPacks() {
        return allPacks;
    }

    //region AsyncTask implementation

    private static class InsertPackAsyncTask extends AsyncTask<Pack, Void, Pack> {
        private PackDao packDao;

        private InsertPackAsyncTask(PackDao packDao) {
            this.packDao = packDao;
        }

        @Override
        protected Pack doInBackground(Pack... packs) {
            long add_id = packDao.insert(packs[0]);
            packs[0].setIKey((int)add_id);
            return packs[0];
        }
        @Override
        protected void onPostExecute(Pack pack){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference packsRef = firebaseDatabase.getReference("packs");;
            packsRef.push().setValue(pack);
        }

    }

    private static class UpdatePackAsyncTask extends AsyncTask<Pack, Void, Void> {
        private PackDao packDao;

        private UpdatePackAsyncTask(PackDao packDao) {
            this.packDao = packDao;
        }

        @Override
        protected Void doInBackground(Pack... packs) {
            packDao.update(packs[0]);
            return null;
        }
    }

    private static class DeletePackAsyncTask extends AsyncTask<Pack, Void, Void> {
        private PackDao packDao;

        private DeletePackAsyncTask(PackDao packsDao) {
            this.packDao = packsDao;
        }

        @Override
        protected Void doInBackground(Pack... packs) {
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
            packDao.deleteAllPacks();
            return null;
        }
    }
}
