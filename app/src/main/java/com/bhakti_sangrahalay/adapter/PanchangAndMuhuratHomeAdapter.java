package com.bhakti_sangrahalay.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.ui.activity.BaseActivity;
import com.bhakti_sangrahalay.ui.activity.PanchangActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PanchangAndMuhuratHomeAdapter extends RecyclerView.Adapter<PanchangAndMuhuratHomeAdapter.MyView> {

    private List<String> nameList;
    private List<Integer> imageList;

    private Context context;
    private Resources resources;

    public class MyView extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        ImageView imageView;

        public MyView(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardview);
            textView = view.findViewById(R.id.textview);
            imageView = view.findViewById(R.id.image_view);
            //textView.setTypeface(BaseActivity.mediumTypeface);
            cardView.setOnClickListener(v -> {

                Bundle bundle = new Bundle();
                Intent intent = new Intent(context, PanchangActivity.class);
                bundle.putInt("index", getLayoutPosition());
                intent.putExtras(bundle);
                context.startActivity(intent);
            });
        }
    }

    public PanchangAndMuhuratHomeAdapter(Context context, List<String> nameList, List<Integer> imageList) {
        this.context = context;
        resources = context.getResources();
        this.nameList = nameList;
        this.imageList = imageList;
    }

    @NotNull
    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.panchang_muhurat_layout, parent, false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        holder.textView.setText(nameList.get(position));
        holder.imageView.setImageDrawable(resources.getDrawable(imageList.get(position)));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}

