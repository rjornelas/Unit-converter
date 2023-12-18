package com.example.unit_converter.model

import com.example.unit_converter.model.strategies.CalculatorStrategy

data class CalculationStrategyHolder(
    val name: String,
    val calculatorStrategy: CalculatorStrategy
)
