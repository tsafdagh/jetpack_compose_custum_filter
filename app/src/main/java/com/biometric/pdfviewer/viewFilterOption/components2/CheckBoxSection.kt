package com.biometric.pdfviewer.viewFilterOption.components2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.biometric.pdfviewer.entities.ChoiceModel

val NONE = "None"
val OTHER = "Other"

@Composable
fun CheckboxBlocComponent(
    modifier: Modifier = Modifier,
    choiceObject: ChoiceModel,
    onItemsSelected: (selectedValue: String) -> Unit,
    onItemsUnSelected: (unSelectedValue: String) -> Unit
) {

    var isOtherViewSelected by remember {
        mutableStateOf(false)
    }

    var isNoneViewSelected by remember {
        mutableStateOf(false)
    }

    var otherTextFieldValue by remember {
        mutableStateOf("")
    }

    Column(
        modifier
            .background(color = Color.DarkGray)
            .padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = choiceObject.title, color = Color.White)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
        ) {

            choiceObject.choices.forEach { item ->
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    val isChecked = remember { mutableStateOf(false) }

                    Checkbox(
                        checked = if(isNoneViewSelected) false else isChecked.value,
                        onCheckedChange = { checkedstate ->
                            isChecked.value = checkedstate

                            if (checkedstate) {
                                isNoneViewSelected = false
                                onItemsSelected(item)
                            } else {
                                onItemsUnSelected(item)
                            }

                        },
                        enabled = true,
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Magenta,
                            uncheckedColor = Color.LightGray,
                            checkmarkColor = Color.LightGray
                        )
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = item,
                        color = Color.White
                    )
                }
            }

            if (choiceObject.hasNone) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Checkbox(
                        checked = isNoneViewSelected,
                        onCheckedChange = { checkedstate ->
                            isNoneViewSelected = checkedstate
                            if(isNoneViewSelected){
                                isOtherViewSelected = false
                                otherTextFieldValue =""
                                onItemsSelected(NONE)
                            }else{
                                onItemsUnSelected(NONE)
                            }
                        },
                        enabled = true,
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Magenta,
                            uncheckedColor = Color.LightGray,
                            checkmarkColor = Color.LightGray
                        )
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = NONE,
                        color = Color.White
                    )
                }
            }
            if (choiceObject.hasOther) {

                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Checkbox(
                        checked = isOtherViewSelected,
                        onCheckedChange = { checkedstate ->
                            isOtherViewSelected = checkedstate

                            if (checkedstate) {
                                isOtherViewSelected = true
                            } else {
                                onItemsUnSelected(otherTextFieldValue)
                                otherTextFieldValue = ""
                            }
                        },
                        enabled = true,
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Magenta,
                            uncheckedColor = Color.LightGray,
                            checkmarkColor = Color.LightGray
                        )
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = OTHER,
                        color = Color.White
                    )
                }
            }

            if (isOtherViewSelected) {
                val focusManager = LocalFocusManager.current
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = otherTextFieldValue,
                    onValueChange = { newValue ->
                        otherTextFieldValue = newValue
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                            onItemsSelected(otherTextFieldValue)
                        }
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedLabelColor = MaterialTheme.colors.primary,
                        textColor = Color.White
                    )
                )
            }
        }
    }
}