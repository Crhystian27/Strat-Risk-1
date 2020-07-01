package co.mba.strat_risk.network;

import java.util.List;

import co.mba.strat_risk.data.dto.NewsDTO;
import io.reactivex.Observable;

import retrofit2.http.GET;


public interface ApiService {


    //Implement Search
    @GET("api")
    Observable<List<NewsDTO>> getNews();
}
