package com.biometric.pdfviewer.fileDownload.models


data class Notice(


    val id: String,

    val title: kotlin.String,

    val message: kotlin.String,

    val sentAt: java.time.LocalDateTime?,

    val isStarred: kotlin.Boolean,

    val readAt: java.time.LocalDateTime? = null

)

