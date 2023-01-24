package com.example.quranapp.presentation.screen.juz

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.layoutId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quranapp.R
import com.example.quranapp.presentation.screen.juz_details.JuzViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun JuzScreen(navController: NavController) {
    JuzCollapsableToolbar(navController = navController)
}


@OptIn(ExperimentalMotionApi::class)
@Composable
fun JuzMotionLayoutHeader(
    navController: NavController,
    progress: Float,
    scrollableBody: @Composable () -> Unit
) {
   /* val color by animateColorAsState(
        targetValue = if (progress > 0.5f)
            Color.White else Color.Black,

        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        ),
    )*/
    MotionLayout(
        start = JsonConstraintSetStart(),
        end = JsonConstraintSetEnd(),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .background(Color.Transparent)
                .layoutId("card"),
            shape = RoundedCornerShape(motionDistance("card", "radius")),
            elevation = 4.dp,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(id = R.color.brush_1),
                                colorResource(id = R.color.brush_2)
                            )
                        )
                    )
            ) {

            }
        }
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentHeight()
                .layoutId("text1"),
            text = "Play My Own",
            color = Color.White,
            fontSize = motionProperties("text1").value.fontSize("textSize"),
        )
        Text(
            text = "Khatam Quran",
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .layoutId("text2"),
            fontSize = motionProperties("text2").value.fontSize("textSize"),
            color = Color.White,
            textAlign = TextAlign.Start
        )
        Image(
            painter = painterResource(id = R.drawable.bg_card1),
            contentDescription = null,
            modifier = Modifier
                .layoutId("image")
        )
        Button(
            onClick = {
                navController.popBackStack()
            },
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Gray
            ),
            elevation = ButtonDefaults.elevation(),
            modifier = Modifier
                .size(38.dp)
                .layoutId("btn_back")
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_new_24),
                    contentDescription = "Back Arrow",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    tint = colorResource(id = R.color.text_name_color)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .layoutId("divider")
        )
        /*Text(
            text = "Mandalorian",
            modifier = Modifier
                .layoutId("title")
                .wrapContentHeight(),
            color = motionColor("title", "textColor"),
            fontSize = motionFontSize("title", "textSize"),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )*/
        Box(
            Modifier
                .layoutId("content")
        ) {
            scrollableBody()
        }
    }
}

@Composable
private fun JsonConstraintSetStart() = ConstraintSet(
    """ {
	card: { 
		width: "spread",
        height: 120,
		start: ['parent', 'start', 16],
		end: ['parent', 'end', 16],
		top: ['parent', 'top', 56],
        custom: {
			radius: 12
		}
	},
    image: {
        bottom: ['card', 'bottom', 0],
		end: ['card', 'end', 0],
    },
    text1: {
		top: ['card', 'top', 0],
		start: ['card', 'start', 16],
		bottom: ['text2', 'top', 0],
		custom: {
			textColor: "#000000", 
			textSize: 18
		}
	},
    text2: {
		top: ['text1', 'bottom', 0],
		start: ['card', 'start', 16],
		bottom: ['card', 'bottom', 0],
		custom: {
			textColor: "#000000", 
			textSize: 14
		}
	},
    btn_back: {
		top: ['parent', 'top', 0],
		start: ['parent', 'start', 16],
        bottom: ['divider', 'top', 0],
	},
    divider: {
        width: "spread",
        height: 0.5,
        top: ['parent', 'top', 56],
        start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
    },
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['card', 'bottom', 16],
	}
} """
)

@Composable
private fun JsonConstraintSetEnd() = ConstraintSet(
    """ {
	card: { 
		width: "spread",
        height: 56,
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
        custom: {
			radius: 0
		}
	},

    image: {
        height: 56,
        bottom: ['card', 'bottom', 0],
        top: ['card', 'top', 0],
		end: ['card', 'end', 0],
    },
    text1: {
        width: "spread",
        height: 0,
		top: ['card', 'top', 0],
		start: ['card', 'start', 16],
		bottom: ['text2', 'top', 0],
		custom: {
			textColor: "#000000", 
			textSize: 0
		}
	},
    text2: {
        width: "spread",
        height: 28,
		top: ['parent', 'top', 0],
		start: ['btn_back', 'end', 16],
		bottom: ['divider', 'top', 0],
		custom: {
			textColor: "#000000", 
			textSize: 20
		}
	},
    btn_back: {
		top: ['parent', 'top', 0],
		start: ['parent', 'start', 16],
        bottom: ['divider', 'top', 0],
	},
    divider: {
        width: "spread",
        height: 0.5,
        top: ['parent', 'top', 56],
        start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
    },
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['card', 'bottom', 0],
	}
                  
} """
)
