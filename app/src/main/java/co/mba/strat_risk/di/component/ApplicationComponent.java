package co.mba.strat_risk.di.component;

import android.app.Application;

import javax.inject.Singleton;

import co.mba.strat_risk.base.BaseApplication;
import co.mba.strat_risk.di.module.ActivityModule;
import co.mba.strat_risk.di.module.DbModule;
import co.mba.strat_risk.di.module.FragmentModule;
import co.mba.strat_risk.di.module.ViewModelModule;
import co.mba.strat_risk.network.ApplicationModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        DbModule.class,
        ActivityModule.class,
        FragmentModule.class,
        AndroidSupportInjectionModule.class,
        ViewModelModule.class,
        ApplicationModule.class})

public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
