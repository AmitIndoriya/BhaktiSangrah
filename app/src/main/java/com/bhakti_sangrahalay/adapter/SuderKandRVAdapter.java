package com.bhakti_sangrahalay.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.model.SunderKaandBean;

import java.util.List;

public class SuderKandRVAdapter extends RecyclerView.Adapter<SuderKandRVAdapter.MyView> {

    List<SunderKaandBean.SunderKandArray.Choupai> choupaiList;

    Context context;
    Resources resources;

    public class MyView extends RecyclerView.ViewHolder {

        TextView contentTV;
        TextView contentMeaningTV;

        public MyView(View view) {
            super(view);
            contentTV = view.findViewById(R.id.content_tv);
            contentMeaningTV = view.findViewById(R.id.content_meaning_tv);
            //contentTV.setTypeface(BaseActivity.regularTypeface);
           // contentMeaningTV.setTypeface(BaseActivity.regularTypeface);
        }
    }

    public SuderKandRVAdapter(Context context, List<SunderKaandBean.SunderKandArray.Choupai> choupaiList) {
        this.context = context;
        resources = context.getResources();
        this.choupaiList = choupaiList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sunder_kand_list_item, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        SunderKaandBean.SunderKandArray.Choupai choupai = choupaiList.get(position);
        holder.contentTV.setText(choupai.getChopai());
        holder.contentMeaningTV.setText(choupai.getMeaning());
    }

    @Override
    public int getItemCount() {
        return choupaiList.size();
    }
}
