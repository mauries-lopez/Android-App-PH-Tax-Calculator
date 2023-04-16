/*
********************
Last names: Lopez, Rojo, Refuerzo
Language: Kotlin
Paradigm(s): object-oriented, imperative, functional, and procedural programming
********************
 */

package com.example.phtaxcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calculate.*

class CalculateActivity : AppCompatActivity() {

    private lateinit var userMonthlyIncome: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)

        userMonthlyIncome = findViewById(R.id.monthlyIncomeInput)

        try {
            calculateButton.setOnClickListener {
                val monthlyIncome = userMonthlyIncome.text.toString().toDoubleOrNull()
                if (monthlyIncome == null) {
                    Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
                } else {
                val nextActivity = Intent(this, MainActivity::class.java)
                    nextActivity.putExtra("monthlyIncome", monthlyIncome)
                    startActivity(nextActivity)
                }
            }
        } catch (e: Exception) {
            Log.e("TAG", "Error occurred", e)
        }

    }

}
