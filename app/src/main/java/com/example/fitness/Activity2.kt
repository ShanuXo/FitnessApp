package com.example.fitness

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Activity2 : AppCompatActivity() {
    companion object{
        const val CAMERA_PERMISSION_CODE = 1
        const val CAMERA_REQUEST_CODE = 2
    }
    lateinit var btn_camera: Button
    lateinit var iv_image: AppCompatImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gallery)

        btn_camera=findViewById(R.id.btn_camera)
        btn_camera.setOnClickListener(){
            if(ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
                )== PackageManager.PERMISSION_GRANTED
            ){
                val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }
            else
            {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }
            else{
                Toast.makeText(
                    this,
                    "OOPS U DENIED CAMERA PERMIT , YOU CAN ALLLOW IN SETTINGS",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode ==  CAMERA_REQUEST_CODE){
                val thumbnail: Bitmap = data!!.extras!!.get("data") as Bitmap
                iv_image=findViewById(R.id.iv_image)
                iv_image.setImageBitmap(thumbnail)
            }
        }
    }


}