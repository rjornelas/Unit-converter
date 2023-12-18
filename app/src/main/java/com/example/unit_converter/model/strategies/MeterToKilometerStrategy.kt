package com.example.unit_converter.model.strategies

class MeterToKilometerStrategy: CalculatorStrategy {
    override fun calculate(value: Double): Double {
        return value.div(1_000)
    }

    override fun getResultLabel(isPlural: Boolean): String = if (isPlural) "kilometers" else "kilometer"
}