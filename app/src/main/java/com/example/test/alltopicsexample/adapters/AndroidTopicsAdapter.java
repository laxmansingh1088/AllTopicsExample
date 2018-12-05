package com.example.test.alltopicsexample.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.test.alltopicsexample.R;
import com.example.test.alltopicsexample.dagger.Activity.DaggerActivity;
import com.example.test.alltopicsexample.jobscheduler.JobSchedulerActivity;
import com.example.test.alltopicsexample.mvvm.MVVMActivity;
import com.example.test.alltopicsexample.spinbottle.SpinTheBottleActivity;
import com.example.test.alltopicsexample.tiktoe.TikToeGameActivity;

import java.util.List;

public class AndroidTopicsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> list;
    private Context mContext;
    private final int VIEW_TYPE_ITEM = 1;


    public AndroidTopicsAdapter(Context context, List<String> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topics_list_item, null, false);
            AndroidTopicsAdapter.TopicsViewHolder viewHolder = new AndroidTopicsAdapter.TopicsViewHolder(view);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof TopicsViewHolder) {
            ((TopicsViewHolder) viewHolder).mTvTitle.setText(list.get(position));
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

        private TextView mTvTitle;

        public TopicsViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (list.get(getAdapterPosition()).toString()) {
                case "Dagger":
                    mContext.startActivity(new Intent(mContext, DaggerActivity.class));
                    break;

                case "RxJava":
                    break;

                case "LiveData":
                    break;

                case "Room":
                    break;

                case "MVVM":
                    mContext.startActivity(new Intent(mContext, MVVMActivity.class));
                    break;

                case "Activity":
                    break;

                case "Fragments":
                    break;

                case "BroadCast Receivers":
                    break;

                case "Services":
                    break;


                case "JobScheduler":
                    mContext.startActivity(new Intent(mContext, JobSchedulerActivity.class));
                    break;

                case "Tic tac toe":
                    mContext.startActivity(new Intent(mContext, TikToeGameActivity.class));
                    break;

                case "Spin the Bottle":
                    mContext.startActivity(new Intent(mContext, SpinTheBottleActivity.class));
                    break;

                default:
                    break;
            }
        }
    }


}
