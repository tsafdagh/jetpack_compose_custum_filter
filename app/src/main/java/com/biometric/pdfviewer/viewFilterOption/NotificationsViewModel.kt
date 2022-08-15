package com.biometric.pdfviewer.viewFilterOption

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.biometric.pdfviewer.fileDownload.models.Filteriteria
import com.biometric.pdfviewer.fileDownload.models.Notice
import com.biometric.pdfviewer.fileDownload.utils.MyDateUtils
import java.util.*

class NotificationsViewModel() : ViewModel() {

    val listLiveData = MutableLiveData<List<Notice>>()

    var originalList = mutableListOf<Notice>()


    @RequiresApi(Build.VERSION_CODES.O)
    fun filterData(filterCriterion: Filteriteria) {

        if (originalList.isEmpty()) {
            originalList = listLiveData.value as MutableList<Notice>
        }

        val listResult = originalList.filter {
            filterMethod(it, filterCriterion)
        } //filterList(originalList, filterCriterion)

        listLiveData.postValue(listResult)

    }

/*    fun clearFilter(){
        if (originalList.isNotEmpty()){
            listLiveData.postValue(originalList)
        }
    }*/

/*
    @RequiresApi(Build.VERSION_CODES.O)
    fun filterList(originalList:List<Notice>, filterCriterion:Filteriteria):List<Notice>{

        val filteredList = mutableListOf<Notice>()

        val criteriaStartAt = filterCriterion.startAt?.let { it1 -> MyDateUtils.localToGMT(it1) }
        val criteriaEndDate = filterCriterion.endDate?.let { it1 -> MyDateUtils.localToGMT(it1) }

        originalList.forEach { noticeTemps->

            var firstCriteriaValidate = false
            var secondriteriaValidate = false

            if (criteriaStartAt != null){
                val utcSentAt = noticeTemps.sentAt?.let { it1 -> MyDateUtils.fromLdt(it1) }

                if((utcSentAt ?: Date()) >= criteriaStartAt){
                    filteredList.add(noticeTemps)
                }
            }

            if(criteriaEndDate != null){
                val utcSentAt = noticeTemps.sentAt?.let { it1 -> MyDateUtils.fromLdt(it1) }

                if(criteriaEndDate <= (utcSentAt ?: Date())){
                    filteredList.add(noticeTemps)
                }
            }

            if(filterCriterion.onlyStarredItem){
                if(noticeTemps.isStarred){

                }
            }

        }

        return filteredList
    }
*/


    @RequiresApi(Build.VERSION_CODES.O)
    fun filterMethod(notice: Notice, filterCriterion: Filteriteria): Boolean {

        val criteriaStartAt = filterCriterion.startAt?.let { it1 -> MyDateUtils.localToGMT(it1) }
        val criteriaEndDate = filterCriterion.endDate?.let { it1 -> MyDateUtils.localToGMT(it1) }

        val noticeUtcSentAt = notice.sentAt?.let { it1 -> MyDateUtils.fromLdt(it1) }

        val startAtCondition = if (criteriaStartAt != null) {
            ((noticeUtcSentAt ?: Date()) >= criteriaStartAt)
        } else {
            true
        }

        val endAtCondition = if (criteriaEndDate != null) {
            (noticeUtcSentAt ?: Date()) <= criteriaEndDate
        } else {
            true
        }

        val onlyStarredItemCondition = if (filterCriterion.onlyStarredItem) {
            startAtCondition && endAtCondition && notice.isStarred
        }else{true}

        val viewReadItem =  if (filterCriterion.viewReadItems) {
            startAtCondition && endAtCondition && (notice.readAt != null)
        }else{true}

/*        val generalCond = if (criteriaEndDate != null) {
            if (filterCriterion.onlyStarredItem) {
                if (filterCriterion.viewReadItems) {
                    (criteriaEndDate <= (utcSentAt
                        ?: Date())) && notice.isStarred && (notice.readAt != null)
                } else {
                    (criteriaEndDate <= (utcSentAt ?: Date())) && notice.isStarred
                }
            } else {
                (criteriaEndDate <= (utcSentAt
                    ?: Date()))
            }
        } else if (filterCriterion.onlyStarredItem) {
                if (filterCriterion.viewReadItems) {
                    notice.isStarred && (notice.readAt != null)
                } else {
                    notice.isStarred
                }
            } else {
                true
            }*/


        return startAtCondition && endAtCondition && onlyStarredItemCondition && viewReadItem
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun provideData() {
        val list = mutableListOf(
            Notice(
                id = UUID.randomUUID(),
                title = "Title 1",
                message = "1 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-08-05T15:55:46"),
                isStarred = false,
                readAt = null
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 2",
                message = "2 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-08-06T15:55:46"),
                isStarred = false,
                readAt = MyDateUtils.convertStringToLocalDateTime("2022-08-07T20:52:26")
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 3",
                message = "3 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-08-08T15:52:26"),
                isStarred = false,
                readAt = null
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 4",
                message = "4 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-08-09T15:52:26"),
                isStarred = true,
                readAt = MyDateUtils.convertStringToLocalDateTime("2022-08-10T15:52:26")
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 5",
                message = "5 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-08-11T13:34:03"),
                isStarred = false,
                readAt = null
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 6",
                message = "6 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-08-12T15:55:46"),
                isStarred = false,
                readAt = null
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 7",
                message = "7 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-08-13T20:52:26"),
                isStarred = false,
                readAt = null
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 8",
                message = "8 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-08-14T22:55:46"),
                isStarred = true,
                readAt = MyDateUtils.convertStringToLocalDateTime("2022-08-15T20:52:26")
            )
        )
        listLiveData.postValue(list)

    }

}