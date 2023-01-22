package com.example.calculatormvp

interface CalculatorView {

    fun showResult(result: String)
    fun showError()
    fun workSpace(value: String)
}