package co.mba.strat_risk.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.mba.strat_risk.R;
import co.mba.strat_risk.data.dto.NewsDTO;
import co.mba.strat_risk.util.Utilities;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<? extends NewsDTO> dtoList;
    private List<? extends NewsDTO> filteredList;
    private RelativeLayout empty;
    private Dialog dialog;

    public NewsAdapter(Context context, List<? extends NewsDTO> dtoList, RelativeLayout empty) {
        this.context = context;
        this.dtoList = dtoList;
        this.filteredList = dtoList;
        this.empty = empty;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, parent, false);
        return new ViewHolder(view, context, dtoList);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        //TODO PROYECTO CARRITO DE COTIZACION -- PARA VARIAS POLIZAS.
        //TODO COTIZAR AUTOS EN TODAS LAS COMPAÑIAS.

        //TODO DIAGRAMA DE FLUJO PARA LOS SEGUROS. -AUTOMATIZAR
        String param1 = dtoList.get(position).getTitle();
        String param2 = dtoList.get(position).getDescription();
        //String param3 = dtoList.get(position).getName().trim();

        //TODO LOAD FROM INTERNET WITH GLIDE
        holder.param0.setImageResource(R.drawable.ic_rss);
        Utilities.getBitmap(context, holder.param0);

        holder.param1.setText(param1);
        holder.param2.setText(param2);
        //holder.param3.setText(param3);


    }

    @Override
    public int getItemCount() {
        if (dtoList == null || dtoList.size() == 0) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.GONE);
        }
        return dtoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private List<? extends NewsDTO> dtoList;
        private TextView param1, param2, param3, param4;
        private ImageView param0;

        public ViewHolder(@NonNull View itemView, Context context, List<? extends NewsDTO> dtoList) {
            super(itemView);
            this.context = context;
            this.dtoList = dtoList;
            itemView.setOnClickListener(this);
            param0 = itemView.findViewById(R.id.pic);
            param1 = itemView.findViewById(R.id.row_title);
            param2 = itemView.findViewById(R.id.row_body);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
