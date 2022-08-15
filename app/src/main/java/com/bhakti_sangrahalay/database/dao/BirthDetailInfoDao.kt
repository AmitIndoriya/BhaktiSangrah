package com.bhakti_sangrahalay.database.dao

import androidx.room.*
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
    suspend fun insertAll(list: List<BirthDetailInfo>): List<Long>

    @Delete
    suspend fun delete(birthDetailInfo: BirthDetailInfo)

    @Query("DELETE FROM BirthDetailInfo WHERE id = :id")
    fun deleteId(id: Long)

    @Update
    suspend fun update(birthDetailInfo: BirthDetailInfo)

}