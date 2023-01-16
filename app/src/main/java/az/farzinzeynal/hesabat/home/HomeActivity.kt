package az.farzinzeynal.hesabat.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import az.farzinzeynal.hesabat.R
import az.farzinzeynal.hesabat.history.HistoryActivity
import az.farzinzeynal.hesabat.input.StartNewReportDialog
import az.farzinzeynal.hesabat.input.ValueInputDialog

class HomeActivity : AppCompatActivity() {



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIncreaseRuble = findViewById<Button>(R.id.btnIncreaseRuble)
        val btnDereaseRuble = findViewById<Button>(R.id.btnDereaseRuble)
        val btnRubbleHistory = findViewById<Button>(R.id.btnRubbleHistory)

        val btnIncreaseDollar = findViewById<Button>(R.id.btnIncreaseDollar)
        val btnDereaseDollar = findViewById<Button>(R.id.btnDereaseDollar)
        val btnDollarHistory = findViewById<Button>(R.id.btnDollarHistory)

        val btnStartNew = findViewById<Button>(R.id.btnStartNewReport)

        val rubleValue = findViewById<TextView>(R.id.rubleValue)
        val usaDollarValue = findViewById<TextView>(R.id.usaDollarValue)

        btnDollarHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        btnRubbleHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        btnStartNew.setOnClickListener {
            val dialog = StartNewReportDialog()
            dialog.show(supportFragmentManager,"START_REPORT")
        }

        btnIncreaseDollar.setOnClickListener {
            val dialog = ValueInputDialog(applicationContext,"DOLLAR","PLUS")
            dialog.show(supportFragmentManager,"INCRASE_DOLLAR")
        }

        btnIncreaseRuble.setOnClickListener {
            val dialog = ValueInputDialog(applicationContext, "RUBLE", "PLUS")
            dialog.show(supportFragmentManager,"INCRASE_RUBLE")
        }

        btnDereaseDollar.setOnClickListener {
            val dialog = ValueInputDialog(applicationContext, "DOLLAR", "MINUS")
            dialog.show(supportFragmentManager,"DECRASE_DOLLAR")
        }

        btnDereaseRuble.setOnClickListener {
            val dialog = ValueInputDialog(applicationContext, "RUBLE","MINUS")
            dialog.show(supportFragmentManager,"DECRASE_RUBLE")
        }
    }
}