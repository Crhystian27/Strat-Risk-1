package co.mba.strat_risk.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.Objects;

import co.mba.strat_risk.data.entity.News;
import dagger.Provides;


@Dao
public interface NewsDao {

    //Load New -1, 0 or 1
    @Query("SELECT * FROM news_table where status = :idStatus")
    LiveData<List<News>> loadNewsStatus(Integer idStatus);

    //Delete
    @Query("DELETE FROM news_table where id = :idNews")
    void deleteNews(Integer idNews);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(News news);
}
