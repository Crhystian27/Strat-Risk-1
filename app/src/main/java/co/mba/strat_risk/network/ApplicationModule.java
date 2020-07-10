package co.mba.strat_risk.network;

import androidx.room.Dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import co.mba.strat_risk.data.dao.NewsDao;
import co.mba.strat_risk.data.repository.Repository;
import co.mba.strat_risk.util.Constants;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
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
    GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Executor providesExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Singleton
    @Provides
    @Named("schedulerIO")
    static Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }

    @Singleton
    @Provides
    @Named("androidScheduler")
    static Scheduler provideAndroidScheduler() {
        return AndroidSchedulers.mainThread();
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
        builder.addNetworkInterceptor(loggingInterceptor);
        builder.connectTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(requestInterceptor);
        return builder.cache(null).build();
    }

    @Provides
    @Singleton
    static Retrofit providesRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl(Constants.BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    ApiService providesService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
