package az.farzinzeynal.hesabat.shared_prefs

import androidx.annotation.StringDef

object SharedTypes {
    const val VALUE_DATA = "VALUE_DATA"
    const val USERDATA = "USERDATA"
    @Retention(AnnotationRetention.SOURCE)
    @StringDef(VALUE_DATA, USERDATA)
    annotation class SharedTypesDef
}
