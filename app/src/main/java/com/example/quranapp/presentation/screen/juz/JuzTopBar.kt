package com.example.quranapp.presentation.screen.juz

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quranapp.R
import com.example.quranapp.presentation.screen.settings.MotionLayoutHeader
import com.example.quranapp.presentation.screen.settings.ScrollableContent
import com.example.quranapp.utils.fonts

@Composable
fun JuzTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Quran+",
                fontSize = 20.sp,
                color = colorResource(R.color.text_name_color),
                fontFamily = fonts,
                fontWeight = FontWeight.Bold
            )
        },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = colorResource(id = R.color.unselected_tab_color)
                )
            }
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun JuzCollapsableToolbar(navController: NavController) {
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
                JuzMotionLayoutHeader(
                    navController = navController,
                    progress = if (swipingState.progress.to == SwipingStates.COLLAPSED) swipingState.progress.fraction else 1f - swipingState.progress.fraction
                ) {
                    JuzContent(navController = navController)
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
