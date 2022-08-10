package com.astrologerchat.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.astrologerchat.di.factory.ViewModelFactory
import com.astrologerchat.di.keys.ViewModelKey
import com.bhakti_sangrahalay.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  MovieListViewModel.class as key,
     * and a Provider that will build a MovieListViewModel
     * object.
     * 
     * */

    @Binds
    @IntoMap
    @ViewModelKey(DashBoardViewModel::class)
    protected abstract fun dashBoardViewModel(dashBoardViewModel: DashBoardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BirthDetaiInputActivityViewModel::class)
    protected abstract fun birthDetaiInputActivityViewModel(birthDetaiInputActivityViewModel: BirthDetaiInputActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(KundliOutputActivityViewModel::class)
    protected abstract fun kundliOutputActivityViewModel(kundliOutputActivityViewModel: KundliOutputActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AartiDescActivityViewModel::class)
    protected abstract fun aartiDescActivityViewModel(aartiDescActivityViewModel: AartiDescActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChalishaDescActivityModel::class)
    protected abstract fun chalishaDescActivityModel(chalishaDescActivityModel: ChalishaDescActivityModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PanchangViewModel::class)
    protected abstract fun panchangViewModel(panchangViewModel: PanchangViewModel): ViewModel
}