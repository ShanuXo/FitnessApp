package com.example.fitness.ui.gallery

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitness.Activity2
import com.example.fitness.R



class GalleryFragment : Fragment() {
    companion object{
        private  const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }
    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var btn:Button
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }
    lateinit var btn_camera: Button
    lateinit var iv_image: AppCompatImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_camera=view.findViewById(R.id.btn_camera)
        btn_camera.setOnClickListener(){
            if(context?.let { it1 ->
                    ContextCompat.checkSelfPermission(
                        it1,
                        android.Manifest.permission.CAMERA
                    )
                } == PackageManager.PERMISSION_GRANTED
            ){
                val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, Activity2.CAMERA_REQUEST_CODE)
            }
            else
            {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(android.Manifest.permission.CAMERA),
                    Activity2.CAMERA_PERMISSION_CODE
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
        if(requestCode == Activity2.CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, Activity2.CAMERA_REQUEST_CODE)
            }
            else{
                Toast.makeText(
                    context,
                    "OOPS U DENIED CAMERA PERMIT , YOU CAN ALLLOW IN SETTINGS",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == Activity2.CAMERA_REQUEST_CODE){
                val thumbnail: Bitmap = data!!.extras!!.get("data") as Bitmap
                iv_image= view?.findViewById(R.id.iv_image)!!
                iv_image.setImageBitmap(thumbnail )
            }
        }
    }



}
