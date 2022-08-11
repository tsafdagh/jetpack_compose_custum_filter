package com.biometric.pdfviewer.fileDownload.utils

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.work.*
import com.biometric.pdfviewer.fileDownload.models.File
import com.biometric.pdfviewer.fileDownload.worker.FileDownloadWorker

object DaowloadingUtils {

    fun startDownloadingFile(
        lifeCycleOwner:LifecycleOwner,
        context:Context,
        file: File,
        success: (String) -> Unit,
        failed: (String) -> Unit,
        running: () -> Unit
    ) {
        val data = Data.Builder()

        data.apply {
            putString(FileDownloadWorker.FileParams.KEY_FILE_NAME, file.name)
            putString(FileDownloadWorker.FileParams.KEY_FILE_URL, file.url)
            putString(FileDownloadWorker.FileParams.KEY_FILE_TYPE, file.type)
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .build()

        val fileDownloadWorker = OneTimeWorkRequestBuilder<FileDownloadWorker>()
            .setConstraints(constraints)
            .setInputData(data.build())
            .build()

        val workManager = WorkManager.getInstance(context)

        workManager.enqueueUniqueWork(
            "oneFileDownloadWork_${System.currentTimeMillis()}",
            ExistingWorkPolicy.KEEP,
            fileDownloadWorker
        )

        workManager.getWorkInfoByIdLiveData(fileDownloadWorker.id)
            .observe(lifeCycleOwner) { info ->
                info?.let {
                    when (it.state) {
                        WorkInfo.State.SUCCEEDED -> {
                            success(
                                it.outputData.getString(FileDownloadWorker.FileParams.KEY_FILE_URI)
                                    ?: ""
                            )
                        }
                        WorkInfo.State.FAILED -> {
                            failed("Downloading failed!")
                        }
                        WorkInfo.State.RUNNING -> {
                            running()
                        }
                        else -> {
                            failed("Something went wrong")
                        }
                    }
                }
            }
    }
}