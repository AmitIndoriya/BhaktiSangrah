package com.bhakti_sangrahalay.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bhakti_sangrahalay.database.entity.BirthDetailInfo

@Dao
interface BirthDetailInfoDao {

    /* @Query("SELECT * FROM BirthDetailInfo")
     suspend fun getAll(): List<BirthDetailInfo>*/
    @Query("SELECT * FROM BirthDetailInfo")
    suspend fun getAll(): List<BirthDetailInfo>

    /*  @Insert
      suspend fun insertAll(users: List<BirthDetailInfo>)*/
    @Insert
    suspend fun insertAll(users: List<BirthDetailInfo>)

    @Delete
    suspend fun delete(user: BirthDetailInfo)

    @Query("DELETE FROM BirthDetailInfo WHERE id = :id")
    fun deleteId(id: Int)

}