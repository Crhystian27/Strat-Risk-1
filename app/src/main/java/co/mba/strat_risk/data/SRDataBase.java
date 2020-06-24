package co.mba.strat_risk.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import co.mba.strat_risk.data.dao.Modulo1Dao;
import co.mba.strat_risk.data.entity.Modulo1;


@Database(
        entities = {Modulo1.class},
        version = 1,
        exportSchema = false)

//TODO implement converter
//@TypeConverters({DateConverter.class})
public abstract class SRDataBase extends RoomDatabase {

    // -- Dao --
    public abstract Modulo1Dao modulo1Dao();
}
