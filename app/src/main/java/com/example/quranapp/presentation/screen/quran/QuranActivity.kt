package com.example.quranapp.presentation.screen.quran

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.OverScroller
import androidx.appcompat.app.AppCompatActivity
import com.example.quranapp.databinding.ActivityQuranBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuranActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        binding = ActivityQuranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pdfViewer.fromAsset("quran.pdf")
            .show()
    }
}