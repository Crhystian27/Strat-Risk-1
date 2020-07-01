package co.mba.strat_risk.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import co.mba.strat_risk.data.dao.NewsDao;
import co.mba.strat_risk.data.entity.News;


@Database(
        entities = {News.class},
        version = 1,
        exportSchema = false)

//TODO implement converter
//@TypeConverters({DateConverter.class})
public abstract class SRDataBase extends RoomDatabase {

    // -- Dao --
    public abstract NewsDao newsDao();
}
