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
import com.bhakti_sangrahalay.activity.AartiDescActivityNew;
import com.bhakti_sangrahalay.contansts.GlobalVariables;
import com.bhakti_sangrahalay.ui.activity.MoreItemActivity;

import java.util.List;

public class CategoryAdapter1 extends RecyclerView.Adapter<CategoryAdapter1.MyView> {

    private List<String> nameList;
    private List<Integer> imageList;
    private List<Integer> rowFileList;
    private int type;

    Context context;
    Resources resources;

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
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rowFileList.get(getLayoutPosition()) == GlobalVariables.OTHERS) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(context, MoreItemActivity.class);
                        bundle.putInt("type", type);
                        bundle.putString("title", resources.getString(R.string.aartiyan));
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    } else {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(context, AartiDescActivityNew.class);
                        bundle.putInt("imageId", imageList.get(getLayoutPosition()));
                        bundle.putInt("fileId", rowFileList.get(getLayoutPosition()));
                        bundle.putString("title", resources.getString(R.string.aarti));
                        bundle.putInt("fragNum", getLayoutPosition());

                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }

                }
            });
        }
    }

    public CategoryAdapter1(Context context, List<String> nameList, List<Integer> imageList, List<Integer> rowFileList, int type) {
        this.context = context;
        resources = context.getResources();
        this.nameList = nameList;
        this.imageList = imageList;
        this.rowFileList = rowFileList;
        this.type = type;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_dashboard_cat_item1, parent, false);

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
