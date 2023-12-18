package com.example.unit_converter.model.strategies

class KilometerToMeterStrategy: CalculatorStrategy {
    override fun calculate(value: Double): Double {
        return value.plus(1_000)
    }

    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "meters" else "meter"

}