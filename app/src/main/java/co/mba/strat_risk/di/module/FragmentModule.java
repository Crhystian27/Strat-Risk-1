package co.mba.strat_risk.di.module;

import co.mba.strat_risk.ui.interesting.InterestingFragment;
import co.mba.strat_risk.ui.news.NewsFragment;
import co.mba.strat_risk.ui.opportunity.OpportunityFragment;
import co.mba.strat_risk.ui.risk.RiskFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {


    @ContributesAndroidInjector
    abstract InterestingFragment contributeDashboardFragment();

    @ContributesAndroidInjector
    abstract NewsFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract RiskFragment contributeNotificationsFragment();

    @ContributesAndroidInjector
    abstract OpportunityFragment contributeOpportunityFragment();

}
