package com.bhakti_sangrahalay.dagger.module

import android.content.Context
import androidx.room.Room
import com.bhakti_sangrahalay.database.constants.DbConstants
import com.bhakti_sangrahalay.database.dao.BirthDetailInfoDao
import com.bhakti_sangrahalay.database.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val context: Context) {

    @Provides
    @Singleton
    fun provideDataBase() =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DbConstants.DB_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideBirthDetailInfoDao(appDatabase: AppDatabase): BirthDetailInfoDao =
        appDatabase.BirthDetailInfoDao()

}