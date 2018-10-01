package com.example.test.alltopicsexample.mvvm.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.test.alltopicsexample.mvvm.dao.NoteDao;
import com.example.test.alltopicsexample.mvvm.model.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;


    public abstract NoteDao noteDao();


    public static synchronized NoteDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
           new insertNotesAsync(instance).execute();
        }
    };


    private static class insertNotesAsync extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;

        public insertNotesAsync(NoteDatabase noteDatabase) {
            this.noteDao = noteDatabase.noteDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insertNote(new Note("1st", "first note", 1));
            noteDao.insertNote(new Note("2nd", "second note", 2));
            noteDao.insertNote(new Note("3rd", "third note", 3));
            noteDao.insertNote(new Note("4th", "fourth note", 4));
            noteDao.insertNote(new Note("5th", "fifth note", 5));
            return null;
        }
    }

}
