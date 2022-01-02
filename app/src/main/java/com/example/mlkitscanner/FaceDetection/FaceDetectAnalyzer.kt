package com.example.mlkitscanner.FaceDetection

import android.annotation.SuppressLint
import android.media.Image
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

class FaceDetectAnalyzer: ImageAnalysis.Analyzer {

    val detector  = FaceDetection.getClient(FaceDetectorOptions
        .Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
        .build())
    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageproxy: ImageProxy) {
        Log.d("FaceDetect","image Analysed")
        val image: Image? = imageproxy.image
        imageproxy.image?.let {
            val inputimage = InputImage.fromMediaImage(
                it,
                imageproxy.imageInfo.rotationDegrees
            )


            detector.process(inputimage)
                .addOnSuccessListener { faces ->
                    Log.d("FACEDETECT", "FACES = ${faces.size}")
                    faces.forEach{
                        Log.d("FACEDETECT", """
                           left Eye ${it.leftEyeOpenProbability}
                           right Eye ${it.rightEyeOpenProbability}
                           smile ${it.smilingProbability}
                        """.trimIndent())
                    }
                }.addOnFailureListener {
                    Log.e("FACEDETECT", "Detection Failed", it)
                }.addOnCompleteListener {
                    //image?.close()
                    imageproxy.close()
                }
        }

            ?:imageproxy.close() // close if image not found either
    }

}