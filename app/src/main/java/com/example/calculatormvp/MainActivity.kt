package com.example.calculatormvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatormvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CalculatorView {
    private lateinit var presenter: CalculatorPresenter
    private lateinit var binding: ActivityMainBinding
    private var isLeftBracket = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val calculator = Calculator()
        presenter = CalculatorPresenter(this, calculator)
        setListeners()
    }

    private fun setListeners() {
        binding.clearAll.setOnClickListener { presenter.clearAll() }
        binding.one.setOnClickListener { presenter.number("1") }
        binding.two.setOnClickListener { presenter.number("2") }
        binding.three.setOnClickListener { presenter.number("3") }
        binding.four.setOnClickListener { presenter.number("4") }
        binding.five.setOnClickListener { presenter.number("5") }
        binding.six.setOnClickListener { presenter.number("6") }
        binding.seven.setOnClickListener { presenter.number("7") }
        binding.eight.setOnClickListener { presenter.number("8") }
        binding.nine.setOnClickListener { presenter.number("9") }
        binding.zero.setOnClickListener { presenter.number("0") }
        binding.dot.setOnClickListener { presenter.number(".") }
        binding.plus.setOnClickListener { presenter.operator("+") }
        binding.minus.setOnClickListener { presenter.operator("-") }
        binding.div.setOnClickListener { presenter.operator("/") }
        binding.multiply.setOnClickListener { presenter.operator("*") }
        binding.equals.setOnClickListener { presenter.equals() }
        binding.parentheses.setOnClickListener {
            presenter.parentheses(isLeftBracket)
            isLeftBracket = !isLeftBracket
        }
        binding.backSpace.setOnClickListener { presenter.backspace() }
    }

    override fun showResult(result: String) {
        binding.resultShow.text = result
    }

    override fun workSpace(value: String) {
        binding.workSpace.text = value
    }

    override fun showError() {
        binding.resultShow.text = (R.string.error.toString())
    }
}