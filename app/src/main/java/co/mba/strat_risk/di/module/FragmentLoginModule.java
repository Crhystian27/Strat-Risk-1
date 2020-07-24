package co.mba.strat_risk.di.module;

import co.mba.strat_risk.ui.login.LoginFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentLoginModule {

    @ContributesAndroidInjector
    abstract LoginFragment contributeLoginFragment();

}
