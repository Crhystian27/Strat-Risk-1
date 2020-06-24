package co.mba.strat_risk.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import co.mba.strat_risk.data.entity.Modulo1;


@Dao
public interface Modulo1Dao {

    @Query("SELECT * FROM modulo1_table")
    LiveData<List<Modulo1>> loadAllModulo1();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllModulo1(List<Modulo1> modulo1);
}
