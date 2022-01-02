package com.example.mlkitscanner.ImageLabel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
import com.example.mlkitscanner.BaseLensActivity

class ImageLabelingActivity() : BaseLensActivity() {
    override val imageAnalyzer= ImageLabelAnalyzer()
    override fun StartScanner() {
        startImageLabeling()
    }
    private fun startImageLabeling(){
     imageAnalysis.setAnalyzer(
         ContextCompat.getMainExecutor(this),imageAnalyzer
     )
    }
    fun showtoast(message:String){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

}