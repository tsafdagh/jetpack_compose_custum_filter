package com.biometric.pdfviewer.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.biometric.pdfviewer.persistence.FacilityEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FacilityEntityDao {

    @Query("SELECT * FROM FacilityEntities")
    fun getAllEntities() : Flow<List<FacilityEntity>>

    @Query("SELECT * FROM FacilityEntities WHERE facilityId = :id")
    fun getEntity(id: String): FacilityEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(facilities: List<FacilityEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(facility: FacilityEntity)

    @Query("DELETE FROM FacilityEntities WHERE facilityId = :id")
    suspend fun deleteById(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplace(facility: FacilityEntity)
}