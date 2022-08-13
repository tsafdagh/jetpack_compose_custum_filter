package com.biometric.pdfviewer.fileDownload.models

import java.util.*

data class Filteriteria(
    var startAt: Date? = null,
    var endDate: Date? = null,
    var onlyStarredItem: Boolean = false,
    var viewReadItems: Boolean = false
) {
    constructor() : this(
        startAt = null,
        endDate = null,
        onlyStarredItem = false,
        viewReadItems = false
    )

    fun clear() {
        startAt = null
        endDate = null
        onlyStarredItem = false
        viewReadItems = false
    }
}