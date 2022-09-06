package com.biometric.pdfviewer.viewFilterOption.components2

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.biometric.pdfviewer.entities.ChoiceModel


@Composable
fun RadioGroupSection(choiceObject: ChoiceModel, onOptionItemSelected: (selected: String) -> Unit) {

    //val radioOption = choiceObject.choices.toMutableList()
    //radioOption.add(NONE)
    //radioOption.add(OTHER)

    var isOtherViewSelected by remember {
        mutableStateOf(false)
    }
    var otherTextFieldValue by remember {
        mutableStateOf("")
    }


    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.DarkGray)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        choiceObject.choices.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            onOptionItemSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    modifier = Modifier.padding(all = Dp(value = 8F)),
                    onClick = {
                        onOptionSelected(text)
                        onOptionItemSelected(text)
                        otherTextFieldValue =""
                    }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 16.dp), color = Color.White
                )
            }
        }

        if (choiceObject.hasNone) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (NONE == selectedOption),
                        onClick = {
                            onOptionSelected(NONE)
                            onOptionItemSelected(NONE)
                            isOtherViewSelected = false
                        }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (NONE == selectedOption),
                    modifier = Modifier.padding(all = Dp(value = 8F)),
                    onClick = {
                        onOptionSelected(NONE)
                        onOptionItemSelected(NONE)
                        isOtherViewSelected = false
                        otherTextFieldValue =""
                    }
                )
                Text(
                    text = NONE,
                    modifier = Modifier.padding(start = 16.dp), color = Color.White
                )
            }
        }

        if (choiceObject.hasOther) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (OTHER == selectedOption),
                        onClick = {
                            onOptionSelected(OTHER)
                            isOtherViewSelected = true
                            //onOptionItemSelected(OTHER)
                        }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (OTHER == selectedOption),
                    modifier = Modifier.padding(all = Dp(value = 8F)),
                    onClick = {
                        onOptionSelected(OTHER)
                        isOtherViewSelected = true
                        //onOptionItemSelected(OTHER)
                    }
                )
                Text(
                    text = OTHER,
                    modifier = Modifier.padding(start = 16.dp), color = Color.White
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
                        onOptionItemSelected(otherTextFieldValue)
                    }
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = MaterialTheme.colors.primary,
                    textColor = Color.White
                ),
                label = { Text("Describe", color = Color.White) }
            )
        }


    }
}