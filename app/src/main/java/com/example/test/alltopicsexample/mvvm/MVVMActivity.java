package com.example.test.alltopicsexample.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.test.alltopicsexample.R;
import com.example.test.alltopicsexample.mvvm.adapters.NoteAdapter;
import com.example.test.alltopicsexample.mvvm.model.Note;
import com.example.test.alltopicsexample.mvvm.viewmodel.NoteViewModel;
import com.example.test.alltopicsexample.utils.BaseActivity;

import java.util.List;

public class MVVMActivity extends BaseActivity {
    private RecyclerView mRvNotes;
    private NoteViewModel mNoteViewModel;
    private NoteAdapter mNoteAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("MVVM");
        setContentView(R.layout.mvvm_demo_activity);
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
    }

    @Override
    protected void onInView() {
        mRvNotes.setLayoutManager(new LinearLayoutManager(this));
        mNoteAdapter = new NoteAdapter(this);
        mRvNotes.setAdapter(mNoteAdapter);
    }

    @Override
    protected void onBindView() {

    }
}
