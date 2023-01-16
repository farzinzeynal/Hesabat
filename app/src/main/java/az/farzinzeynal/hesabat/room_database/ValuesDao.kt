package az.farzinzeynal.hesabat.room_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import az.farzinzeynal.hesabat.room_database.model.DollarAccDbModel
import az.farzinzeynal.hesabat.room_database.model.RubleAccDbModel

@Dao
interface ValuesDao {

    @Insert(entity = DollarAccDbModel::class)
    fun insertNewDollarValue(item: DollarAccDbModel)

    @Query("SELECT * FROM dollarAccTable")
    fun getDollarValues(): MutableList<DollarAccDbModel>

    @Insert(entity = RubleAccDbModel::class)
    fun insertNewRubleValue(item: RubleAccDbModel)

    @Query("SELECT * FROM rubbleAccTable")
    fun getRubleValues(): MutableList<RubleAccDbModel>

}