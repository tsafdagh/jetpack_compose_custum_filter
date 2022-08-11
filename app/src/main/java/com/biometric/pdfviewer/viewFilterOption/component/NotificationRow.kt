package com.biometric.pdfviewer.viewFilterOption.component

import android.os.Build
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biometric.pdfviewer.fileDownload.models.Notice
import com.biometric.pdfviewer.fileDownload.utils.MyDateUtils
import com.biometric.pdfviewer.fileDownload.utils.MyDateUtils.timeAgo
import java.time.LocalDateTime

@Composable
fun NotificationRow(
    onCLick: () -> Unit,
    notice: Notice

//    onLongPress: () -> Unit,
) {
    var isRead by remember { mutableStateOf(notice.readAt != null) } // Chechk if the notification has been read or not
    var isStar by remember { mutableStateOf(notice.isStarred) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
//            .height(70.dp)
            .clickable(onClick = onCLick),
    ) {

        Row(
            modifier = Modifier
                .background(
                    color = Color.DarkGray
                )
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            if (!isRead) {
                Canvas(
                    modifier = Modifier
                        .size(5.dp)
                        .padding(top = 12.dp)
                        .weight(1f),
                    onDraw = {
                        val size = 10.dp.toPx()
                        drawCircle(
                            color = Color.Red,
                            radius = size / 2f
                        )
                    }
                )
            } else {
                Spacer(modifier = Modifier.width(15.dp))
            }
            Column(
                modifier = Modifier
                    .weight(25f)
                    .padding(start = 5.dp)
            ) {
                Row(
                    // modifier = Modifier.weight(2f),
                    // verticalAlignment = Alignment.CenterVertically,
//                     horizontalArrangement = Arrangement.Start
                ) {

                    Text(
                        text = notice.title + " - " + notice.message,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(2.0f),
                        maxLines = 1,
                    )

                    var timeAgoText = ""
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val javaDate = notice.sentAt?.let { MyDateUtils.fromLdt(it) }
                        timeAgoText = javaDate?.timeAgo() ?: notice.sentAt.toString()
                    } else {
                        timeAgoText = notice.sentAt.toString()
                    }

                    println("** Date value: ${notice.sentAt}")

                    Text(
                        text = timeAgoText,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Light,
                        fontSize = 13.sp,
                        textAlign = TextAlign.End,
                        maxLines = 1,
                        modifier = Modifier.weight(1.0f)
                    )
                }
                Row(
                    // modifier = Modifier.align(Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = notice.message,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    if (isStar) {
                        Icon(
                            Icons.Filled.Star,
                            tint = Color.Yellow,
                            contentDescription = "Star"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = notice.message,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp,
//                textAlign = TextAlign.Center,
                    maxLines = 2,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}

// @Preview(showBackground = true, showSystemUi = true)
// @Composable
// fun NotificationRowPreview() {
//    val notif = NotificationItem(
//        title = "Requirement Assigned - Say yello world",
//        name = "Denis Dumpters",
//        desc = "A new requirement form - 2021-08-30 21:08:44 been assigned to you.",
//        date = "3 weeks ago"
//    )
//    NotificationRow(notification = notif, onCLick = {})
// }

fun isNotificationRead(readAt: LocalDateTime): Boolean {
    return readAt != null
}