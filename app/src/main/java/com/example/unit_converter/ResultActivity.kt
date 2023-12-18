package com.example.unit_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = intent.getDoubleExtra("Result", 0.0)
        val label = intent.getStringExtra("Label")

        val tvValue: TextView = findViewById(R.id.tvValue)
        tvValue.text = result.toString()

        val tvValueLabel: TextView = findViewById(R.id.tvValueLabel)
        tvValueLabel.text = label

        val btnClose: Button =  findViewById(R.id.btnReturn)
        btnClose.setOnClickListener{
            finish()
        }

    }
}