package co.mba.strat_risk.network;

import java.util.List;

import co.mba.strat_risk.data.dto.NewsDTO;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {

    /*@POST("192.168./oauth/token")
    //Send fields authentication
    //Recibo un tokensillo
    Observable<RequestBody> loginUser(@Body //Object);

    @GET("../api/user")
            //Obtener
    Observable<UserDTO> getUser(@Header("Authorization") String value);*/

    //Implement Search
    @GET("everything?qInTitle=suzuki motor&apiKey=2433bb7187974bb890590e8593afda40")
    Observable<NewsDTO> getNews();
}
