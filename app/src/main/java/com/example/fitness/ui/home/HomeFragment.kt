package com.example.fitness.ui.home

import android.os.Bundle
import android.os.ParcelFileDescriptor.MODE_READ_ONLY
import android.os.ParcelFileDescriptor.open
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitness.R
import java.io.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var b1: Button
    private lateinit var food:EditText
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        food=view.findViewById(R.id.food_name)
        var food_name: Editable? = food.text
        b1=view.findViewById(R.id.b1)
        b1.setOnClickListener() {
            val minput = InputStreamReader(requireContext().assets.open("food_calories.csv"))

            val reader = BufferedReader(minput)
            var line: String?
            var displayData: String = ""

            while (reader.readLine().also { line = it } != null) {
                val row: List<String> = line!!.split(",")
                var txtData = view.findViewById(R.id.food_details) as TextView
                txtData.text = row[0]
                if (row[0].equals(food_name.toString(),true) )
                {
                    displayData = displayData + row[0] + "\t" + row[1] + "\n"
                }

            }


            var txtData = view.findViewById(R.id.food_details) as TextView
            txtData.text = displayData
        }


    }

}