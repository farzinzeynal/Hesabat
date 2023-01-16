package az.farzinzeynal.hesabat.input

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import az.farzinzeynal.hesabat.R
import az.farzinzeynal.hesabat.room_database.MainRoomDB
import az.farzinzeynal.hesabat.room_database.model.DollarAccDbModel
import az.farzinzeynal.hesabat.room_database.model.RubleAccDbModel
import az.farzinzeynal.hesabat.room_database.model.SumOperattion
import az.farzinzeynal.hesabat.room_database.repository.DbRepository
import az.farzinzeynal.hesabat.shared_prefs.MainSharedPrefs
import az.farzinzeynal.hesabat.shared_prefs.SharedPrefNames
import az.farzinzeynal.hesabat.shared_prefs.SharedTypes
import az.farzinzeynal.hesabat.util.Extentions.formatValue
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class ValueInputDialog(applicationContext: Context,
                       private val type:String,
                       private val operationType:String,
                       val onClick: (SumOperattion) -> Unit): DialogFragment() {


    private val roomDao = MainRoomDB.getInstance(applicationContext).valuesDao()
    private val databaseRepository = DbRepository(roomDao)
    private var fromHOlder= ""
    private var toHOlder = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_value_input, container, false);
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val valueTitle = view.findViewById<TextView>(R.id.valueTitle)
        val submitButton = view.findViewById<Button>(R.id.btnSubmit)
        val enretedValue = view.findViewById<TextInputLayout>(R.id.enretedValue)
        val valueHolder = view.findViewById<TextInputLayout>(R.id.valueHolder)

        val finalDollarList = databaseRepository.getDollarValues()
        val finalRubbleList = databaseRepository.getRubleValues()



        enretedValue.hint = "Məbləğ:"

        when(operationType){
            "PLUS"->{
                valueTitle.text ="Mədaxil:"
                valueHolder.hint = "Kimdən:"
            }
            "MINUS"->{
                valueTitle.text ="Məxaric:"
                valueHolder.hint = "Kimə:"
            }
        }

        submitButton.setOnClickListener {

            when(operationType){
                "PLUS"->{
                    fromHOlder = valueHolder.editText?.text.toString()
                }
                "MINUS"->{
                    toHOlder = valueHolder.editText?.text.toString()
                }
            }

            if (enretedValue.editText?.text.toString().isNotEmpty()){
                when(type){
                    "DOLLAR"->{
                        val dollarAccDbModel = DollarAccDbModel(
                            dollarValue = enretedValue.editText?.text.toString().toDouble(),
                            from =fromHOlder,
                            to = toHOlder,
                            date = Date().toString(),
                            operationType = operationType
                        )
                        databaseRepository.writeDollarValuesToRoom(dollarAccDbModel)
                        val finalDollarValuePlus = finalDollarList.filter { it.operationType=="PLUS" }.sumOf { it.dollarValue }
                        val finalDollarValueMinus = finalDollarList.filter { it.operationType=="MINUS" }.sumOf { it.dollarValue }
                        calculcateSumValues(finalDollarValuePlus,finalDollarValueMinus,"DOLLAR")
                    }

                    "RUBLE"->{
                        val rubbleAccDbModel = RubleAccDbModel(
                            rubleValue = enretedValue.editText?.text.toString().toDouble(),
                            from =fromHOlder,
                            to = toHOlder,
                            date = Date().toString(),
                            operationType = operationType
                        )
                        databaseRepository.writeRubleValuesToRoom(rubbleAccDbModel)
                        val finalRubleValuePlus = finalRubbleList.filter { it.operationType=="PLUS" }.sumOf { it.rubleValue }
                        val finalRubleValueMinus = finalRubbleList.filter { it.operationType=="MINUS" }.sumOf { it.rubleValue }
                        calculcateSumValues(finalRubleValuePlus,finalRubleValueMinus,"RUBLE")
                    }
                }
                dismiss()
            }
            else{
                enretedValue.error ="Məbləği daxil edin!"
            }

        }

    }

    private fun calculcateSumValues(plusValue: Double,minusValue:Double, type: String) {
        val returValue = plusValue-minusValue
        when(type){
            "DOLLAR"->{
                MainSharedPrefs(requireContext(), SharedTypes.VALUE_DATA).set(SharedPrefNames.DOLLAR_VALUE,returValue.formatValue())
                onClick.invoke(SumOperattion(returValue.formatValue(),"DOLLAR"))
            }
            "RUBLE"->{
                MainSharedPrefs(requireContext(), SharedTypes.VALUE_DATA).set(SharedPrefNames.RUBLE_VALUE,returValue.formatValue())
                onClick.invoke(SumOperattion(returValue.formatValue(),"RUBLE"))
            }
        }


    }
}