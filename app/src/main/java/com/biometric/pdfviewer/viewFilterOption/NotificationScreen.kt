package com.biometric.pdfviewer.viewFilterOption


import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.biometric.pdfviewer.R
import com.biometric.pdfviewer.fileDownload.models.Notice
import com.biometric.pdfviewer.fileDownload.utils.MyDateUtils.convertStringToLocalDateTime
import com.biometric.pdfviewer.viewFilterOption.component.FilterCriteriaAlertDialog
import com.biometric.pdfviewer.viewFilterOption.component.NotificationRow
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotificationScreen(viewModel: NotificationsViewModel) {

    var showCustomDialogWithResult by remember { mutableStateOf(false) }

    var isFilterCriteriaApply by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Notification",
                            style = MaterialTheme.typography.body1
                        )

                        Icon(
                            if (isFilterCriteriaApply) painterResource(id = R.drawable.ic_baseline_filter_alt_24) else painterResource(
                                id = R.drawable.ic_baseline_filter_list_24
                            ),
                            contentDescription = "Favorite",
                            modifier = Modifier
                                .size(ButtonDefaults.IconSize)
                                .clickable {
                                    showCustomDialogWithResult = true
                                },
                        )
                    }

                },
                navigationIcon = {
                    IconButton(onClick = { }) {
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

        val dataList by viewModel.listLiveData.observeAsState(listOf())

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(1.5.dp),
            state = rememberLazyListState()
        ) {
            itemsIndexed(items = dataList) { index, item ->

                NotificationRow(
                    notice = item,
                    onCLick = {
                    }
                )
            }


        }

        val context = LocalContext.current

        if (showCustomDialogWithResult) {
            FilterCriteriaAlertDialog(
                onDismiss = {
                    showCustomDialogWithResult = !showCustomDialogWithResult
                    Toast.makeText(context, "Dialog dismissed!", Toast.LENGTH_SHORT)
                        .show()
                },
                onNegativeClick = {
                    showCustomDialogWithResult = !showCustomDialogWithResult
                    Toast.makeText(context, "Negative Button Clicked!", Toast.LENGTH_SHORT)
                        .show()

                },
                onApplyClicked = { filterCriterion ->
                    showCustomDialogWithResult = !showCustomDialogWithResult

                    isFilterCriteriaApply = viewModel.checkifCriteriaEnabled(filterCriterion)
                    viewModel.filterData(filterCriterion)
                },
                onClearClicked = {
                    viewModel.clearFilter()
                    isFilterCriteriaApply =false
                    showCustomDialogWithResult = !showCustomDialogWithResult
                }
            )
        }

        LaunchedEffect(key1 = "Key1", block = {
            viewModel.provideData()
        })
    }
}
