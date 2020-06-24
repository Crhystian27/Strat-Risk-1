package co.mba.strat_risk.di.module;

import co.mba.strat_risk.ui.dashboard.DashboardFragment;
import co.mba.strat_risk.ui.home.HomeFragment;
import co.mba.strat_risk.ui.notifications.NotificationsFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract DashboardFragment contributeDashboardFragment();

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract NotificationsFragment contributeNotificationsFragment();
}
