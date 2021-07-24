package com.devidea.grigoapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class CustomRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private final ArrayList<PostListDTO> arrayList;
    private static OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public CustomRecyclerView(ArrayList<PostListDTO> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getItemViewType(int position) {
        if(position <= getItemCount()){
            return VIEW_TYPE_ITEM;
        }
        else {
            return VIEW_TYPE_LOADING;
        }
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_post_list, parent, false);

            return new PostListViewHolder(view);

        } else {

            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_post_list, parent, false);

            return new ProgressHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(getItemCount()>=position){
            PostListViewHolder listViewHolder = (PostListViewHolder) holder;

            listViewHolder.title.setText(arrayList.get(position).getTitle());
            listViewHolder.content.setText(arrayList.get(position).getContent());
            listViewHolder.teg.setText((CharSequence) arrayList.get(position).getTag());
            listViewHolder.writer.setText(arrayList.get(position).getWriter());
            listViewHolder.time.setText(arrayList.get(position).getTimeStamp());
        }

        else{
            ProgressHolder progressHolder = (ProgressHolder) holder;
        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class PostListViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView content;
        private final TextView teg;
        private final TextView writer;
        private final TextView time;

        public PostListViewHolder(@NonNull View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getBindingAdapterPosition();
                    //Log.d("position", String.valueOf(pos));

                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });


            title = (TextView) itemView.findViewById(R.id.post_title);
            content = (TextView) itemView.findViewById(R.id.content);
            teg = (TextView) itemView.findViewById(R.id.teg);
            writer = (TextView) itemView.findViewById(R.id.writer);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }

    class ProgressHolder extends RecyclerView.ViewHolder {

        public ProgressBar progressBar;

        public ProgressHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }


}