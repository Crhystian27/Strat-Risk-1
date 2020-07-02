package co.mba.strat_risk.di.component;

import android.app.Application;

import javax.inject.Singleton;

import co.mba.strat_risk.base.BaseApplication;
import co.mba.strat_risk.di.module.ActivityModule;
import co.mba.strat_risk.di.module.DbModule;
import co.mba.strat_risk.di.module.FragmentModule;
import co.mba.strat_risk.di.module.ViewModelModule;
import co.mba.strat_risk.network.ServicesModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ViewModelModule.class,
        FragmentModule.class,
        ActivityModule.class,
        DbModule.class,
        ServicesModule.class})

public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(BaseApplication application);

}
