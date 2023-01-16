package az.farzinzeynal.hesabat.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

object Extentions {


    fun Double.formatValue(): String {
        val nf: NumberFormat = DecimalFormat.getInstance(Locale.ENGLISH)
        val decimalFormatter = nf as DecimalFormat
        val symbols = DecimalFormatSymbols()
        symbols.decimalSeparator = ','
        symbols.groupingSeparator = ' '
        decimalFormatter.decimalFormatSymbols = symbols
        return decimalFormatter.format(this)
    }
}