package com.biometric.pdfviewer.fileDownload.models

import java.util.*


data class Notice(


    val id: UUID,

    val title: kotlin.String,

    val message: kotlin.String,

    val sentAt: java.time.LocalDateTime?,

    val isStarred: kotlin.Boolean,

    val readAt: java.time.LocalDateTime? = null

)

