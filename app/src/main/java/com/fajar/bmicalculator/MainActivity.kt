package com.fajar.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtHeight: EditText
    private lateinit var edtWeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    private lateinit var tvIndex: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtHeight = findViewById(R.id.edt_height)
        edtWeight = findViewById(R.id.edt_weight)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)
        tvIndex = findViewById(R.id.tvIndex)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onClick(v: View){
        if(v.id == R.id.btn_calculate){
            val inputWeight = edtWeight.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()


            var isEmptyFields = false

            //validation
            if(inputHeight.isEmpty()){
                isEmptyFields = true
                edtHeight.error = "Fill your data first"
            }
            if(inputWeight.isEmpty()){
                isEmptyFields = true
                edtWeight.error = "Fill your data first"
            }

            //after validated, display result
            if(!isEmptyFields){
                val bmi = inputWeight.toDouble() / (inputHeight.toDouble() * inputHeight.toDouble())
                tvResult.text = bmi.toString()

                if(bmi <= 18.5){
                    val index = "Underweight"
                    tvIndex.text = index
                }
                if(bmi <= 25){
                    val index = "Normal"
                    tvIndex.text = index
                }
                if(bmi <= 30){
                    val index = "Overweight"
                    tvIndex.text = index
                }
                if(bmi > 30){
                    val index = "Obesity"
                    tvIndex.text = index
                }

            }


        }
    }

}