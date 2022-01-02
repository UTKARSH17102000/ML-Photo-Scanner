package com.example.mlkitscanner.FaceDetection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
import com.example.mlkitscanner.BaseLensActivity

class FaceDetectActivity() : BaseLensActivity() {
    override val imageAnalyzer = FaceDetectAnalyzer()

    override fun StartScanner() {
        scanFace()
    }


    private fun scanFace(){
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),imageAnalyzer
        )
    }
}