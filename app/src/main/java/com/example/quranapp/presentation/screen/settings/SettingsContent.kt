package com.example.quranapp.presentation.screen.settings

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun CollapsableToolbar() {
    val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        val heightInPx = with(LocalDensity.current) { maxHeight.toPx() } // Get height of screen
        val connection = remember {
            object : NestedScrollConnection {

                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return if (delta < 0) {
                        swipingState.performDrag(delta).toOffset()
                    } else {
                        Offset.Zero
                    }
                }

                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return swipingState.performDrag(delta).toOffset()
                }

                override suspend fun onPostFling(
                    consumed: Velocity,
                    available: Velocity
                ): Velocity {
                    swipingState.performFling(velocity = available.y)
                    return super.onPostFling(consumed, available)
                }

                private fun Float.toOffset() = Offset(0f, this)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .swipeable(
                    state = swipingState,
                    thresholds = { _, _ -> FractionalThreshold(0.5f) },
                    orientation = Orientation.Vertical,
                    anchors = mapOf(
                        // Maps anchor points (in px) to states
                        0f to SwipingStates.COLLAPSED,
                        heightInPx to SwipingStates.EXPANDED,
                    )
                )
                .nestedScroll(connection)
        ) {
//z		var animateToEnd by remember { mutableStateOf(false) }
//		val progress by animateFloatAsState(
//			targetValue = if (animateToEnd) 1f else 0f,
//			animationSpec = tween(1000)
//		)
            Column {
//				Text(text = "From: ${swipingState.progress.from}", modifier = Modifier.padding(16.dp))
//				Text(text = "To: ${swipingState.progress.to}", modifier = Modifier.padding(16.dp))
//				Text(text = swipingState.progress.fraction.toString(), modifier = Modifier.padding(16.dp))
                MotionLayoutHeader(progress = if (swipingState.progress.to == SwipingStates.COLLAPSED) swipingState.progress.fraction else 1f - swipingState.progress.fraction) {
                    ScrollableContent()
                }
            }
//		Button(onClick = { animateToEnd = animateToEnd.not() },
//			Modifier
//				.align(Alignment.BottomCenter)
//				.padding(16.dp)) {
//			Text(text = if (!animateToEnd) "Collapse" else "Expand")
//		}
        }
    }
}

// Helper class defining swiping State
enum class SwipingStates {
    EXPANDED,
    COLLAPSED
}


@Composable
internal fun ScrollableContent() {
    val list = listOf(1..600).flatten()
    LazyColumn(
        Modifier.padding(
            bottom = 56.dp // We need to reduce content height by the height of collapsed content
        )
    ) {
        items(
            items = list,
            itemContent = { id ->
                ScrollableContentItem(id = id.toString())
            },
        )
    }
}

@Composable
private fun ScrollableContentItem(id: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "Item $id",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h5
            )
        }

    }
}