package co.mba.strat_risk.network;

import java.util.List;

import co.mba.strat_risk.data.dto.NewsDTO;


import io.reactivex.Single;
import retrofit2.http.GET;


public interface ApiService {


    //Implement Search
    @GET("v2/everything?qInTitle=suzuki motor&apiKey=2433bb7187974bb890590e8593afda40")
    Single<List<NewsDTO>> getNews();
}
