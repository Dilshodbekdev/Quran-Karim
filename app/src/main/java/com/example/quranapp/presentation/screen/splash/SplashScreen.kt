package com.example.quranapp.presentation.screen.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quranapp.R
import kotlinx.coroutines.delay

/*val fontFamily = FontFamily(
    Font(R.font.lexend_black, FontWeight.Thin),
    Font(R.font.lexend_light, FontWeight.Thin),
    Font(R.font.lexend_regular, FontWeight.Thin),
    Font(R.font.lexend_medium, FontWeight.Thin),
    Font(R.font.lexend_semibold, FontWeight.Thin),
    Font(R.font.lexend_bold, FontWeight.Thin),
    Font(R.font.lexend_extrabold, FontWeight.Thin),
    Font(R.font.lexend_extralight, FontWeight.Thin),
)*/

@Preview
@Composable
fun SplashScreen(navController: NavController = rememberNavController()) {

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(2000L)
        navController.navigate("main_screen")
    }

    val constraints = ConstraintSet {
        val title = createRefFor("title")
        val description = createRefFor("description")
        val card = createRefFor("cv")
        val card1 = createRefFor("cv1")

        constrain(title) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(card.top)
        }

        constrain(description) {
            top.linkTo(title.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(card.top)
        }

        constrain(card) {
            top.linkTo(title.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }

        constrain(card1) {
            top.linkTo(card.bottom)
            bottom.linkTo(card.bottom)
            start.linkTo(card.start)
            end.linkTo(card.end)
        }
    }

    ConstraintLayout(
        constraints,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Text(
            text = "Quran App",
            color = Color(R.color.main_color),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.layoutId("title")
        )

        Text(
            text = "Learn Quran and\nRecite once everyday",
            color = Color.Black,
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.layoutId("description")
        )

        Card(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(0.8f)
                .layoutId("cv")
                .scale(scale.value),
            backgroundColor = Color(R.color.main_color),
            shape = RoundedCornerShape(22.dp),
            elevation = 0.dp
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_card),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

       /* Card(
            modifier = Modifier
                .fillMaxHeight(0.06f)
                .fillMaxWidth(0.5f)
                .layoutId("cv1"),
            backgroundColor = Color(R.color.main_color),
            shape = RoundedCornerShape(30.dp),
            elevation = 1.dp,
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = "Get Started",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }*/
    }

    /* Column(modifier = Modifier.fillMaxSize()) {
         Text(
             text = "Quran App",
             color = Color(R.color.main_color),
             style = TextStyle.Default,
             fontSize = 30.sp
         )

         Text(
             text = "Learn Quran and Recite\nonce everyday",
             color = Color(R.color.main_color),
             style = TextStyle.Default,
             fontSize = 22.sp
         )
     }*/
}