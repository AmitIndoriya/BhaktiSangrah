package com.astrologerchat.di.builders.ActivityBuilder

import com.bhakti_sangrahalay.ui.activity.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {


    /*@ContributesAndroidInjector()
    abstract fun contributeBirthDetaiInputActivity(): BirthDetailInputActivity*/
    @ContributesAndroidInjector()
    abstract fun contributeDashboardActivity(): DashBoardActivity

    @ContributesAndroidInjector()
    abstract fun contributeBirthDetailInputActivityNew(): BirthDetailInputActivityNew

    @ContributesAndroidInjector()
    abstract fun contributeAartiDescActivityNew(): AartiDescActivityNew

    @ContributesAndroidInjector()
    abstract fun contributeChalishaDescActivityNew(): ChalishaDescActivityNew

    @ContributesAndroidInjector()
    abstract fun contributePanchangActivity(): PanchangActivity


    /*  @ContributesAndroidInjector()
      abstract fun contributeKundliOutputActivity(): KundliOutputActivity*/
}