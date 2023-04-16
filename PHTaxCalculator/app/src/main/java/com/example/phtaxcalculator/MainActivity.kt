/*
********************
Last names: Lopez, Rojo, Refuerzo
Language: Kotlin
Paradigm(s): object-oriented, imperative, functional, and procedural programming
********************
 */

package com.example.phtaxcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var sssValue : TextView
    private lateinit var phValue : TextView
    private lateinit var pagibigValue : TextView
    private lateinit var totalContriValue : TextView
    private lateinit var incomeTaxValue : TextView
    private lateinit var netpayTaxValue : TextView
    private lateinit var totalDeducValue : TextView
    private lateinit var netPayAfterDeducValue : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sssValue = findViewById(R.id.sssValue)
        phValue = findViewById(R.id.phValue)
        pagibigValue = findViewById(R.id.pagibigValue)
        totalContriValue = findViewById(R.id.totalContriValue)
        incomeTaxValue = findViewById(R.id.incomeTaxValue)
        netpayTaxValue = findViewById(R.id.netpayTaxValue)
        totalDeducValue = findViewById(R.id.totalDeducValue)
        netPayAfterDeducValue = findViewById(R.id.netPayAfterDeducValue)

        val monthlyIncome = intent.getDoubleExtra("monthlyIncome", 0.0)

        val totalContributions = calculateMonthlyContri(monthlyIncome)
        val taxableIncome = monthlyIncome - totalContributions
        val incomeTax = calculateIncomeTax(taxableIncome)
        calculateNetPayAfterTax(monthlyIncome, incomeTax)
        val totalDeductions = calculateTotalDeductions(incomeTax, totalContributions)
        calculateNetPay(monthlyIncome, totalDeductions)
        Toast.makeText(this, "Calculation completed", Toast.LENGTH_SHORT).show()

    }

    private fun calculateSSS (monthlyIncome: Double, text: TextView ): Double {

        val range = arrayOf(
            0.00..3249.99 to 135.00,
            3250.00..3749.99 to 157.50,
            3750.00..4249.99 to 180.00,
            4250.00..4749.99 to 202.50,
            4750.00..5249.99 to 225.00,
            5250.00..5749.99 to 247.50,
            5750.00..6249.99 to 270.00,
            6250.00..6749.99 to 292.50,
            6750.00..7249.99 to 315.00,
            7250.00..7749.99 to 337.50,
            7750.00..8249.99 to 360.00,
            8250.00..8749.99 to 382.50,
            8750.00..9249.99 to 405.00,
            9250.00..9749.99 to 427.50,
            9750.00..10249.99 to 450.00,
            10250.00..10749.99 to 472.50,
            10750.00..11249.99 to 495.00,
            11250.00..11749.99 to 517.50,
            11750.00..12249.99 to 540.00,
            12250.00..12749.99 to 562.50,
            12750.00..13249.99 to 585.00,
            13250.00..13749.99 to 607.50,
            13750.00..14249.99 to 630.00,
            14250.00..14749.99 to 652.50,
            14750.00..15249.99 to 675.00,
            15250.00..15749.99 to 697.50,
            15750.00..16249.99 to 720.00,
            16250.00..16749.99 to 742.50,
            16750.00..17249.99 to 765.00,
            17250.00..17749.99 to 787.50,
            17750.00..18249.99 to 810.00,
            18250.00..18749.99 to 832.50,
            18750.00..19249.99 to 855.00,
            19250.00..19749.99 to 877.50,
            19750.00..20249.99 to 900.00,
            20250.00..20749.99 to 922.50,
            20750.00..21249.99 to 945.00,
            21250.00..21749.99 to 967.50,
            21750.00..22249.99 to 990.00,
            22250.00..22749.99 to 1012.50,
            22750.00..23249.99 to 1035.00,
            23250.00..23749.99 to 1057.50,
            23750.00..24249.99 to 1080.00,
            24250.00..24749.99 to 1102.50,
            24750.00..999999.99 to 1125.00
        )

        var ans = 0.0

        for ( (r,v) in range ){
            if (r.contains(monthlyIncome)){
                text.text = String.format("₱"+"%.2f",v)
                ans = v
            }
        }

        return ans
    }

    private fun calculatePhilHealth (monthlyIncome: Double, text: TextView ): Double {
        val ans : Double = ( monthlyIncome * 0.04 ) / 2
        text.text = String.format("₱"+"%.2f", ans)
        return ans
    }

    private fun calculatePagibig (monthlyIncome: Double, text: TextView): Double {

        val range = arrayOf(
            0.00..1500.00 to 0.01,
            1501.00..4999.99 to 0.02,
            5000.00..9999999.99 to 100.00
        )

        var ans = 0.0

        for ( (r,v) in range ){
            if ( r.contains(monthlyIncome) ){
                if ( v.equals(100.00) ){
                    text.text = "₱100.00"
                }
                else{
                    val temp = monthlyIncome * v
                    text.text = String.format("₱"+ "%.2f", temp)
                    ans = temp
                }
            }
        }

        return ans

    }

    private fun calculateMonthlyContri(monthlyIncome: Double) : Double{
        val sssAns = calculateSSS(monthlyIncome, sssValue)
        val phAns = calculatePhilHealth(monthlyIncome, phValue)
        val pagibigAns = calculatePagibig(monthlyIncome, pagibigValue)
        val ans = sssAns + phAns + pagibigAns
        totalContriValue.text =  String.format("₱"+"%.2f",ans)
        return sssAns + phAns + pagibigAns
    }

    private fun calculateIncomeTax(taxableIncome: Double) : Double {

        //Range to withHoldingTax
        val range1 = arrayOf(
            0.00..20832.00 to 0.00,
            20833.00..33332.00 to 0.00,
            33333.00..66666.00 to 2500.00,
            66667.00..166666.00 to 10833.33,
            166667.00..666666.00 to 40833.33,
            666667.00..999999999.99 to 200833.33
        )

        //Range to percent
        val range2 = arrayOf(
            0.00..20832.00 to 0.0,
            20833.00..33332.00 to 0.2,
            33333.00..66666.00 to 0.25,
            66667.00..166666.00 to 0.30,
            166667.00..666666.00 to 0.32,
            666667.00..999999999.99 to 0.35
        )

        //Range to minimum
        val range3 = arrayOf(
            0.00..20832.00 to 0.0,
            20833.00..33332.00 to 20833.00,
            33333.00..66666.00 to 33333.00,
            66667.00..166666.00 to 66667.00,
            166667.00..666666.00 to 166667.00,
            666667.00..999999999.99 to 666667.00
        )

        var withHoldingTax = 0.0
        var percentTax = 0.0
        var compensationLevel = 0.0

        for ( (r,v) in range1 ){
            if ( r.contains(taxableIncome) ){
                withHoldingTax = v
            }
        }

        for ( (r,v) in range2 ){
            if ( r.contains(taxableIncome) ){
                percentTax = v
            }
        }

        for ( (r,v) in range3 ){
            if ( r.contains(taxableIncome) ){
                compensationLevel = v
            }
        }



        val tempIncomeTax = (taxableIncome - compensationLevel) * percentTax
        val realIncomeTax = (withHoldingTax + tempIncomeTax)

        if ( taxableIncome < 20833.00 ){
            incomeTaxValue.setText(R.string.tax_exempted)
        }
        else{
            incomeTaxValue.text = String.format("₱"+"%.2f",realIncomeTax)
            return realIncomeTax
        }

        return 0.0
    }

    private fun calculateNetPayAfterTax( monthlyIncome: Double, incomeTax: Double ){
        val ans = monthlyIncome - incomeTax
        netpayTaxValue.text = String.format("₱"+"%.2f",ans)
    }

    private fun calculateTotalDeductions(incomeTax: Double, totalContributions: Double): Double {
        val ans = incomeTax + totalContributions
        totalDeducValue.text = String.format("₱"+"%.2f",ans)
        return incomeTax + totalContributions
    }

    private fun calculateNetPay(monthlyIncome: Double, totalDeductions: Double){
        val ans = monthlyIncome - totalDeductions
        netPayAfterDeducValue.text = String.format("₱"+"%.2f",ans)
    }

}