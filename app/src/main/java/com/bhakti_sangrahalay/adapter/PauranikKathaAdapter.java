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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.ui.activity.BaseActivity;
import com.bhakti_sangrahalay.ui.activity.KathaActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PauranikKathaAdapter extends RecyclerView.Adapter<PauranikKathaAdapter.MyView> {

    private List<String> nameList;
    private List<Integer> imageList;
    private List<Integer> rowFileList;

    private Context context;
    private Resources resources;

    class MyView extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        ImageView imageView;

        private  MyView(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardview);
            textView = view.findViewById(R.id.textview);
            imageView = view.findViewById(R.id.imageView);
            //textView.setTypeface(BaseActivity.mediumTypeface);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("resId", rowFileList.get(getLayoutPosition()));
                    bundle.putString("title", nameList.get(getLayoutPosition()));
                    Intent intent = new Intent(context, KathaActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        }
    }

    public PauranikKathaAdapter(Context context, List<String> nameList, List<Integer> imageList, List<Integer> rowFileList) {
        this.context = context;
        resources = context.getResources();
        this.nameList = nameList;
        this.imageList = imageList;
        this.rowFileList = rowFileList;
    }

    @NotNull
    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pauranik_list_item, parent, false);
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) itemView.getLayoutParams();
        params.height = (parent.getMeasuredWidth() / 2);
        itemView.setLayoutParams(params);
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
