package az.farzinzeynal.hesabat.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import az.farzinzeynal.hesabat.R
import az.farzinzeynal.hesabat.room_database.model.DollarAccDbModel
import az.farzinzeynal.hesabat.util.Extentions.formatValue
import kotlin.collections.ArrayList


class DollarHistoryAdapter(context: Context) :
    RecyclerView.Adapter<DollarHistoryAdapter.ViewHolder>() {
    val context = context
    private val dataSet: MutableList<DollarAccDbModel> = mutableListOf()
    fun addData(data: MutableList<DollarAccDbModel>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val value: TextView
        val holder: TextView
        init {
            // Define click listener for the ViewHolder's View.
            value = view.findViewById(R.id.value)
            holder = view.findViewById(R.id.holderName)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.value_report_item, viewGroup, false)

        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        //viewHolder.value.text = item.friendUserId?.uppercase()

        if (item.operationType=="PLUS"){
            viewHolder.value.setTextColor(ContextCompat.getColor(context,R.color.green))
            viewHolder.value.text = "+ "+item.dollarValue.formatValue()+" $"
            viewHolder.holder.text = item.from + "  "+item.date
        }
        else{
            viewHolder.value.setTextColor(ContextCompat.getColor(context,R.color.red))
            viewHolder.value.text = "- "+item.dollarValue.formatValue()+" $"
            viewHolder.holder.text = item.to + "  "+item.date
        }


    }

    override fun getItemCount() = dataSet.size

}