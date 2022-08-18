package com.bhakti_sangrahalay.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.databinding.ActMatchMakingResultLayBinding
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.ui.fragment.MatchMakingResultFragment
import com.bhakti_sangrahalay.viewmodel.MatchMakingResultActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MatchMakingResultActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MatchMakingResultActivityViewModel
    lateinit var binding: ActMatchMakingResultLayBinding
    lateinit var boyDetail: BirthDetailBean
    lateinit var girlDetail: BirthDetailBean

    override fun attachViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[MatchMakingResultActivityViewModel::class.java]
        viewModel.getMatchMakingResult(boyDetail, girlDetail)
    }

    override fun setTypeface() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initFont()
        getIntentData()
        attachViewModel()
        binding = ActMatchMakingResultLayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addFragment(MatchMakingResultFragment.getInstance())
    }

    private fun getIntentData() {
        val bundle = intent.extras
        boyDetail = bundle?.getSerializable("boy_detail") as BirthDetailBean
        girlDetail = bundle.getSerializable("girl_detail") as BirthDetailBean
    }

    private fun addFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        tr.add(R.id.fragment_container_view, fragment)
        tr.commitAllowingStateLoss()
    }
}