package co.mba.strat_risk.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.List;

import co.mba.strat_risk.R;
import co.mba.strat_risk.data.dto.ArticlesDTO;
import co.mba.strat_risk.ui.news_detail.NewsDetailActivity;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<? extends ArticlesDTO> dtoList;
    private List<? extends ArticlesDTO> filteredList;
    private RelativeLayout empty;
    private Dialog dialog;

    public NewsAdapter(Context context, List<? extends ArticlesDTO> dtoList, RelativeLayout empty, Dialog dialog) {
        this.context = context;
        this.dtoList = dtoList;
        this.dialog = dialog;
        this.filteredList = dtoList;
        this.empty = empty;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, parent, false);
        return new ViewHolder(view, context, dtoList,dialog);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        //TODO PROYECTO CARRITO DE COTIZACION -- PARA VARIAS POLIZAS.
        //TODO COTIZAR AUTOS EN TODAS LAS COMPAÑIAS.

        //TODO DIAGRAMA DE FLUJO PARA LOS SEGUROS. -AUTOMATIZAR

        String param1 = dtoList.get(position).getTitle();
        String param2 = dtoList.get(position).getDescription();
        String param0 = dtoList.get(position).getUrlToImage();

        Drawable drawable = context.getDrawable(R.drawable.ic_rss);

        setImage(context, holder, drawable, param0);
        holder.param1.setText(param1);
        holder.param2.setText(param2);

    }

    private void setImage(Context context, NewsAdapter.ViewHolder holder, Drawable drawable, String param0) {
        if (param0 == null) {
            Utilities.getBitmap(context, holder.param0);
            Glide.with(context.getApplicationContext())
                    .applyDefaultRequestOptions(RequestOptions.placeholderOf(R.drawable.ic_rss).error(R.drawable.ic_rss).circleCrop())
                    .load(drawable)
                    .into(holder.param0);
        } else {
            Glide.with(context.getApplicationContext())
                    .applyDefaultRequestOptions(RequestOptions.placeholderOf(R.drawable.ic_rss).error(R.drawable.ic_rss).circleCrop())
                    .load(param0)
                    .into(holder.param0);
        }
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

        private Context context;
        private List<? extends ArticlesDTO> dtoList;
        private Dialog dialog;
        private TextView param1, param2, param3, param4;
        private ImageView param0;

        public ViewHolder(@NonNull View itemView, Context context, List<? extends ArticlesDTO> dtoList, Dialog dialog) {
            super(itemView);
            this.dialog = dialog;
            this.context = context;
            this.dtoList = dtoList;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            param0 = itemView.findViewById(R.id.pic);
            param1 = itemView.findViewById(R.id.row_title);
            param2 = itemView.findViewById(R.id.row_body);
        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            ArticlesDTO dto = this.dtoList.get(position);
            Intent intent = new Intent(context, NewsDetailActivity.class);
            intent.putExtra(Constants.EXTRA_NEWS,new Gson().toJson(dto));
            context.startActivity(intent);

        }

        @Override
        public boolean onLongClick(View v) {
          int position  = getAdapterPosition();
          ArticlesDTO dto = this.dtoList.get(position);






            return false;
        }
    }
}
