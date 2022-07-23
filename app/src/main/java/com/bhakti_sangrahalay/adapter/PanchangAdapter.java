package com.bhakti_sangrahalay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.panchang.model.PanchangBean;

import java.util.List;


public class PanchangAdapter extends RecyclerView.Adapter<PanchangAdapter.MyViewHolder> {

    private List<PanchangBean> moviesList;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, year, genre;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            genre = view.findViewById(R.id.genre);
            year = view.findViewById(R.id.year);
        }
    }


    public PanchangAdapter(List<PanchangBean> moviesList) {
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.panchang_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PanchangBean panchangBean = moviesList.get(position);
        holder.title.setText(panchangBean.getTitle());
        holder.genre.setText(panchangBean.getGenre());
        holder.year.setText(panchangBean.getYear());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}