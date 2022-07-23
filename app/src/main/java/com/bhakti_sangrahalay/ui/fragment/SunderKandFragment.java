package com.bhakti_sangrahalay.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.adapter.SuderKandRVAdapter;
import com.bhakti_sangrahalay.adapter.SuderKandRVAdapter1;
import com.bhakti_sangrahalay.adapter.SuderKandRVAdapter2;
import com.bhakti_sangrahalay.model.SunderKaandBean;
import com.bhakti_sangrahalay.ui.activity.BaseActivity;

public class SunderKandFragment extends Fragment {
    SunderKaandBean.SunderKandArray sunderKandArray;
    TextView titleTV;

    TextView chhandHeadingTV;
    TextView chopaiHeadingTV;
    TextView dohaHeadingTV;
    //TextView dohaContentTV;
    //TextView dohaContentMeaningTV;
    TextView shorthaHeadingTV;
    TextView shorthaContentTV;
    TextView shorthaContentMeaningTV;
    LinearLayout chopaiLL;
    LinearLayout chhandLL;
    LinearLayout shorthaLL;
    LinearLayout dohaLL;


    public static SunderKandFragment newInstance(SunderKaandBean.SunderKandArray sunderKandArray) {

        Bundle args = new Bundle();
        args.putSerializable("data_list", sunderKandArray);
        SunderKandFragment fragment = new SunderKandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            sunderKandArray = (SunderKaandBean.SunderKandArray) bundle.getSerializable("data_list");

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.sunder_kand_fragment_layout, container, false);
        RecyclerView chopaiRecyclerView = root.findViewById(R.id.chopai_rv);
        RecyclerView chhandRecyclerView = root.findViewById(R.id.chhand_rv);
        RecyclerView dohaRecyclerView = root.findViewById(R.id.doha_rv);
        titleTV = root.findViewById(R.id.title);
        chopaiLL = root.findViewById(R.id.chopai_container_ll);
        chhandLL = root.findViewById(R.id.chhand_container_ll);
        shorthaLL = root.findViewById(R.id.shortha_container_ll);
        dohaLL = root.findViewById(R.id.doha_container_ll);
        //dohaContentTV = root.findViewById(R.id.doha_content_tv);
        //dohaContentMeaningTV = root.findViewById(R.id.doha_content_meaning_tv);
        chhandHeadingTV = root.findViewById(R.id.chhand_heading_tv);
        chopaiHeadingTV = root.findViewById(R.id.chopai_heading_tv);
        dohaHeadingTV = root.findViewById(R.id.doha_heading_tv);
        shorthaHeadingTV = root.findViewById(R.id.shortha_heading_tv);
        shorthaContentTV = root.findViewById(R.id.shortha_content_tv);
        shorthaContentMeaningTV = root.findViewById(R.id.shortha_content_meaning_tv);
        ImageView imageView = root.findViewById(R.id.image_view);
        imageView.setImageDrawable(sunderKandArray.getDrawable());

        titleTV.setText(sunderKandArray.getTitle());
        if (sunderKandArray.getChoupaiArrayList().size() > 0) {
            chopaiLL.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            chopaiRecyclerView.setLayoutManager(linearLayoutManager);
            SuderKandRVAdapter suderKandRVAdapter = new SuderKandRVAdapter(getActivity(), sunderKandArray.getChoupaiArrayList());
            chopaiRecyclerView.setAdapter(suderKandRVAdapter);
        }
        if (sunderKandArray.getChhandArrayList().size() > 0) {
            chhandLL.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            chhandRecyclerView.setLayoutManager(linearLayoutManager);
            SuderKandRVAdapter1 suderKandRVAdapter = new SuderKandRVAdapter1(getActivity(), sunderKandArray.getChhandArrayList());
            chhandRecyclerView.setAdapter(suderKandRVAdapter);
        }
        if (sunderKandArray.getShorthaArrayList().size() > 0) {
            shorthaLL.setVisibility(View.VISIBLE);
            shorthaContentTV.setText(sunderKandArray.getShorthaArrayList().get(0).getShortha());
            shorthaContentMeaningTV.setText(sunderKandArray.getShorthaArrayList().get(0).getMeaning());
        }
        if (sunderKandArray.getDohaArrayList().size() > 0) {
            dohaLL.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            dohaRecyclerView.setLayoutManager(linearLayoutManager);
            SuderKandRVAdapter2 suderKandRVAdapter = new SuderKandRVAdapter2(getActivity(), sunderKandArray.getDohaArrayList());
            dohaRecyclerView.setAdapter(suderKandRVAdapter);
            //dohaContentTV.setText(sunderKandArray.getDohaArrayList().get(0).getDoha());
            //dohaContentMeaningTV.setText(sunderKandArray.getDohaArrayList().get(0).getMeaning());
        }


        setTypeface();
        return root;
    }

    private void setTypeface() {
        /*titleTV.setTypeface(BaseActivity.boldTypeface);
        //dohaContentTV.setTypeface(BaseActivity.regularTypeface);
        //dohaContentMeaningTV.setTypeface(BaseActivity.regularTypeface);
        chhandHeadingTV.setTypeface(BaseActivity.semiBoldTypeface);
        chopaiHeadingTV.setTypeface(BaseActivity.semiBoldTypeface);
        dohaHeadingTV.setTypeface(BaseActivity.semiBoldTypeface);
        shorthaHeadingTV.setTypeface(BaseActivity.semiBoldTypeface);
        shorthaContentTV.setTypeface(BaseActivity.regularTypeface);
        shorthaContentMeaningTV.setTypeface(BaseActivity.regularTypeface);*/
    }
}
