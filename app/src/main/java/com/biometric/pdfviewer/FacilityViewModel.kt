package com.biometric.pdfviewer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biometric.pdfviewer.persistence.FacilityEntity
import com.biometric.pdfviewer.repository.IFacilityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FacilityViewModel @Inject constructor(private val repository: IFacilityRepository) :
    ViewModel() {


    fun insertFacilities(facilities: List<FacilityEntity>) {
        viewModelScope.launch {
            repository.saveFacilities(facilities)
        }
    }

    fun insertFacility(facility: FacilityEntity) {
        viewModelScope.launch {
            repository.saveFacility(facility)
        }
    }

    fun getFacility(id: String) {
        viewModelScope.launch {
            repository.getFacilityById(id)
        }
    }

    val facilitiesData = MutableLiveData<List<FacilityEntity>>()
    fun getFacilities() {

      repository.getFacilities().onEach {
            facilitiesData.postValue(it)
        }.launchIn(viewModelScope)
    }

    fun updateFacility(updatedFacility: FacilityEntity) {
        viewModelScope.launch {
            repository.updateFacility(updatedFacility)
        }
    }

    fun deleteFacilityById(id:String){
        viewModelScope.launch {
            repository.deleteFacilityById(id)
        }
    }


    fun randomFacilities(number:Int):List<FacilityEntity>{
        val facilities = mutableListOf<FacilityEntity>()
        (0..number).forEach {
            facilities.add(createFacilityEntity())
        }

        return facilities.toList()
    }

   fun createFacilityEntity(): FacilityEntity {

        return FacilityEntity(
            facilityId = UUID.randomUUID().toString(),
            facilityName = "facility Name"+(0..10).random(),
            hiringOrgId = UUID.randomUUID().toString(),
            hiringOrgLogo = "logo"+(0..10).random(),
            hiringOrgName = "Hiring name "+(0..10).random(),
            isCompliant = true
        )
    }


}