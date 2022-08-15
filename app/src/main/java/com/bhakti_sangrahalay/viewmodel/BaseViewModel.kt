package com.bhakti_sangrahalay.viewmodel

import androidx.lifecycle.ViewModel
import com.bhakti_sangrahalay.database.db.AppDatabase
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    @Inject
    lateinit var database: AppDatabase

}