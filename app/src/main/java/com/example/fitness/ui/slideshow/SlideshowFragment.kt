package com.example.fitness.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitness.R

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private lateinit var calculate_btn:Button
    private lateinit var etHeight:TextView
    private lateinit var etWeight:TextView
    private lateinit var bmi:TextView
    private lateinit var status:TextView
    private lateinit var bmi_tv:TextView
    private lateinit var ReCalculate:Button

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProvider(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        /*//val textView: TextView = root.findViewById(R.id.text_slideshow)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculate_btn=view.findViewById(R.id.calculate_btn)
        etHeight=view.findViewById(R.id.etHeight)
        etWeight=view.findViewById(R.id.etWeight)
        bmi=view.findViewById(R.id.bmi)
        status=view.findViewById(R.id.status)
        bmi_tv=view.findViewById(R.id.bmi_tv)
        ReCalculate=view.findViewById(R.id.ReCalculate)
        calculate_btn.setOnClickListener {

            // Check if the height EditText and Weight EditText are not empty
            if (etHeight.text.isNotEmpty() && etWeight.text.isNotEmpty()) {
                val height = (etHeight.text.toString()).toInt()
                val weight = (etWeight.text.toString()).toInt()

                // calculateBMI will return BMI
                val BMI = calculateBMI(height, weight)

                bmi.text = BMI.toString()
                bmi.visibility = View.VISIBLE

                // update the status text as per the bmi conditions
                if (BMI < 18.5) {
                    status.text = "Under Weight"
                } else if (BMI >= 18.5 && BMI < 24.9) {
                    status.text = "Healthy"
                } else if (BMI >= 24.9 && BMI < 30) {
                    status.text = "Overweight"
                } else if (BMI >= 30) {
                    status.text = "Suffering from Obesity"
                }

                bmi_tv.visibility = View.VISIBLE
                status.visibility = View.VISIBLE

                ReCalculate.visibility = View.VISIBLE
                calculate_btn.visibility = View.GONE

            }

            // when either Weight EditText or
            // height EditText have null value
            // we will display toast.
            else {
               // Toast toast=Toast.makeText(this, "please enter the valid height and weight", Toast.LENGTH_SHORT)
                val toast:Toast
                toast = Toast.makeText(context, "please enter the valid height and weight",Toast.LENGTH_SHORT);
                toast.show();

            }
        }

        ReCalculate.setOnClickListener {
            ResetEverything()
        }

    }
    private fun ResetEverything() {

        calculate_btn.visibility = View.VISIBLE
        ReCalculate.visibility = View.GONE

        etHeight.text=""
        etWeight.text=""
        status.text = " "
        bmi.text = " "
        bmi_tv.visibility = View.GONE
    }
    // Function for calculating BMI
    private fun calculateBMI(height: Int, weight: Int): Float {

        val Height_in_metre = height.toFloat() / 100
        val BMI = weight.toFloat() / (Height_in_metre * Height_in_metre)

        return BMI
    }
}