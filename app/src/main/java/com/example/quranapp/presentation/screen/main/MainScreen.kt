package com.example.quranapp.presentation.screen.main

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quranapp.R
import com.example.quranapp.presentation.navigation.BottomModule
import com.example.quranapp.presentation.navigation.Screen
import com.example.quranapp.presentation.navigation.bottomNavigationItems
import com.example.quranapp.presentation.screen.quran.QuranActivity
import kotlin.math.roundToInt

@Preview
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = hiltViewModel()
) {

    //rememberSystemUiController().isStatusBarVisible = false

    val context = LocalContext.current

    var a by remember {
        mutableStateOf(true)
    }

    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        a = controller.currentDestination?.route == Screen.MainScreen.route ||
                controller.currentDestination?.route == Screen.QuranScreen.route ||
                controller.currentDestination?.route == Screen.SettingsScreen.route
    }

    val bottomBarHeight = 60.dp
    val bottomBarHeightPx = with(LocalDensity.current) {
        bottomBarHeight.roundToPx().toFloat()
    }
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.value + delta
                bottomBarOffsetHeightPx.value = newOffset.coerceIn(-bottomBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        scaffoldState = scaffoldState,
        bottomBar = {
            if (a) {
                BottomAppBar(
                    cutoutShape = CircleShape,
                    modifier = Modifier
                        .height(bottomBarHeight)
                        .offset {
                            IntOffset(
                                x = 0,
                                y = -bottomBarOffsetHeightPx.value.roundToInt()
                            )
                        },
                    backgroundColor = Color.White,
                    elevation = 0.dp,
                ) {
                    AppBottomNavigation(
                        navController,
                        bottomNavigationItems
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .wrapContentHeight()
                    .offset {
                        IntOffset(
                            x = 0,
                            y = -bottomBarOffsetHeightPx.value.roundToInt() * 2
                        )
                    },
                onClick = {
                    context.startActivity(Intent(context, QuranActivity::class.java))
                },
                shape = RoundedCornerShape(50),
                backgroundColor = colorResource(id = R.color.title_text_color),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_read_quran),
                    contentDescription = null,
                    tint = colorResource(id = R.color.fab_icon_color),
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        LayerContent(navController = navController, mainViewModel = mainViewModel)
    }
}

@Composable
fun AppBottomNavigation(
    navController: NavController,
    bottomNavigationItems: List<BottomModule>
) {
    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavigationItems.forEach { bottomScreen ->
            BottomNavigationItem(
                selected = false,
                onClick = {
                    navController.navigate(route = bottomScreen.screen) {
                        navController.graph.startDestinationRoute?.let { screen ->
                            popUpTo(screen) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = bottomScreen.painter),
                        contentDescription = null,
                        tint = colorResource(id = R.color.title_text_color)
                    )
                },
                label = {
                    Text(
                        text = bottomScreen.title
                    )
                },
                alwaysShowLabel = false,
            )
        }
    }
}