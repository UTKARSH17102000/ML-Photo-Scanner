package com.example.mlkitscanner.Barcode

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.camera.core.*
import androidx.core.content.ContextCompat
import com.example.mlkitscanner.BaseLensActivity
import com.example.mlkitscanner.R
import kotlinx.android.synthetic.main.activity_lens.*

class BarcodeActivity : BaseLensActivity() {

    override   val imageAnalyzer  = BarcodeAnalyzer()
    override fun StartScanner() {
        scanBarcode()
    }


    private fun scanBarcode() {
        imageAnalysis
            .setAnalyzer(
                ContextCompat.getMainExecutor(this),imageAnalyzer
            )

    }
}