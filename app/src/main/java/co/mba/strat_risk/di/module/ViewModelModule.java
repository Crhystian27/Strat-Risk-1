package co.mba.strat_risk.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import co.mba.strat_risk.di.key.ViewModelKey;


import co.mba.strat_risk.ui.MainViewModel;
import co.mba.strat_risk.ui.MenuViewModel;
import co.mba.strat_risk.ui.NewsDetailViewModel;
import co.mba.strat_risk.ui.SliderViewModel;
import co.mba.strat_risk.ui.interesting.InterestingFragmentViewModel;
import co.mba.strat_risk.ui.login.ForgotFragmentViewModel;
import co.mba.strat_risk.ui.login.LoginFragmentViewModel;
import co.mba.strat_risk.ui.news.NewsFragmentViewModel;
import co.mba.strat_risk.ui.opportunity.OpportunityFragmentViewModel;
import co.mba.strat_risk.ui.risk.RiskFragmentViewModel;
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
    @ViewModelKey(InterestingFragmentViewModel.class)
    abstract ViewModel bindInterestingViewModel(InterestingFragmentViewModel interestingFragmentViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SliderViewModel.class)
    abstract ViewModel bindSliderViewModel(SliderViewModel sliderViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel.class)
    abstract ViewModel bindMenuViewModel(MenuViewModel MenuViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewsFragmentViewModel.class)
    abstract ViewModel bindNewsViewModel(NewsFragmentViewModel newsFragmentViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OpportunityFragmentViewModel.class)
    abstract ViewModel bindOpportunityViewModel(OpportunityFragmentViewModel opportunityFragmentViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RiskFragmentViewModel.class)
    abstract ViewModel bindRiskViewModel(RiskFragmentViewModel riskFragmentViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailViewModel.class)
    abstract ViewModel bindNewsDetailViewModel(NewsDetailViewModel newsDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentViewModel.class)
    abstract ViewModel bindLoginFragmentViewModel(LoginFragmentViewModel loginFragmentViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ForgotFragmentViewModel.class)
    abstract ViewModel bindForgotFragmentViewModel(ForgotFragmentViewModel loginFragmentViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
