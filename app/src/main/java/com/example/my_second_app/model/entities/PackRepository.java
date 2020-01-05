package com.example.my_second_app.model.entities;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PackRepository {
    private PackDao PackDao;
   // private DatabaseReference packsRef;

    private LiveData<List<Pack>> allPacks;

    public PackRepository(Application application) {
        PackDatabase database = PackDatabase.getInstance(application);
        // Write a message to the database
       // FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //packsRef = firebaseDatabase.getReference("packs");

        PackDao = database.PackDao();

        allPacks = PackDao.getAllNotes();
    }

    public void insert(Pack pack) {
        new InsertNoteAsyncTask(PackDao).execute(Pack);
//        notesRef.push().setValue(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(PackDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(PackDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(PackDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    //region AsyncTask implementation

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Note> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Note doInBackground(Note... notes) {
            long add_id = noteDao.insert(notes[0]);
            notes[0].setId((int)add_id);
            return notes[0];
        }
        @Override
        protected void onPostExecute(Note note){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference notesRef = firebaseDatabase.getReference("notes");;
            notesRef.push().setValue(note);
        }

    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
