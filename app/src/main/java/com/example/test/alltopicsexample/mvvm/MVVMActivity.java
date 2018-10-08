package com.example.test.alltopicsexample.mvvm;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.test.alltopicsexample.R;
import com.example.test.alltopicsexample.mvvm.adapters.NoteAdapter;
import com.example.test.alltopicsexample.mvvm.model.Note;
import com.example.test.alltopicsexample.mvvm.viewmodel.NoteViewModel;
import com.example.test.alltopicsexample.utils.BaseActivity;

import java.util.List;

public class MVVMActivity extends BaseActivity {
    public static final int ADD_NOTE_REQUEST_CODE = 1009;

    private Context mContext;
    private RecyclerView mRvNotes;
    private NoteViewModel mNoteViewModel;
    private NoteAdapter mNoteAdapter;
    private FloatingActionButton fab_add_note;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvvm_demo_activity);
        mContext = this;
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        setTitle("MVVM");
        onFindView();
        onInView();
        onBindView();

        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mNoteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                mNoteAdapter.setList(notes);
            }
        });
    }

    @Override
    protected void onFindView() {
        mRvNotes = findViewById(R.id.rv_notes);
        fab_add_note = findViewById(R.id.fab_add_note);
    }

    @Override
    protected void onInView() {
        mRvNotes.setLayoutManager(new LinearLayoutManager(this));
        mNoteAdapter = new NoteAdapter(this);
        mRvNotes.setAdapter(mNoteAdapter);
    }

    @Override
    protected void onBindView() {

        fab_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MVVMActivity.this, AddNoteActivity.class), ADD_NOTE_REQUEST_CODE);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                mNoteViewModel.deleteNote(mNoteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(mContext, "Note Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mRvNotes);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_all_notes, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                mNoteViewModel.deleteAllNotes();
                return true;

            default:
               return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);

            Note note = new Note(title, description, priority);
            mNoteViewModel.insertNote(note);
            Toast.makeText(mContext, "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
