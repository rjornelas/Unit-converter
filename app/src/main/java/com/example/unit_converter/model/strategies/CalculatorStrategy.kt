package com.example.unit_converter.model.strategies

interface CalculatorStrategy {

    fun calculate(value: Double): Double

    fun getResultLabel(isPlural: Boolean): String
}