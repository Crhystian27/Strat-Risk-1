package co.mba.strat_risk.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import co.mba.strat_risk.di.key.ViewModelKey;

import co.mba.strat_risk.ui.MainViewModel;
import co.mba.strat_risk.ui.NewsDetailViewModel;
import co.mba.strat_risk.ui.interesting.InterestingViewModel;
import co.mba.strat_risk.ui.news.NewsViewModel;
import co.mba.strat_risk.ui.opportunity.OpportunityViewModel;
import co.mba.strat_risk.ui.risk.RiskViewModel;
import co.mba.strat_risk.util.ViewModelFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailViewModel.class)
    abstract ViewModel bindNewsDetailViewModel(NewsDetailViewModel newsDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(InterestingViewModel.class)
    abstract ViewModel bindInterestingViewModel(InterestingViewModel interestingViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel.class)
    abstract ViewModel bindNewsViewModel(NewsViewModel newsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OpportunityViewModel.class)
    abstract ViewModel bindOpportunityViewModel(OpportunityViewModel opportunityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RiskViewModel.class)
    abstract ViewModel bindRiskViewModel(RiskViewModel riskViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
