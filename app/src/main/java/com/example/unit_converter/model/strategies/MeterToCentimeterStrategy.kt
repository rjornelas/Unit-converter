package com.example.unit_converter.model.strategies

class MeterToCentimeterStrategy: CalculatorStrategy {
    override fun calculate(value: Double): Double {
        return value.plus(100)
    }

    override fun getResultLabel(isPlural: Boolean): String = if(isPlural) "centimeters" else "centimeter"
}