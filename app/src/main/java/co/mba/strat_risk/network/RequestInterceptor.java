package co.mba.strat_risk.network;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class RequestInterceptor implements Interceptor {
    private String scheme;
    private String host;
    private Integer port;


    @Inject
    public RequestInterceptor() {
    }


    public void setInterceptor(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null)
            return;
        scheme = httpUrl.scheme();
        host = httpUrl.host();
        port = httpUrl.port();

    }


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
    }
}
