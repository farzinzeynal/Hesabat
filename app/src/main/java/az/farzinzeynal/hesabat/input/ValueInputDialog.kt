package az.farzinzeynal.hesabat.input

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import az.farzinzeynal.hesabat.R
import az.farzinzeynal.hesabat.room_database.MainRoomDB
import az.farzinzeynal.hesabat.room_database.model.DollarAccDbModel
import az.farzinzeynal.hesabat.room_database.model.RubleAccDbModel
import az.farzinzeynal.hesabat.room_database.repository.DbRepository
import java.util.*

class ValueInputDialog(applicationContext: Context, private val type:String,  private val operationType:String): DialogFragment() {


    private val roomDao = MainRoomDB.getInstance(applicationContext).valuesDao()
    private val databaseRepository = DbRepository(roomDao)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_value_input, container, false);
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val submitButton = view.findViewById<Button>(R.id.btnSubmit)
        val enretedValue = view.findViewById<EditText>(R.id.enretedValue)



        submitButton.setOnClickListener {
            if (enretedValue.text.toString().isNotEmpty()){
                when(type){
                    "DOLLAR"->{
                        val dollarAccDbModel = DollarAccDbModel(
                            dollarValue = enretedValue.text.toString().toDouble(),
                            from ="Farzin",
                            to = "Elvin",
                            date = Date().toString(),
                            operationType = operationType
                        )
                        databaseRepository.writeDollarValuesToRoom(dollarAccDbModel)
                    }

                    "RUBLE"->{
                        val rubbleAccDbModel = RubleAccDbModel(
                            dollarValue = enretedValue.text.toString().toDouble(),
                            from ="Farzin",
                            to = "Elvin",
                            date = Date().toString(),
                            operationType = operationType
                        )
                        databaseRepository.writeRubleValuesToRoom(rubbleAccDbModel)
                    }
                }
                dismiss()
            }
            else{
                enretedValue.error ="Məbləği daxil edin!"
            }

        }

    }
}