package com.example.quranapp.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.quranapp.R

fun showToast(context: Context,text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

val fonts = FontFamily(
    Font(R.font.poppins_black, weight = FontWeight.Black),
    Font(R.font.poppins_bold, weight = FontWeight.Bold),
    Font(R.font.poppins_extra_bold, weight = FontWeight.ExtraBold),
    Font(R.font.poppins_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.poppins_light, weight = FontWeight.Light),
    Font(R.font.poppins_extra_light, weight = FontWeight.ExtraLight),
    Font(R.font.poppins_thin, weight = FontWeight.Thin),
    Font(R.font.poppins_medium, weight = FontWeight.Medium),
    Font(R.font.poppins_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
)