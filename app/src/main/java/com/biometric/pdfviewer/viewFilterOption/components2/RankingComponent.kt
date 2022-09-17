package com.biometric.pdfviewer.viewFilterOption.components2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biometric.pdfviewer.R
import org.burnoutcrew.reorderable.*

@Composable
fun ReorderableList(dataTmp:List<String>){
    val state = rememberReorderState()

    //On génère les donnée à traiter, la liste contenant les données doit être mutable
    val data =dataTmp.toMutableStateList()


    var isElementDragger by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        state = state.listState,
        modifier = Modifier.fillMaxWidth().reorderable(state, { from, to ->
            isElementDragger = true
            data.move(from.index, to.index)
        }),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(data,  { it }) {  item->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .draggedItem(state.offsetByKey(item))
                    .detectReorderAfterLongPress(state),
                horizontalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            color = colorResource(id = R.color.purple_200),
                            shape = CircleShape
                        )
                        .padding(8.dp),
                ) {

                    val text = if(isElementDragger) {"${data.indexOf(item)}"} else{"-"}

                    Text(text =text , color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Center))
                }
                Text(
                    modifier=Modifier.padding(start = 8.dp),
                    text = item,
                    color = Color.Black, fontSize = 20.sp
                )
            }
        }
    }
}