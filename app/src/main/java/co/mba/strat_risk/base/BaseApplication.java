package co.mba.strat_risk.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Inject;


import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class BaseApplication extends Application implements HasActivityInjector {

    @Inject
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
        DaggerAppComponent.builder().application(this).build().inject(this);
    }
}
