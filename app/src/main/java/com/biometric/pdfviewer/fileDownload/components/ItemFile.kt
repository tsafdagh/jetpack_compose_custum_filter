package com.biometric.pdfviewer.fileDownload.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.biometric.pdfviewer.fileDownload.models.File

@Composable
fun ItemFile(
    file: File,
    startDownload:(File) -> Unit,
    openFile:(File) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .border(width = 2.dp, color = Color.Blue, shape = RoundedCornerShape(16.dp))
            .clickable {
                if (!file.isDownloading){
                    if (file.downloadedUri.isNullOrEmpty()){
                        startDownload(file)
                    }else{
                        openFile(file)
                    }
                }
            }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Text(
                    text = file.name,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black
                )

                Row {
                    val description = if (file.isDownloading){
                        "Downloading..."
                    }else{
                        if (file.downloadedUri.isNullOrEmpty()) "Tap to download the file" else "Tap to open file"
                    }
                    Text(
                        text = description,
                        fontStyle = FontStyle.Italic,
                        color = Color.DarkGray
                    )
                }

            }

            if (file.isDownloading){
                CircularProgressIndicator(
                    color = Color.Blue,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterVertically)
                )
            }

        }

    }
}