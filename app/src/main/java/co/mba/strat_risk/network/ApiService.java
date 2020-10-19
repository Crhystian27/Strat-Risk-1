package co.mba.strat_risk.network;

import co.mba.strat_risk.data.dto.AccessTokenDTO;
import co.mba.strat_risk.data.dto.NewsDTO;


import co.mba.strat_risk.data.entity.User;
import co.mba.strat_risk.data.model.Session;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

    @POST("oauth/token")
    Observable<AccessTokenDTO> getToken(@Body Session session);

    @GET("api/user")
    Observable<User> getUser(@Header("Bearer") String bearer);

    @GET("https://customsearch.googleapis.com/customsearch/v1?filter=1")
    Single<NewsDTO> getNews(@Query("lr") String lr,
                            @Query("gl") String gl,
                            @Query("start") String start,
                            @Query("key") String key,
                            @Query("cx") String cx,
                            @Query("exactTerms") String search);
}
