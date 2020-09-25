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

    @GET("https://customsearch.googleapis.com/customsearch/v1?cx=002215266167357709810:korcta35klu&exactTerms=Suzuki&filter=1&gl=CO&lr=lang_es&start=1&key=AIzaSyC5DfrydcgsCkAd2qLeYLoGUHwK2OOx0Hg")
    Single<NewsDTO> getNews();
}
