package com.biometric.pdfviewer.repository

import com.biometric.pdfviewer.persistence.FacilityEntity
import kotlinx.coroutines.flow.Flow

interface IFacilityRepository {
    fun getFacilities(): Flow<List<FacilityEntity>>
    fun getFacilityById(id: String): FacilityEntity?

    suspend fun saveFacilities(facilities: List<FacilityEntity>)

    suspend fun saveFacility(facility: FacilityEntity)

    suspend fun updateFacility(updatedFacilityEntity: FacilityEntity)

    suspend fun deleteFacilityById(id:String)
}