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
import az.farzinzeynal.hesabat.room_database.MainRoomDB
import az.farzinzeynal.hesabat.room_database.repository.DbRepository
import az.farzinzeynal.hesabat.shared_prefs.MainSharedPrefs
import az.farzinzeynal.hesabat.shared_prefs.SharedPrefNames
import az.farzinzeynal.hesabat.shared_prefs.SharedTypes
import az.farzinzeynal.hesabat.util.Extentions.formatValue

class HomeActivity : AppCompatActivity() {



    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roomDao = MainRoomDB.getInstance(applicationContext).valuesDao()
        val databaseRepository = DbRepository(roomDao)

        val btnIncreaseRuble = findViewById<Button>(R.id.btnIncreaseRuble)
        val btnDereaseRuble = findViewById<Button>(R.id.btnDereaseRuble)
        val btnRubbleHistory = findViewById<Button>(R.id.btnRubbleHistory)

        val btnIncreaseDollar = findViewById<Button>(R.id.btnIncreaseDollar)
        val btnDereaseDollar = findViewById<Button>(R.id.btnDereaseDollar)
        val btnDollarHistory = findViewById<Button>(R.id.btnDollarHistory)

        val btnStartNew = findViewById<Button>(R.id.btnStartNewReport)

        val rubleValue = findViewById<TextView>(R.id.rubleValue)
        val usaDollarValue = findViewById<TextView>(R.id.usaDollarValue)

        val finalDollarList = databaseRepository.getDollarValues()
        val finalRubbleList = databaseRepository.getRubleValues()

        val isNewReport = MainSharedPrefs(this, SharedTypes.USERDATA).get(SharedPrefNames.IS_NEW_REPORT,false) ?: false
        if (!isNewReport){
            val finalDollarValuePlus = finalDollarList.filter { it.operationType=="PLUS" }.sumOf { it.dollarValue }
            val finalDollarValueMinus = finalDollarList.filter { it.operationType=="MINUS" }.sumOf { it.dollarValue }
            val finalRubleValuePlus = finalRubbleList.filter { it.operationType=="PLUS" }.sumOf { it.rubleValue }
            val finalRubleValueMinus = finalRubbleList.filter { it.operationType=="MINUS" }.sumOf { it.rubleValue }
            val finalDollar = finalDollarValuePlus-finalDollarValueMinus
            usaDollarValue.text = finalDollar.formatValue()+" $"
            val finalRuble = finalRubleValuePlus-finalRubleValueMinus
            rubleValue.text = finalRuble.formatValue()+" ₽"
        }


        btnDollarHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("valueType","DOLLAR")
            startActivity(intent)
        }

        btnRubbleHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("valueType","RUBLE")
            startActivity(intent)
        }

        btnStartNew.setOnClickListener {
            MainRoomDB.getInstance(applicationContext).clearAllTables()
            recreate()
        }

        btnIncreaseDollar.setOnClickListener {
            val dialog = ValueInputDialog(applicationContext,"DOLLAR","PLUS"){
                recreate()
            }
            dialog.show(supportFragmentManager,"INCRASE_DOLLAR")
        }

        btnIncreaseRuble.setOnClickListener {
            val dialog = ValueInputDialog(applicationContext, "RUBLE", "PLUS"){
                recreate()
            }
            dialog.show(supportFragmentManager,"INCRASE_RUBLE")
        }

        btnDereaseDollar.setOnClickListener {
            val dialog = ValueInputDialog(applicationContext, "DOLLAR", "MINUS"){
                recreate()
            }
            dialog.show(supportFragmentManager,"DECRASE_DOLLAR")
        }

        btnDereaseRuble.setOnClickListener {
            val dialog = ValueInputDialog(applicationContext, "RUBLE","MINUS"){
                recreate()
            }
            dialog.show(supportFragmentManager,"DECRASE_RUBLE")
        }
    }

}