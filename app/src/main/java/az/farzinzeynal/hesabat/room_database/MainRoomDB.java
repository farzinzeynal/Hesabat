package az.farzinzeynal.hesabat.room_database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import az.farzinzeynal.hesabat.room_database.model.DollarAccDbModel;
import az.farzinzeynal.hesabat.room_database.model.RubleAccDbModel;

@Database(entities = {DollarAccDbModel.class, RubleAccDbModel.class },version = 6,exportSchema = false)
public abstract class MainRoomDB extends RoomDatabase {
    private static MainRoomDB instance;
    public abstract ValuesDao valuesDao();

    public static synchronized MainRoomDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MainRoomDB.class, "main_db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
        }
        return instance;
     }
}