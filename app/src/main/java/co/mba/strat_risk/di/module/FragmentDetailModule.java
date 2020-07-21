package co.mba.strat_risk.di.module;

import co.mba.strat_risk.ui.detail.DetailFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentDetailModule {

    @ContributesAndroidInjector
    abstract DetailFragment contributeDetailFragment();
}
