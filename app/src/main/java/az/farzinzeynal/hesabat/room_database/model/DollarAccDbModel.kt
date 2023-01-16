package az.farzinzeynal.hesabat.room_database.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey


@Keep
@Entity(tableName = "dollarAccTable")
data class DollarAccDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var dollarValue : Double,
    var from: String?,
    var to: String,
    var date: String?,
    var operationType: String?,
)
