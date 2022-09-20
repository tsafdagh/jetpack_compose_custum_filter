package com.biometric.pdfviewer.repository

import com.biometric.pdfviewer.persistence.FacilityEntity
import com.biometric.pdfviewer.persistence.dao.FacilityEntityDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FacilityRepositoryImpl @Inject constructor(
    private val service: FacilityEntityDao
) : IFacilityRepository {
    override fun getFacilities(): Flow<List<FacilityEntity>> {
        return service.getAllEntities()
    }

    override fun getFacilityById(id: String): FacilityEntity? {
        return service.getEntity(id)
    }

    override suspend fun saveFacilities(facilities: List<FacilityEntity>) = service.insertAll(facilities)

    override suspend fun saveFacility(facility: FacilityEntity) = service.insert(facility)

    override suspend fun updateFacility(updatedFacilityEntity: FacilityEntity) =
        service.insertReplace(facility = updatedFacilityEntity)

    override suspend fun deleteFacilityById(id: String) =service.deleteById(id)
}