/*
********************
Last names: Lopez, Rojo, Refuerzo
Language: Kotlin
Paradigm(s): object-oriented, imperative, functional, and procedural programming
********************
 */

package com.example.phtaxcalculator

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_introduction.*

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        introTitle.animate().apply{
            duration = 1000
            alpha(.5f)
            scaleXBy(.5f)
            scaleYBy(.5f)
        }.withEndAction {
            introTitle.animate().apply {
                duration = 1000
                alpha(1f)
                scaleXBy(-.5f)
                scaleYBy(-.5f)
            }
        }.start()

        Handler().postDelayed({
            val startActivity = Intent(this, CalculateActivity::class.java)
            startActivity(startActivity)
            finish()
        }, 4000)

    }

}