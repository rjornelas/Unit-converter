package com.example.unit_converter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.unit_converter.model.CalculationStrategyHolder
import com.example.unit_converter.model.Calculator
import com.example.unit_converter.model.strategies.CalculatorStrategy
import com.example.unit_converter.model.strategies.KilometerToCentimerStrategy
import com.example.unit_converter.model.strategies.KilometerToMeterStrategy
import com.example.unit_converter.model.strategies.MeterToCentimeterStrategy
import com.example.unit_converter.model.strategies.MeterToKilometerStrategy
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var edtValue: EditText
    private lateinit var spConversions: Spinner

    private val supportedCalculatorStrategies = arrayOf(
        CalculationStrategyHolder("Kilometer to centimeter", KilometerToCentimerStrategy()),
        CalculationStrategyHolder("Kilometer to meter", KilometerToMeterStrategy()),
        CalculationStrategyHolder("Meter to centimeter", MeterToCentimeterStrategy()),
        CalculationStrategyHolder("Meter to kilometer", MeterToKilometerStrategy())
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var value = 0.0
        var position = 0

        savedInstanceState?.let{
            value = it.getDouble("VALUE")
            position = it.getInt("POSITION")
        }


        initUi()
        setUi(value, position)
        setActions()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putDouble("VALUE", edtValue.text.toString().toDouble())
        outState.putInt("POSITION", spConversions.selectedItemPosition)
    }

    private fun setActions() {
        val btnConvert: Button = findViewById(R.id.btnConvert)

        btnConvert.setOnClickListener{
            try {
                val value = edtValue.text.toString().toDouble()
                val selectItemPosition = spConversions.selectedItemPosition
                val calculatorStrategy = supportedCalculatorStrategies[selectItemPosition].calculatorStrategy
                Calculator.setCalculationStrategy(calculatorStrategy)
                val result = Calculator.calculate(value)

                showResult(result, calculatorStrategy)

            }catch (e: NumberFormatException){
                edtValue.error = "Invalid value"
                edtValue.requestFocus()
            }
        }

        val btnClear: Button = findViewById(R.id.btnClear)
        btnClear.setOnClickListener{
            edtValue.setText("")
            edtValue.error = null
            spConversions.setSelection(0)
        }
    }

    private fun showResult(
        result: Double,
        calculatorStrategy: CalculatorStrategy
    ) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("Result", result)
        intent.putExtra("Label", calculatorStrategy.getResultLabel(checkPlural(result)))
        startActivity(intent)
    }

    private fun checkPlural(result: Double) = result > 1

    private fun setUi(value: Double, position: Int) {

        edtValue.setText(value.toString())

        val spAdapter = ArrayAdapter(this, R.layout.res_spinner_item, getSpinnerData())
        spAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spConversions.adapter = spAdapter
        spConversions.setSelection(position)
    }

    private fun initUi() {
        spConversions = findViewById(R.id.spConversions)
        edtValue = findViewById(R.id.edtValue)
    }

    private fun getSpinnerData(): MutableList<String> {
        val spinnerData = mutableListOf<String>()
        supportedCalculatorStrategies.forEach {
            spinnerData.add(it.name)
        }
        return spinnerData
    }
}