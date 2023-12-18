package com.example.unit_converter.model

import com.example.unit_converter.model.strategies.CalculatorStrategy
import java.lang.IllegalArgumentException

object Calculator {

    private var calculatorStrategy: CalculatorStrategy? = null

    fun setCalculationStrategy(calculatorStrategy: CalculatorStrategy){
        this.calculatorStrategy = calculatorStrategy
    }

    fun calculate(value: Double): Double{

        if(calculatorStrategy == null) {
            throw IllegalArgumentException("Calculation Strategy is not set")
        }

        return calculatorStrategy!!.calculate(value)
    }

}