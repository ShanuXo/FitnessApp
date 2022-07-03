package com.example.fitness.ui.gym

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitness.R
import java.util.*


class GymFragment : Fragment() {
    private lateinit var addr:EditText
    private lateinit var textView: TextView
    private lateinit var btn1:Button

    private lateinit var gymViewModel: GymViewModel
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        gymViewModel = ViewModelProvider(this).get(GymViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gym, container, false)
        val textView: TextView = root.findViewById(R.id.text_gym)

       /* gymViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addr=view.findViewById(R.id.editTextAddress)
        btn1=view.findViewById(R.id.addressButton)
        btn1.setOnClickListener(){
            val uri: String = java.lang.String.format(
                Locale.ENGLISH,
                "geo:0,0?q="+Uri.encode(addr.text.toString())+"=gyms",
                addr
            )
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }

    }




}