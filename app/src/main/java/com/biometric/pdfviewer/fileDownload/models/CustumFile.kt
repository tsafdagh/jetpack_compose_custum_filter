package com.biometric.pdfviewer.fileDownload.models

import android.graphics.Bitmap
import android.net.Uri

open class CustomFile(
    open var type: String,
    open var Uri: Uri? = null,
)


data class ImageFileFromCamera(override var type: String, var bitmap: Bitmap) : CustomFile(type)

data class ImageFileFileFromGallery(override var type: String, override var Uri: Uri?) : CustomFile(type, Uri)

data class DocumentFIle(override var type: String, override var Uri: Uri?) : CustomFile(type, Uri)
