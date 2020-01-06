package com.example.my_second_app.model.entities;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PackRepository {
    private PackDao packDao;
   // private DatabaseReference packsRef;

    private LiveData<List<Pack>> allPacks;

    public PackRepository(Application application) {
        PackDatabase database = PackDatabase.getInstance(application);
        // Write a message to the database
       // FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //packsRef = firebaseDatabase.getReference("packs");

        PackDao = database.PackDao();

        allPacks = PackDao.getAllPacks();
    }

    public void insert(Pack pack) {
        new InsertNoteAsyncTask(PackDao).execute(Pack);
//        notesRef.push().setValue(note);
    }

    public void update(Pack pack) {
        new UpdateNoteAsyncTask(PackDao).execute(pack);
    }

    public void delete(Pack pack) {
        new DeletePackAsyncTask(PackDao).execute(pack);
    }

    public void deleteAllPacks() {
        new DeleteAllPackAsyncTask(PackDao).execute();
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
            packs[0].setId((int)add_id);
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
            this.packsDao = packsDao;
        }

        @Override
        protected Void doInBackground(Pack... packs) {
            noteDao.delete(packs[0]);
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
            PackDao.deleteAllPacks();
            return null;
        }
    }
}
