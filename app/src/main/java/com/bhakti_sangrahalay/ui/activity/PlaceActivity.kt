package com.bhakti_sangrahalay.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.PlaceListAdapter
import com.bhakti_sangrahalay.model.PlaceModel
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.viewmodel.PlaceViewModel
import java.util.*
import kotlin.collections.ArrayList


class PlaceActivity : BaseActivity(), TextWatcher {
    lateinit var viewModel: PlaceViewModel
    lateinit var preferences: SharedPreferences
    private lateinit var placeList: ArrayList<PlaceModel>



    private lateinit var searchET: EditText
    private lateinit var placeRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        attachViewModel()
        preferences=getSharedPreferences("Application",MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.place_search)
        initView()
        setListener()
    }

     override fun attachViewModel() {
        val viewModelProvider = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
        viewModel = viewModelProvider[PlaceViewModel::class.java]
        placeList = viewModel.getPlaceList(resources)
    }

    override fun setTypeface() {
        TODO("Not yet implemented")
    }

    fun initView() {
        searchET = findViewById(R.id.search_et)
        placeRV = findViewById(R.id.place_rv)
    }

    private fun setListener() {
        searchET.addTextChangedListener(this)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        setPlaceRVData(searchPlace(p0.toString()))
    }

    private fun searchPlace(searchStr: String): ArrayList<PlaceModel> {
        val places = ArrayList<PlaceModel>()
        for (i in 0..placeList.size - 1) {

            if (placeList[i].city.toLowerCase(Locale.ROOT).contains(searchStr.toLowerCase(Locale.ROOT))) {
                Log.i("", "")
                places.add(placeList[i])
            }
        }
        return places
    }

    private fun setPlaceRVData(placeList: ArrayList<PlaceModel>) {
        val placeListAdapter = PlaceListAdapter(this, placeList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        placeRV.layoutManager = mLayoutManager
        placeRV.itemAnimator = DefaultItemAnimator()
        placeRV.adapter = placeListAdapter
    }
}