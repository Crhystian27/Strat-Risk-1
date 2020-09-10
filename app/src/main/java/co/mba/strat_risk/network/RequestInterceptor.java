package co.mba.strat_risk.network;

import android.util.Log;

import java.io.IOException;

import co.mba.strat_risk.data.dto.AccessTokenDTO;
import co.mba.strat_risk.util.AppPreferences;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Log.e("-->ver",request.url().host());

        if (!request.url().host().contains("customsearch.googleapis.com")) {
            request = request.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .method(request.method(), request.body())
                    .build();

            AccessTokenDTO accessToken = AppPreferences.getInstance().getAccessTokenDTO();
            if (accessToken != null) {
                request = request.newBuilder()
                        .addHeader("Authorization", accessToken.getType() + " " + accessToken.getAccessToken())
                        .method(request.method(), request.body())
                        .build();
            }
        }


        return chain.proceed(request);
    }


    /*private String scheme;
    private String host;
    private Integer port;


    public RequestInterceptor() {
    }*/

    /*public void setInterceptor(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null)
            return;
        scheme = httpUrl.scheme();
        host = httpUrl.host();
        port = httpUrl.port();

    }*/


    /*
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (scheme != null && host != null && port != null) {
            HttpUrl newUrl = request.url().newBuilder()
                    .scheme(scheme)
                    .host(host)
                    .port(port)
                    .build();
            request = request.newBuilder()
                    .url(newUrl)
                    .build();
        }
        return chain.proceed(request);
    }*/
}
