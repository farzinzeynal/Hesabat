package az.farzinzeynal.hesabat.room_database.repository

import az.farzinzeynal.hesabat.room_database.ValuesDao
import az.farzinzeynal.hesabat.room_database.model.DollarAccDbModel
import az.farzinzeynal.hesabat.room_database.model.RubleAccDbModel

class DbRepository(private val roomDao: ValuesDao) {
    fun writeDollarValuesToRoom(value: DollarAccDbModel) = roomDao.insertNewDollarValue(value)

    fun getDollarValues() = roomDao.getDollarValues()

    fun writeRubleValuesToRoom(value: RubleAccDbModel) = roomDao.insertNewRubleValue(value)

    fun getRubleValues() = roomDao.getRubleValues()
}