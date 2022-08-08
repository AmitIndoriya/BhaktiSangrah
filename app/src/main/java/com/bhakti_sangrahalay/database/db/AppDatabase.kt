package com.bhakti_sangrahalay.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bhakti_sangrahalay.database.dao.BirthDetailInfoDao
import com.bhakti_sangrahalay.database.entity.BirthDetailInfo

@Database(entities = [BirthDetailInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun BirthDetailInfoDao(): BirthDetailInfoDao
}
