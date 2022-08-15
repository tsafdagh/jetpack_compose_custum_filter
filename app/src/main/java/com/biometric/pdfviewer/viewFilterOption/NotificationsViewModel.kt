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


    @RequiresApi(Build.VERSION_CODES.O)
    fun filterData(filterCriterion: Filteriteria) {
        val originalList = listLiveData.value


        var listResult = mutableListOf<Notice>()

        listResult = originalList?.filter {
            filterMethod(it, filterCriterion)
        }?.toMutableList() ?: mutableListOf()


        listLiveData.postValue(listResult)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun filterMethod(notice: Notice, filterCriterion: Filteriteria): Boolean {

        val criteriaStartAt = filterCriterion.startAt?.let { it1 -> MyDateUtils.localToGMT(it1) }
        val criteriaEndDate = filterCriterion.endDate?.let { it1 -> MyDateUtils.localToGMT(it1) }

        val utcSentAt = notice.sentAt?.let { it1 -> MyDateUtils.fromLdt(it1) }

        val startAtCondition = if (criteriaStartAt != null) {
            ((utcSentAt ?: Date()) >= criteriaStartAt)
        } else {
            true
        }

        val endAtCondition = if (criteriaEndDate != null) {

            (criteriaEndDate <= (utcSentAt
                ?: Date()))
        } else {
            true
        }

        if (criteriaStartAt != null && criteriaEndDate != null) {
            (((utcSentAt ?: Date()) >= criteriaStartAt) && criteriaEndDate <= (utcSentAt
                ?: Date()))
        }

        return startAtCondition && endAtCondition
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun provideData() {
        val list = mutableListOf(
            Notice(
                id = UUID.randomUUID(),
                title = "Title 1",
                message = "1 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-07-11T15:55:46"),
                isStarred = false,
                readAt = null
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 2",
                message = "2 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-08-08T15:55:46"),
                isStarred = false,
                readAt = MyDateUtils.convertStringToLocalDateTime("2022-08-08T20:52:26")
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 3",
                message = "3 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-07-18T15:52:26"),
                isStarred = false,
                readAt = null
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 4",
                message = "4 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-06-27T15:52:26"),
                isStarred = true,
                readAt = MyDateUtils.convertStringToLocalDateTime("2022-06-28T15:52:26")
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
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-08-11T15:55:46"),
                isStarred = false,
                readAt = null
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 7",
                message = "7 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-07-11T15:55:46"),
                isStarred = false,
                readAt = null
            ),
            Notice(
                id = UUID.randomUUID(),
                title = "Title 8",
                message = "8 Your submission for Review Sample has been approved.",
                sentAt = MyDateUtils.convertStringToLocalDateTime("2022-08-11T22:55:46"),
                isStarred = true,
                readAt = MyDateUtils.convertStringToLocalDateTime("2022-08-28T20:52:26")
            )
        )
        listLiveData.postValue(list)

    }

}