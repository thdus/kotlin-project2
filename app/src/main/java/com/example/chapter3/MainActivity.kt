package com.example.chapter3

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.chapter3.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var inputNumber : Int =0
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditText = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
        val swapImageButton = binding.swapImageButton



        inputEditText.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()){
                0
            } else {
                text.toString().toInt()
            }
            if(cmToM) {
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                outputTextView.text = inputNumber.times(100).toString()
            }

        }

        swapImageButton.setOnClickListener {
            cmToM = cmToM.not()
            if(cmToM) {
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text=inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle){
        cmToM= savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if(cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if(cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }

}



