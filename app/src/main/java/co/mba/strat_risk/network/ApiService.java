package co.mba.strat_risk.network;

import java.util.List;

import co.mba.strat_risk.data.dto.AccessTokenDTO;
import co.mba.strat_risk.data.dto.NewsDTO;


import co.mba.strat_risk.data.dto.UserDTO;
import co.mba.strat_risk.data.model.Session;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiService {

    /*@POST("192.168./oauth/token")
    //Send fields authentication
    //Recibo un tokensillo
    Observable<RequestBody> loginUser(@Body //Object);

    @GET("../api/user")
            //Obtener
    Observable<UserDTO> getUser(@Header("Authorization") String value);*/

    //Implement Search
    //http://newsapi.org/v2/top-headlines?country=co&apiKey=2ba600d146a84468ac58021aa9f7c2e3
    //@GET("everything?qInTitle=suzuki motor&apiKey=2433bb7187974bb890590e8593afda40")

    @POST("localhost/oauth/token")
    Observable<AccessTokenDTO> getToken(@Body Session session);

    @GET("localhost/api/user")
    Observable<UserDTO> getUser(@Header("Authorization") String bearer);

    @GET("top-headlines?country=co&apiKey=2433bb7187974bb890590e8593afda40")
    Observable<NewsDTO> getNews();
}
