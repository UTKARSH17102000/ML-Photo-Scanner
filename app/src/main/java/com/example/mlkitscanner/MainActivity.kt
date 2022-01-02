package com.example.mlkitscanner

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.mlkitscanner.Barcode.BarcodeActivity
import com.example.mlkitscanner.FaceDetection.FaceDetectActivity
import com.example.mlkitscanner.ImageLabel.ImageLabelingActivity
import com.example.mlkitscanner.TextRecog.TextRecognationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        @JvmStatic val PHOTO_REQ_CODE = 234
        @JvmStatic val EXTRA_DATA = "data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnTakePhoto.setOnClickListener {

            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePhotoIntent, PHOTO_REQ_CODE)
        }

        btnCameraActivity.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
        btnBarcodeActivity.setOnClickListener {
            startActivity(Intent(this, BarcodeActivity::class.java))
        }
        btnFaceDetectActivity.setOnClickListener {
            startActivity(Intent(this, FaceDetectActivity::class.java))
        }
        btnImageLabelActivity.setOnClickListener {
            startActivity(Intent(this, ImageLabelingActivity::class.java))
        }
        btnTextRecogActivity.setOnClickListener {
            startActivity(Intent(this, TextRecognationActivity::class.java))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PHOTO_REQ_CODE){
            val bitmap:Bitmap =data?.extras?.get(EXTRA_DATA) as Bitmap
            imgThumb.setImageBitmap(bitmap)
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}