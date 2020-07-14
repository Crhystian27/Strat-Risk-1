package co.mba.strat_risk.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import co.mba.strat_risk.data.SRDataBase;
import co.mba.strat_risk.data.dao.NewsDao;
import co.mba.strat_risk.data.repository.Repository;
import co.mba.strat_risk.util.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Repository provideRepository(ApiService apiService, Executor executor, NewsDao newsDao, InternetConnection connection, RequestInterceptor interceptor) {
        return new Repository(apiService, executor, newsDao, connection, interceptor);
    }

    @Provides
    @Singleton
    NewsDao provideNewsDao(SRDataBase dataBase){
        return dataBase.newsDao();
    }

    @Provides
    @Singleton
    Executor providesExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    RequestInterceptor providesRequestInterceptor() {
        return new RequestInterceptor();
    }

    @Provides
    @Singleton
    InternetConnection providesInternetConnection() {
        return new InternetConnection();
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(RequestInterceptor requestInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(requestInterceptor);
        builder.addInterceptor(requestInterceptor);
        builder.addNetworkInterceptor(loggingInterceptor);
        builder.connectTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS);
        return builder.cache(null).build();
    }

    @Provides
    @Singleton
    static Retrofit providesRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }

    @Provides
    @Singleton
    ApiService providesService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
