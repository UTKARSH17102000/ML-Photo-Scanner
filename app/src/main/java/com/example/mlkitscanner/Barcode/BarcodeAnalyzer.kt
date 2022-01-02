package com.example.mlkitscanner.Barcode


import android.annotation.SuppressLint
import android.media.Image
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer: ImageAnalysis.Analyzer {

    val scanner = BarcodeScanning.getClient()
    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageproxy: ImageProxy) {
        Log.d("BARCODE","image Analysed")
        val image: Image? = imageproxy.image
        imageproxy.image?.let {
            val inputimage = InputImage.fromMediaImage(
                it,
                imageproxy.imageInfo.rotationDegrees
            )


            scanner.process(inputimage)
                .addOnSuccessListener {
                    it.forEach {
                        Log.d(
                            "BARCODE", """
                            Format = ${it.format}
                            Value = ${it.rawValue}
                            """.trimIndent()
                        )
                    }
                }.addOnFailureListener {
                    Log.e("BARCODE", "Detection Failed", it)
                }.addOnCompleteListener {
                    //image?.close()
                    imageproxy.close()
                }
        }

            ?:imageproxy.close() // close if image not found either
    }
}