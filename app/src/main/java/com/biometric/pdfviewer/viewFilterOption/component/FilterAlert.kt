package com.biometric.pdfviewer.viewFilterOption.component

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.biometric.pdfviewer.R
import com.biometric.pdfviewer.fileDownload.models.Filteriteria
import com.biometric.pdfviewer.fileDownload.utils.MyDateUtils.convertDateToSpecificStringFormat
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun FilterCriteriaAlertDialog(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onClearClicked: () -> Unit,
    onApplyClicked: (Filteriteria) -> Unit
) {

    val selectionCriteria = Filteriteria()

    val tmpDate: Date? = null
    var startAt by remember { mutableStateOf(tmpDate) }
    var endDate by remember { mutableStateOf(tmpDate) }
    var onlyStarred by remember { mutableStateOf(false) }
    var viewReadItm by remember { mutableStateOf(false) }


    val mContext = LocalContext.current

    val defaultDate = Calendar.getInstance()
    defaultDate.time = Date()


    val mStartDatePicker = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->

            val cal = Calendar.getInstance()
            cal.set(Calendar.YEAR, mYear)
            cal.set(Calendar.MONTH, mMonth)
            cal.set(Calendar.DAY_OF_MONTH, mDayOfMonth)

            startAt = cal.time

        }, defaultDate.get(Calendar.YEAR), defaultDate.get(Calendar.MONTH), defaultDate.get(Calendar.DAY_OF_MONTH)
    )
    val mEndDatePicker = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->

            val cal = Calendar.getInstance()
            cal.set(Calendar.YEAR, mYear)
            cal.set(Calendar.MONTH, mMonth)
            cal.set(Calendar.DAY_OF_MONTH, mDayOfMonth)

            endDate = cal.time

        }, defaultDate.get(Calendar.YEAR), defaultDate.get(Calendar.MONTH), defaultDate.get(Calendar.DAY_OF_MONTH)
    )
    mEndDatePicker.datePicker.minDate = startAt?.time?:Date().time

    Dialog(onDismissRequest = onDismiss) {

        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            backgroundColor = colorResource(id = R.color.black_cassan)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.clickable { onNegativeClick() },
                        text = "Close",
                        style = TextStyle(color = Color.Black)
                    )
                    Text(
                        text = "Filters",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        modifier = Modifier.clickable {
                            startAt = null
                            endDate = null
                            onlyStarred = false
                            viewReadItm = false
                            onClearClicked()
                        },
                        text = "Clear",
                        style = TextStyle(color = Color.Black)
                    )
                }

                Card(
                    //shape = MaterialTheme.shapes.medium,
                    shape = RoundedCornerShape(10.dp),
                    // modifier = modifier.size(280.dp, 240.dp)
                    modifier = Modifier.padding(top = 30.dp, start = 8.dp, end = 8.dp),
                    elevation = 0.dp,
                    backgroundColor = Color.White
                ) {

                    val textButonModifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            color = colorResource(
                                id = R.color.black_cassan
                            )
                        )
                        .padding(horizontal = 8.dp)

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Received Before", style = TextStyle(color = Color.Black))


                            TextButton(
                                modifier = textButonModifier,
                                onClick = {
                                    mStartDatePicker.show()
                                }) {

                                val textValue =
                                    startAt?.convertDateToSpecificStringFormat("MMM dd, yyyy")
                                        ?: "Select"
                                Text(
                                    text = textValue,
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(text = "Received After", style = TextStyle(color = Color.Black))
                            TextButton(
                                modifier = textButonModifier,
                                onClick = {
                                    mEndDatePicker.show()

                                }) {

                                val textValue =
                                    endDate?.convertDateToSpecificStringFormat("MMM dd, yyyy")
                                        ?: "Select"
                                Text(
                                    text = textValue,
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    //shape = MaterialTheme.shapes.medium,
                    shape = RoundedCornerShape(10.dp),
                    // modifier = modifier.size(280.dp, 240.dp)
                    modifier = Modifier.padding(top = 30.dp, start = 8.dp, end = 8.dp),
                    elevation = 0.dp,
                    backgroundColor = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Starred items Only",
                                style = TextStyle(color = Color.Black)
                            )


                            Switch(
                                checked = onlyStarred,
                                onCheckedChange = { onlyStarred = it },
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text = "View Read items",
                                style = TextStyle(color = Color.Black)
                            )
                            Switch(
                                checked = viewReadItm,
                                onCheckedChange = { viewReadItm = it }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    TextButton(
                        modifier = Modifier
                            .width(130.dp)
                            .background(
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(size = 8.dp)
                            ),
                        onClick = {

                            selectionCriteria.startAt = startAt
                            selectionCriteria.endDate = endDate
                            selectionCriteria.viewReadItems = viewReadItm
                            selectionCriteria.onlyStarredItem = onlyStarred

                            onApplyClicked(selectionCriteria)
                        }) {
                        Text(
                            text = "Apply",
                            style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                        )
                    }
                }

            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(name = "Custom Dialog")
@Composable
fun MyDialogUIPreview() {
    FilterCriteriaAlertDialog(
        onDismiss = { /*TODO*/ },
        onNegativeClick = { /*TODO*/ },
        onApplyClicked = {},
        onClearClicked = {}
    )
}