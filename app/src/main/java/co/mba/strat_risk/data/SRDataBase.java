package co.mba.strat_risk.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import co.mba.strat_risk.data.dao.NewsDao;
import co.mba.strat_risk.data.dao.UserDao;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.entity.User;


@Database(
        entities = {News.class, User.class},
        version = 1,
        exportSchema = false)

public abstract class SRDataBase extends RoomDatabase {

    // -- Dao --
    public abstract NewsDao newsDao();
    public abstract UserDao userDao();
}
