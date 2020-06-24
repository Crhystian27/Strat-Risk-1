package co.mba.strat_risk.di.component;

import android.app.Application;

import javax.inject.Singleton;

import co.mba.strat_risk.base.BaseApplication;
import co.mba.strat_risk.di.module.ActivityModule;
import co.mba.strat_risk.di.module.DbModule;
import co.mba.strat_risk.di.module.FragmentModule;
import co.mba.strat_risk.di.module.ViewModelModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ViewModelModule.class,
        FragmentModule.class,
        ActivityModule.class,
        DbModule.class})

public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }

    void inject(BaseApplication application);

}
