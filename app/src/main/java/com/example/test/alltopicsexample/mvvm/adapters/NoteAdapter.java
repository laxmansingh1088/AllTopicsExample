package com.example.test.alltopicsexample.mvvm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.alltopicsexample.R;
import com.example.test.alltopicsexample.mvvm.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Note> list = new ArrayList<Note>();
    private Context mContext;
    private final int VIEW_TYPE_ITEM = 1;


    public NoteAdapter(Context context) {
        mContext = context;
    }


    public void setList(List<Note> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return list.get(position);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_list_item, null, false);
            NoteAdapter.TopicsViewHolder viewHolder = new NoteAdapter.TopicsViewHolder(view);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof TopicsViewHolder) {
            Note note = list.get(position);
            ((TopicsViewHolder) viewHolder).mTvTitle.setText(note.getTitle());
            ((TopicsViewHolder) viewHolder).tv_title_description.setText(note.getDescription());
            ((TopicsViewHolder) viewHolder).tv_title_priority.setText(note.getPriority() + "");

        }

    }


    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private class TopicsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTvTitle, tv_title_description, tv_title_priority;

        public TopicsViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title_note);
            tv_title_description = (TextView) itemView.findViewById(R.id.tv_title_description);
            tv_title_priority = (TextView) itemView.findViewById(R.id.tv_title_priority);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                default:
                    break;
            }
        }
    }


}
