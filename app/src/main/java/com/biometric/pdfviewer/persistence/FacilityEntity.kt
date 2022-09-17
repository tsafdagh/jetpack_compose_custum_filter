package com.biometric.pdfviewer.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FacilityEntities")
data class FacilityEntity(
    @PrimaryKey(autoGenerate = false)
    var facilityId:String,
    var facilityName:String,
    var hiringOrgId:String,
    var hiringOrgLogo: String,
    var hiringOrgName: String,
    var isCompliant:Boolean
){
    constructor():this(facilityId="", facilityName="", hiringOrgId="", hiringOrgLogo="", hiringOrgName="", isCompliant=false)
}