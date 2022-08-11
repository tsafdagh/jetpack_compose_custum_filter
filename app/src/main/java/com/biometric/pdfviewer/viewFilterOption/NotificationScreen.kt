package com.biometric.pdfviewer.viewFilterOption


import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.biometric.pdfviewer.fileDownload.models.Notice
import com.biometric.pdfviewer.fileDownload.utils.MyDateUtils.convertStringToLocalDateTime
import com.biometric.pdfviewer.viewFilterOption.component.NotificationRow
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotificationScreen(
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notification",
                        style = MaterialTheme.typography.h3
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }

    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(1.5.dp),
            state =rememberLazyListState()
        ) {
            itemsIndexed(items = provideData()) { index, item ->

                NotificationRow(
                    notice = item,
                    onCLick = {
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun provideData(): List<Notice> {
    return listOf(
        Notice(
            id = "506ab06a-0c71-4ca9-aa4e-f68240118e02",
            title = "Requirement Approved",
            message = "Your submission for Review Sample has been approved.",
            sentAt = convertStringToLocalDateTime("2022-08-11T15:55:46"),
            isStarred = false,
            readAt = null
        ),
        Notice(
            id = "3ca3c17f-d660-4b22-ae75-3d8b337b63d9",
            title = "Requirement Approved",
            message = "Your submission for Review Sample has been approved.",
            sentAt = convertStringToLocalDateTime("2022-08-11T15:52:26"),
            isStarred = false,
            readAt = null
        ),
        Notice(
            id = "506ab06a-0c71-4ca9-aa4e-f68240118e02",
            title = "Requirement Approved",
            message = "Your submission for Review Sample has been approved.",
            sentAt = convertStringToLocalDateTime("2022-08-11T13:34:03"),
            isStarred = false,
            readAt = null
        ),
        Notice(
            id = "506ab06a-0c71-4ca9-aa4e-f68240118e02",
            title = "Requirement Approved",
            message = "Your submission for Review Sample has been approved.",
            sentAt = convertStringToLocalDateTime("2022-08-11T15:55:46"),
            isStarred = false,
            readAt = null
        ),
        Notice(
            id = "506ab06a-0c71-4ca9-aa4e-f68240118e02",
            title = "Requirement Approved",
            message = "Your submission for Review Sample has been approved.",
            sentAt = convertStringToLocalDateTime("2022-08-11T15:55:46"),
            isStarred = false,
            readAt = null
        )
    )
}