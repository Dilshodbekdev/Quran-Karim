package com.example.quranapp.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun showToast(context: Context,text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}