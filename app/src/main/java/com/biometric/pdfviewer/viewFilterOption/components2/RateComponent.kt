package com.biometric.pdfviewer.viewFilterOption.components2

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.biometric.pdfviewer.entities.ChoiceModel

@Composable
fun RateComponent(
    modifier: Modifier = Modifier,
    choiceData:List<Int>,
    choiceObject: ChoiceModel,
    onItemSelected: (Int) ->Unit
    ) {

    var selectedItem by remember{ mutableStateOf(-1)}

    Column(
        modifier
            .background(color = Color.DarkGray)
            .padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = choiceObject.title, color = Color.White)

        Row(modifier = Modifier
            .padding(start = 8.dp, top = 12.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())) {

            choiceData.forEach {itemTemp->

                val selectedColor = if(selectedItem == itemTemp){Color.Blue}else{Color.Transparent}

                val cutumModifier = Modifier
                    .padding(end = 8.dp)
                    .size(36.dp)

                    .border(width = 1.dp, shape = CircleShape, color = Color.White)
                    .clickable {
                        selectedItem =  itemTemp
                        onItemSelected(selectedItem)
                    }
                    .padding(1.dp)
                    .background(
                        color = selectedColor,
                        shape = CircleShape
                    )
                    .padding(8.dp)

                Box(
                    modifier = cutumModifier,
                ) {


                    Text(text =itemTemp.toString() , color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}



data class TwoField(
    var text:String,
    var value:Int
){
    constructor():this("", 0)
}