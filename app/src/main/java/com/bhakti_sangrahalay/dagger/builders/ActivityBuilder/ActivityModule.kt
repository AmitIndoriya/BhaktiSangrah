package com.astrologerchat.di.builders.ActivityBuilder

import com.bhakti_sangrahalay.ui.activity.BirthDetailInputActivity
import com.bhakti_sangrahalay.ui.activity.BirthDetailInputActivityNew
import com.bhakti_sangrahalay.ui.activity.DashBoardActivity
import com.bhakti_sangrahalay.ui.activity.KundliOutputActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

  /*  @ContributesAndroidInjector()
    abstract fun contributeDashboardActivity(): DashBoardActivity

    @ContributesAndroidInjector()
    abstract fun contributeBirthDetaiInputActivity(): BirthDetailInputActivity*/
    @ContributesAndroidInjector()
    abstract fun contributeBirthDetailInputActivityNew(): BirthDetailInputActivityNew

  /*  @ContributesAndroidInjector()
    abstract fun contributeKundliOutputActivity(): KundliOutputActivity*/
}