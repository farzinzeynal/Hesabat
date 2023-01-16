package az.farzinzeynal.hesabat.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import az.farzinzeynal.hesabat.R
import az.farzinzeynal.hesabat.room_database.MainRoomDB
import az.farzinzeynal.hesabat.room_database.repository.DbRepository
import com.google.android.material.textfield.TextInputEditText

class HistoryActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val roomDao = MainRoomDB.getInstance(applicationContext).valuesDao()
        val databaseRepository = DbRepository(roomDao)

        val finalValue = findViewById<TextView>(R.id.finalValue)

        val finalDollar = databaseRepository.getDollarValues()
        val finalRubble = databaseRepository.getRubleValues()

        finalValue.text = "$finalDollar  \n   $finalRubble"

    }
}