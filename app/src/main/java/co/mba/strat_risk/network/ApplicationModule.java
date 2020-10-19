package co.mba.strat_risk.network;


import com.bumptech.glide.module.AppGlideModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import co.mba.strat_risk.BuildConfig;
import co.mba.strat_risk.data.SRDataBase;
import co.mba.strat_risk.data.dao.NewsDao;
import co.mba.strat_risk.data.dao.UserDao;
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
    AppGlideModule provideAppGlideModule() {
        return provideAppGlideModule();
    }

    @Provides
    @Singleton
    Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Repository provideRepository(ApiService apiService, Executor executor, NewsDao newsDao, UserDao userDao) {
        return new Repository(apiService, executor, newsDao, userDao);
    }

    @Provides
    @Singleton
    NewsDao provideNewsDao(SRDataBase dataBase) {
        return dataBase.newsDao();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(SRDataBase dataBase) {
        return dataBase.userDao();
    }

    @Provides
    @Singleton
    Executor providesExecutor() {
        return Executors.newSingleThreadExecutor();
    }


    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient.Builder().connectTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }


    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(OkHttpClient.Builder okHttpClientBuilder) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.HEADERS);
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        okHttpClientBuilder.addInterceptor(new ErrorInterceptor());
        okHttpClientBuilder.addNetworkInterceptor(new LoggingInterceptor());
        okHttpClientBuilder.addInterceptor(new RequestInterceptor());

        return okHttpClientBuilder.cache(null).build();
    }

    @Provides
    @Singleton
    static Retrofit providesRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .baseUrl("http://159.89.87.144")
                .build();
    }

    @Provides
    @Singleton
    ApiService providesService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
