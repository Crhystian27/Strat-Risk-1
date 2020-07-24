package co.mba.strat_risk.di.module;


import co.mba.strat_risk.ui.detail.NewsDetailFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentNewsDetailModule {

    @ContributesAndroidInjector
    abstract NewsDetailFragment contributeNewsDetailFragment();
}
