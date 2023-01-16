package az.farzinzeynal.hesabat.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import az.farzinzeynal.hesabat.R
import az.farzinzeynal.hesabat.adapter.DollarHistoryAdapter
import az.farzinzeynal.hesabat.adapter.RubleHistoryAdapter
import az.farzinzeynal.hesabat.room_database.MainRoomDB
import az.farzinzeynal.hesabat.room_database.repository.DbRepository
import az.farzinzeynal.hesabat.util.Extentions.formatValue
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class HistoryActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapterDollar: DollarHistoryAdapter
    private lateinit var adapterRuble: RubleHistoryAdapter

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val roomDao = MainRoomDB.getInstance(applicationContext).valuesDao()
        val databaseRepository = DbRepository(roomDao)

        val finalValuePlus = findViewById<TextView>(R.id.finalValuePlus)
        val finalValueMinus = findViewById<TextView>(R.id.finalValueMinus)
        val historyList = findViewById<RecyclerView>(R.id.historyList)

        val finalDollarList = databaseRepository.getDollarValues()
        val finalRubbleList = databaseRepository.getRubleValues()

        val finalDollarValuePlus = finalDollarList.filter { it.operationType=="PLUS" }.sumOf { it.dollarValue }.formatValue()
        val finalDollarValueMinus = finalDollarList.filter { it.operationType=="MINUS" }.sumOf { it.dollarValue }.formatValue()
        val finalRubleValuePlus = finalRubbleList.filter { it.operationType=="PLUS" }.sumOf { it.rubleValue }.formatValue()
        val finalRubleValueMinus = finalRubbleList.filter { it.operationType=="MINUS" }.sumOf { it.rubleValue }.formatValue()

        linearLayoutManager = LinearLayoutManager(this)
        historyList.layoutManager = linearLayoutManager

        val valueType = intent.extras?.getString("valueType") ?: ""

        if (valueType=="DOLLAR"){
            adapterDollar = DollarHistoryAdapter(this)
            historyList.adapter =adapterDollar
            adapterDollar.addData(finalDollarList)
            finalValuePlus.text = "Yekun mədaxil $finalDollarValuePlus $"
            finalValueMinus.text = "Yekun məxaric $finalDollarValueMinus $"
        }
        else {
            adapterRuble = RubleHistoryAdapter(this)
            historyList.adapter =adapterRuble
            adapterRuble.addData(finalRubbleList)
            finalValuePlus.text = "Yekun mədaxil $finalRubleValuePlus ₽"
            finalValueMinus.text = "Yekun məxaric $finalRubleValueMinus ₽"
        }




    }



}