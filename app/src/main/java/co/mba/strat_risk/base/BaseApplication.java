package co.mba.strat_risk.base;

import androidx.multidex.MultiDex;

import android.content.Context;

import co.mba.strat_risk.di.component.ApplicationComponent;
import co.mba.strat_risk.di.component.DaggerApplicationComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


public class BaseApplication extends DaggerApplication {

    private static BaseApplication instance;
    private ApplicationComponent component;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }

    public static Context getAppContext() {
        return instance;
    }

    /*@Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    //TODO create new Context application Injector
    public Context context;

    //Nuevoas cambios

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    //TODO REVISAR DAGGER
    private void initDagger() {
        DaggerApplicationComponent.builder().application(this).build().inject(this);
    }*/
}
