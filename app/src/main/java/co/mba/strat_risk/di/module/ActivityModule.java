package co.mba.strat_risk.di.module;

import co.mba.strat_risk.ui.LoginActivity;
import co.mba.strat_risk.ui.MainActivity;
import co.mba.strat_risk.ui.NewsDetailActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {FragmentMainModule.class})
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector()
    abstract NewsDetailActivity contributeNewsDetailActivity();

    @ContributesAndroidInjector(modules = {FragmentLoginModule.class})
    abstract LoginActivity contributeLoginActivity();

}
