package com.biometric.pdfviewer.viewFilterOption.components2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.biometric.pdfviewer.entities.ChoiceModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DropDownSection(
    modifier: Modifier = Modifier,
    choiceObject: ChoiceModel,
    onElementSelected:(String) ->Unit
) {

    val suggestions = choiceObject.choices.toMutableList()
    if (choiceObject.hasNone) {
        suggestions.add(NONE)
    }
    if (choiceObject.hasOther) {
        suggestions.add(OTHER)
    }
    Column(
        modifier = modifier
            .background(color = Color.DarkGray)
            .fillMaxWidth()
            .padding(20.dp),

    ) {

        var selectedElement by remember {
            mutableStateOf("")
        }

        MainDropdown(
            elements = suggestions,
            modifier = Modifier.padding(vertical = 18.dp),
            onItemTextSelected = {
                selectedElement = it
                onElementSelected(selectedElement)
            }
        )


        AnimatedVisibility(
            visible = selectedElement.equals(
                OTHER,
                ignoreCase = true
            )
        ) {
            OtherReasonTextField(
                                onTextFieldChange = {
                    onElementSelected(it)
                }
            )
        }
    }
}


@Composable
fun MainDropdown(
    modifier: Modifier = Modifier,
    elements: List<String>,
    onItemTextSelected: (selectedText: String) -> Unit
) {
    var mExpanded by remember { mutableStateOf(false) }


    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier.padding(20.dp)) {

        OutlinedTextField(
            readOnly = true,
            value = mSelectedText,
            onValueChange = {  },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {Text("Label")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedLabelColor = MaterialTheme.colors.primary,
                textColor = Color.White
            )
        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            elements.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label
                    onItemTextSelected(label)
                    mExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }

    }
}

@Composable
private fun OtherReasonTextField(
    onTextFieldChange: (newText: String) -> Unit
) {

    var otherTextFieldValue by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current


    OutlinedTextField(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),

        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedLabelColor = MaterialTheme.colors.primary,
            textColor = Color.White
        ),
        singleLine = false,
        value = otherTextFieldValue,

        onValueChange = { newValue ->
            otherTextFieldValue = newValue
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.moveFocus(FocusDirection.Down)
                onTextFieldChange(otherTextFieldValue)
            }
        ),

        label = {
            Text(
                text = "Describe"
            )
        },
    )
}