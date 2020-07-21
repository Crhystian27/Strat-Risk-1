package co.mba.strat_risk.di.module;

import co.mba.strat_risk.ui.interesting.InterestingFragment;
import co.mba.strat_risk.ui.news.NewsFragment;
import co.mba.strat_risk.ui.opportunity.OpportunityFragment;
import co.mba.strat_risk.ui.risk.RiskFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentMainModule {


    @ContributesAndroidInjector
    abstract InterestingFragment contributeInterestingFragment ();

    @ContributesAndroidInjector
    abstract NewsFragment contributeNewsFragment();

    @ContributesAndroidInjector
    abstract RiskFragment contributeRiskFragment();

    @ContributesAndroidInjector
    abstract OpportunityFragment contributeOpportunityFragment();

}
