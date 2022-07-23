package com.astrologerchat.di.builders.ActivityBuilder

import com.bhakti_sangrahalay.ui.activity.BirthDetaiInputActivity
import com.bhakti_sangrahalay.ui.activity.DashBoardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeDashboardActivity(): DashBoardActivity
    @ContributesAndroidInjector()
    abstract fun contributeBirthDetaiInputActivity(): BirthDetaiInputActivity
}