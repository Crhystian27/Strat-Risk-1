package co.mba.strat_risk.data.dao;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import co.mba.strat_risk.data.entity.News;

@Dao
public interface NewsDao {

    //Load New  Local 0  Opportunity 1 Neutral 2  Risk 3,
    @Query("SELECT * FROM news_table where status = :idStatus")
    LiveData<List<News>> loadNewsStatus(Integer idStatus);

    @Query("SELECT * FROM news_table where id = :idNews")
    LiveData<News> loadNews(Integer idNews);

    //Delete
    @Query("DELETE FROM news_table where id = :idNews")
    void deleteNews(Integer idNews);

    @Query("DELETE FROM news_table")
    void deleteAllNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(News news);
}
