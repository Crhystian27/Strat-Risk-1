package co.mba.strat_risk.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import co.mba.strat_risk.data.entity.User;


@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table  where id =:id")
    LiveData<User> loadUser(Integer id);

    @Query("DELETE FROM user_table where id =:id")
    void deleteUser(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User userDTO);
}
