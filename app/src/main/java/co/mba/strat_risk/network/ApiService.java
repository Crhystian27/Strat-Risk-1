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

    /*@POST("192.168./oauth/token")
    //Send fields authentication
    //Recibo un tokensillo
    Observable<RequestBody> loginUser(@Body //Object);

    @GET("../api/user")
            //Obtener
    Observable<UserD> getUser(@Header("Authorization") String value);*/

    //Implement Search
    //http://newsapi.org/v2/top-headlines?country=co&apiKey=2ba600d146a84468ac58021aa9f7c2e3
    //@GET("everything?qInTitle=suzuki motor&apiKey=2433bb7187974bb890590e8593afda40")

    @POST("oauth/token")
    Observable<AccessTokenDTO> getToken(@Body Session session);

    /*@Headers({
            "Content-Type: application/json",
            "Authorization: Your-App-Name"
    })*/
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
