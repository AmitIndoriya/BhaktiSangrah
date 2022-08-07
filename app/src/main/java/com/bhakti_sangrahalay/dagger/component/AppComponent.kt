package com.astrologerchat.di.component

import android.app.Application
import com.astrologerchat.di.builders.ActivityBuilder.ActivityModule
import com.astrologerchat.di.module.ViewModelModule
import com.astrologerchat.di.module.AppModule
import com.astrologerchat.di.module.NetworkModule
import com.bhakti_sangrahalay.app.MyApp
import com.bhakti_sangrahalay.dagger.module.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        DatabaseModule::class,
        ActivityModule::class]
)
interface AppComponent {

    fun inject(app: MyApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
        fun build(): AppComponent
    }
}