package com.example.calculatormvp


class CalculatorPresenter(private val view: CalculatorView, private val calculator: Calculator) {
    private var workSpace = WorkSpace()
    private var clearAll = Calculator()

    fun number(value: String) {
        view.workSpace(workSpace.addToWorkSpace(value))
    }

    fun operator(operator: String) {
        val replacementOperator = when (operator) {
            "/" -> "÷"
            "*" -> "×"
            else -> operator
        }
        view.workSpace(workSpace.addToWorkSpace(replacementOperator))
    }

    fun parentheses(isLeft: Boolean) {
        val bracket = if (isLeft) "(" else ")"
        view.workSpace(workSpace.addToWorkSpace(bracket))
    }

    fun equals() {
        val expression = workSpace.getExpression().replace("÷", "/").replace("×", "*")
        try {
            val result = calculator.calculate(expression)
            view.showResult(result)
        } catch (e: Exception) {
            view.showError()
        }
    }

    fun clearAll() {
        clearAll = Calculator()
        workSpace = WorkSpace()
        view.workSpace("")
        view.showResult("")
    }

    fun backspace() {
        val currentExpression = workSpace.getExpression()
        if (currentExpression.isNotEmpty()) {
            val newExpression = currentExpression.substring(0, currentExpression.length - 1)
            workSpace.setExpression(newExpression)
            view.workSpace(newExpression)
        }
    }
}