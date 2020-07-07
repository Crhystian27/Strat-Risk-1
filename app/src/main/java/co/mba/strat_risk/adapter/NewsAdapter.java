package co.mba.strat_risk.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.mba.strat_risk.data.dto.NewsDTO;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<? extends NewsDTO> dtoList;
    private List<? extends NewsDTO> filteredList;
    private LinearLayout empty;
    private Dialog dialog;

    public NewsAdapter(Context context, List<? extends NewsDTO> dtoList,LinearLayout empty) {
        this.context = context;
        this.dtoList = dtoList;
        this.filteredList = dtoList;
        this.empty = empty;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private List<? extends  NewsDTO> dtoList;
        private TextView param1 , param2, param3, param4;
        private ImageView param0;

        public ViewHolder(@NonNull View itemView, Context context, List<? extends NewsDTO> dtoList) {
            super(itemView);
            this.context = context;
            this.dtoList = dtoList;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }
}
