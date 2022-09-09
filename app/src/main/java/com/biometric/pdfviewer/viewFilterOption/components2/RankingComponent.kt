package com.biometric.pdfviewer.viewFilterOption.components2

import android.R
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biometric.pdfviewer.viewFilterOption.NotificationsViewModel
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.draggedItem
import org.burnoutcrew.reorderable.rememberReorderState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun ShowRanking(viewModel: NotificationsViewModel) {

    val state = rememberReorderState() // 1.
    val items by viewModel.listOfElementObserver.observeAsState(listOf())

    LazyColumn(
        state = state.listState,
        modifier = Modifier.fillMaxWidth()
            .reorderable(state, { fromPos, toPos -> // 2.
                viewModel.onTaskReordered(items.toMutableList(), fromPos, toPos)
            })
            .detectReorderAfterLongPress(state) // 3.
        , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(items) { index, item ->
            Text(
                modifier =Modifier. padding(vertical = 8.dp)
                    .draggedItem(state.offsetByKey(item.index))
                ,
                text= item.value,
                color = Color.Black, fontSize = 20.sp
            )
        }
    }
}

/*

@Composable
fun VerticalReorderList() {
    val data = remember { mutableStateOf(List(100) { "Item $it" }) }
    val state = rememberReorderableLazyListState(onMove = { from, to ->
        data.value = data.value.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }
    })
    LazyColumn(
        state = state.listState,
        modifier = Modifier
            .reorderable(state)
            .detectReorderAfterLongPress(state)
    ) {
        items(data.value, { it }) { item ->
            ReorderableItem(state, key = item) { isDragging ->
                val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
                Column(
                    modifier = Modifier
                        .shadow(elevation.value)
                        .background(MaterialTheme.colors.surface)
                ) {
                    Text(item)
                }
            }
        }
    }
}*/
