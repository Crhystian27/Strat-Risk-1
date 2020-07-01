package co.mba.strat_risk.ui.interesting;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InterestingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InterestingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}