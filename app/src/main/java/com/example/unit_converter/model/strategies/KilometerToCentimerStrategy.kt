package com.example.unit_converter.model.strategies

class KilometerToCentimerStrategy: CalculatorStrategy {
    override fun calculate(value: Double): Double {
        return value.plus(100_000)
    }

    override fun getResultLabel(isPlural: Boolean): String = if(isPlural) "centimeters" else "centimeter"
}