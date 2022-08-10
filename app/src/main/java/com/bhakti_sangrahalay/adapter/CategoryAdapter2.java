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
import com.bhakti_sangrahalay.ui.activity.ChalishaDescActivityNew;
import com.bhakti_sangrahalay.contansts.GlobalVariables;
import com.bhakti_sangrahalay.ui.activity.MoreItemActivity;

import java.util.List;

public class CategoryAdapter2 extends RecyclerView.Adapter<CategoryAdapter2.MyView> {

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
                    if (rowFileList.get(getLayoutPosition()) == GlobalVariables.OTHERS) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(context, MoreItemActivity.class);
                        bundle.putInt("type", type);
                        bundle.putString("title", resources.getString(R.string.chalisha));
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    } else {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(context, ChalishaDescActivityNew.class);
                        bundle.putInt("imageId", drawableList.get(getLayoutPosition()));
                        bundle.putInt("fileId", rowFileList.get(getLayoutPosition()));
                        bundle.putString("title", resources.getString(R.string.chalisha));
                        bundle.putInt("fragNum", getLayoutPosition());
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }

                }
            });
        }
    }


    public CategoryAdapter2(Context context, List<String> nameList, List<Integer> drawableList, List<Integer> rowFileList, int type) {
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
