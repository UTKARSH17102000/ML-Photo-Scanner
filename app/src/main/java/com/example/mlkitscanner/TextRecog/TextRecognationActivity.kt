package com.example.mlkitscanner.TextRecog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
import com.example.mlkitscanner.BaseLensActivity

class TextRecognationActivity : BaseLensActivity() {
    override val imageAnalyzer =  TextAnalyzer()


    override fun StartScanner() {
startTextRecognition()
    }

    private fun startTextRecognition(){
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),imageAnalyzer
        )
    }


}