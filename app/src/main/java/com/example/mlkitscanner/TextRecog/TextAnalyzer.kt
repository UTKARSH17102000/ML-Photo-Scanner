package com.example.mlkitscanner.TextRecog

import android.annotation.SuppressLint
import android.media.Image
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition

class TextAnalyzer : ImageAnalysis.Analyzer {

    val recognizer = TextRecognition.getClient()
    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageproxy: ImageProxy) {

        Log.d("TEXT","image Analysed")
        val image: Image? = imageproxy.image
        imageproxy.image?.let {
            val inputimage = InputImage.fromMediaImage(
                it,
                imageproxy.imageInfo.rotationDegrees
            )


            recognizer.process(inputimage)
                .addOnSuccessListener {
                    it.textBlocks.forEach {
                        Log.d(
                            "TEXT", """
                            Format = ${it.lines.joinToString( "\n"){it.text}}
                            """.trimIndent()
                        )
                    }
                }.addOnFailureListener {
                    Log.e("TEXT", "Detection Failed", it)
                }.addOnCompleteListener {
                    //image?.close()
                    imageproxy.close()
                }
        }

            ?:imageproxy.close() // close if image not found either
    }

}