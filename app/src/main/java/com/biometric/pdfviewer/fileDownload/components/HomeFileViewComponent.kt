package com.biometric.pdfviewer.fileDownload.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.biometric.pdfviewer.fileDownload.models.File
import com.biometric.pdfviewer.fileDownload.utils.DaowloadingUtils.startDownloadingFile
import com.pspdfkit.ui.PdfActivityIntentBuilder


//https://kotlinlang.org/docs/kotlin-reference.pdf
//https://pspdfkit.com/blog/2021/open-pdf-in-jetpack-compose-app/
@Composable
fun HomeFileViewComponent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current

        val data = remember {
            mutableStateOf(
                File(
                    id = "10",
                    name = "Pdf File 2 MB",
                    type = "PDF",
                    url = "https://lyceeduruy.fr/wp-content/uploads/2013/04/R%C3%A9visions-Philosophie-Le-Monde.pdf",
                    downloadedUri = null
                )
            )
        }

        val lifeCycleOwner = LocalLifecycleOwner.current
        ItemFile(
            file = data.value,
            startDownload = {
                startDownloadingFile(
                    lifeCycleOwner=lifeCycleOwner,
                    context=context,
                    file = data.value,
                    success = {
                        data.value = data.value.copy().apply {
                            isDownloading = false
                            downloadedUri = it
                        }
                    },
                    failed = {

                        data.value = data.value.copy().apply {
                            isDownloading = false
                            downloadedUri = null
                        }
                    },
                    running = {
                        data.value = data.value.copy().apply {
                            isDownloading = true
                        }
                    }
                )
            },
            openFile = {



// We are opening a document from application assets.
                val documentUri = Uri.parse(it.downloadedUri)

// Build the \`Intent\` for launching the \`PdfActivity\`.
                val intent = PdfActivityIntentBuilder.fromUri(context, documentUri).build()

// Launch the activity.
                context.startActivity(intent)

/*                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(it.downloadedUri?.toUri(),"application/pdf")
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                try {
                    context.startActivity(intent)
                }catch (e: ActivityNotFoundException){
                    Toast.makeText(
                        context,
                        "Can't open Pdf",
                        Toast.LENGTH_SHORT
                    ).show()
                }*/
            }
        )
    }
}

@Composable
fun PDFViewsdsds(
    byteArray: ByteArray
) {




}