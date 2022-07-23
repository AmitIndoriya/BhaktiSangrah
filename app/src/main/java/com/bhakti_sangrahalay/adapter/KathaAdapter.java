package com.bhakti_sangrahalay.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.model.KathaBean;
import com.bhakti_sangrahalay.ui.activity.KathaDescActivity;

import java.util.ArrayList;

public class KathaAdapter extends RecyclerView.Adapter<KathaAdapter.MyView> {

    private ArrayList<KathaBean> kathaList;


    Context context;
    Resources resources;

    public class MyView extends RecyclerView.ViewHolder {
        TextView textView;

        public MyView(View view) {
            super(view);
            textView = view.findViewById(R.id.textview);
            //textView.setTypeface((BaseActivity.mediumTypeface));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Intent startIntent = new Intent(context, KathaDescActivity.class);
                    bundle.putSerializable("katha", kathaList.get(getLayoutPosition()));
                    startIntent.putExtras(bundle);
                    context.startActivity(startIntent);
                }
            });
        }
    }

    public KathaAdapter(Context context, ArrayList<KathaBean> kathaList) {
        this.context = context;
        resources = context.getResources();
        this.kathaList = kathaList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.katha_list_item_layout, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        holder.textView.setText(kathaList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return kathaList.size();
    }

}
