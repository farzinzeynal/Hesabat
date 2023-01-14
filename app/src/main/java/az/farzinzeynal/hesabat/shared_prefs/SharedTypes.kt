package az.farzinzeynal.hesabat.shared_prefs

import androidx.annotation.StringDef

object SharedTypes {
    const val SETTINGS = "SETTINGS"
    const val USERDATA = "USERDATA"
    @Retention(AnnotationRetention.SOURCE)
    @StringDef(SETTINGS, USERDATA)
    annotation class SharedTypesDef
}
