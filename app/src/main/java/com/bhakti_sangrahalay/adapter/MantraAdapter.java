package com.bhakti_sangrahalay.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.model.MantraBean;

import java.util.ArrayList;

public class MantraAdapter extends RecyclerView.Adapter<MantraAdapter.MyView> {

    private ArrayList<MantraBean> arrayList;

    Context context;
    Resources resources;
    ArrayList<Integer> drawableList;

    public class MyView extends RecyclerView.ViewHolder {

        TextView titleTV;
        TextView contentTV;
        CardView cardView;
        ImageView imageView;


        public MyView(View view) {
            super(view);
            titleTV = view.findViewById(R.id.title_tv);
            contentTV = view.findViewById(R.id.content_tv);
            cardView = view.findViewById(R.id.cardview);
            imageView = view.findViewById(R.id.imageView);
           // titleTV.setTypeface(BaseActivity.boldTypeface);
           // contentTV.setTypeface(BaseActivity.mediumTypeface);

        }
    }

    public MantraAdapter(Context context, ArrayList<MantraBean> arrayList, ArrayList<Integer> drawableList) {
        this.context = context;
        resources = context.getResources();
        this.arrayList = arrayList;
        this.drawableList = drawableList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mantra_list_item, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        holder.titleTV.setText(arrayList.get(position).getTitle());
        holder.contentTV.setText(arrayList.get(position).getContent());
        holder.imageView.setImageDrawable(resources.getDrawable(drawableList.get(position)));
        //holder.cardView.setCardBackgroundColor(context.getResources().getColor(colorList.get(position)));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}