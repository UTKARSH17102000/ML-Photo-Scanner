package com.example.mlkitscanner.ImageLabel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.media.Image
import android.util.Log
import android.widget.Toast

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.mlkitscanner.BaseLensActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class ImageLabelAnalyzer: ImageAnalysis.Analyzer {
    val labeler = ImageLabeling.getClient(ImageLabelerOptions.Builder().setConfidenceThreshold(0.7F).build())
    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageproxy: ImageProxy) {
        Log.d("LABEL","image Analysed")
        val image: Image? = imageproxy.image
        imageproxy.image?.let {
            val inputimage = InputImage.fromMediaImage(
                it,
                imageproxy.imageInfo.rotationDegrees
            )


            labeler.process(inputimage)
                .addOnSuccessListener { labels ->

                    labels.forEach{
                        Log.d("LABEL", """
                           Format ${it.text}
                           Value ${it.confidence}
                        """.trimIndent())
                    }
                }.addOnFailureListener {
                    Log.e("LABEL", "Detection Failed", it)
                }.addOnCompleteListener {
                    //image?.close()
                    imageproxy.close()
                }
        }

            ?:imageproxy.close() // close if image not found either
    }

}