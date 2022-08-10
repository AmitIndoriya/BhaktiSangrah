package com.bhakti_sangrahalay.ui.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.activity.AboutUsActivity
import com.bhakti_sangrahalay.activity.SuggestionActivity
import com.bhakti_sangrahalay.adapter.HomeGridRVAdapter
import com.bhakti_sangrahalay.databinding.ActDashboardLayoutBinding
import com.bhakti_sangrahalay.util.Utility
import com.bhakti_sangrahalay.viewmodel.DashBoardViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class DashBoardActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: DashBoardViewModel
    private lateinit var binding: ActDashboardLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initFont()
        binding = ActDashboardLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbarSetUp()
        setHomeGridItems()
    }

    private fun toolbarSetUp() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white, null))
        setUpNavigationView()
        supportActionBar?.title = resources.getString(R.string.Dash_board)
    }

    override fun attachViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[DashBoardViewModel::class.java]
    }

    override fun setTypeface() {
    }

    private fun setHomeGridItems() {
        val nameArray = resources.getStringArray(R.array.dashboard_module_list)
        val iconArray = Utility.getIntArray(resources, R.array.dashboard_module_icon_list)
        val drawableBgArray = Utility.getIntArray(resources, R.array.dashboard_module_bg_list)
        binding.gridRv.layoutManager = GridLayoutManager(this, 3)
        val adapter = HomeGridRVAdapter(this, nameArray, drawableBgArray, iconArray)
        binding.gridRv.adapter = adapter
    }

    private fun setUpNavigationView() {
        binding.navView.setNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.nav_share_app -> {
                    shareApp()
                    binding.drawerLayout.closeDrawer(GravityCompat.START, false)
                }
                R.id.nav_rate_app -> {
                    rateApp()
                    binding.drawerLayout.closeDrawer(GravityCompat.START, false)
                }
                R.id.nav_suggestion -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.START, false)
                    startActivity(Intent(this, SuggestionActivity::class.java))
                }
                R.id.nav_about_us -> {
                    startActivity(Intent(this, AboutUsActivity::class.java))
                    binding.drawerLayout.closeDrawer(GravityCompat.START, false)
                }
            }
            menuItem.isChecked = !menuItem.isChecked
            menuItem.isChecked = true
            true
        }
        val actionBarDrawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.openDrawer,
            R.string.closeDrawer
        ) {
            override fun onDrawerClosed(drawerView: View) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView)
            }
        }

        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.drawerArrowDrawable.color = resources.getColor(R.color.white, null)
        actionBarDrawerToggle.syncState()
    }

    private fun shareApp() {
        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.type = "text/plain"
        whatsappIntent.setPackage("com.whatsapp")
        val shareText =
            "DownLoad Bhakti-Sangrahalay App: " + "https://play.google.com/store/apps/details?id=com.bhakti_sangrahalay"
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, shareText)
        try {
            startActivity(whatsappIntent)
        } catch (ex: ActivityNotFoundException) {
            //ToastHelper.MakeShortText("Whatsapp have not been installed.");
        }
    }

    private fun rateApp() {
        try {
            val rateIntent: Intent = rateIntentForUrl("market://details")
            startActivity(rateIntent)
        } catch (e: ActivityNotFoundException) {
            val rateIntent: Intent = rateIntentForUrl("https://play.google.com/store/apps/details")
            startActivity(rateIntent)
        }
    }

    private fun rateIntentForUrl(url: String): Intent {
        val intent = Intent(
            Intent.ACTION_VIEW, Uri.parse(
                java.lang.String.format(
                    "%s?id=%s", url,
                    packageName
                )
            )
        )
        var flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        flags = if (Build.VERSION.SDK_INT >= 21) {
            flags or Intent.FLAG_ACTIVITY_NEW_DOCUMENT
        } else {
            flags or Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET
        }
        intent.addFlags(flags)
        return intent
    }
}