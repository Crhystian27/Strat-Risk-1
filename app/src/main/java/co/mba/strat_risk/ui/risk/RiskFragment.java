package co.mba.strat_risk.ui.risk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import co.mba.strat_risk.R;

public class RiskFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RiskViewModel riskViewModel = new ViewModelProvider(this).get(RiskViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        riskViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
}