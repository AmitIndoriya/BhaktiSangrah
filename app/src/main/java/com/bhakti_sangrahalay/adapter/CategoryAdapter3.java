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
import com.bhakti_sangrahalay.ui.activity.MantraActivity;
import com.bhakti_sangrahalay.ui.activity.PauranikKathaHomeActivity;
import com.bhakti_sangrahalay.ui.activity.VartKathaHomeActivity;

import java.util.List;

public class CategoryAdapter3 extends RecyclerView.Adapter<CategoryAdapter3.MyView> {

    private List<String> nameList;
    private List<Integer> drawableList;
    private List<Integer> rowFileList;
    Resources resources;
    Context context;
    int type;

    public class MyView extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        CardView cardView;

        public MyView(View view) {
            super(view);
            textView = view.findViewById(R.id.textview);
            imageView = view.findViewById(R.id.image_view);
            cardView = view.findViewById(R.id.cardview);
            //textView.setTypeface(BaseActivity.mediumTypeface);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getLayoutPosition() == 0) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(context, VartKathaHomeActivity.class);
                        bundle.putInt("type", getLayoutPosition());
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    } else if (getLayoutPosition() == 1) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(context, PauranikKathaHomeActivity.class);
                        bundle.putInt("type", getLayoutPosition());
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    } else if (getLayoutPosition() == 2) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(context, MantraActivity.class);
                        bundle.putInt("type", getLayoutPosition());
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }

                }
            });
        }
    }


    public CategoryAdapter3(Context context, List<String> nameList, List<Integer> drawableList, List<Integer> rowFileList, int type) {
        this.context = context;
        resources = context.getResources();
        this.nameList = nameList;
        this.drawableList = drawableList;
        this.rowFileList = rowFileList;
        this.type = type;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_dashboard_cat_item2, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(MyView holder, final int position) {
        holder.textView.setText(nameList.get(position));
        holder.imageView.setImageDrawable(resources.getDrawable(drawableList.get(position)));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}
