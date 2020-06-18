package co.mba.strat_risk.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Modulo1 {

    @Query("SELECT * FROM modulo1_table")
    LiveData<List<Modulo1>> loadAllModulo1();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllModulo1(List<Modulo1> modulo1);
}
