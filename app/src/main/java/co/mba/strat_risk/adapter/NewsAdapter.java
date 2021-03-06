package co.mba.strat_risk.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.List;

import co.mba.strat_risk.R;

import co.mba.strat_risk.data.entity.News;

import co.mba.strat_risk.ui.NewsDetailActivity;

import co.mba.strat_risk.util.Constants;

import androidx.lifecycle.ViewModelProvider.Factory;

import co.mba.strat_risk.widgets.DialogSelection;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<? extends News> dtoList;
    private Integer status;
    private LinearLayout empty;
    private Activity activity;
    private RelativeLayout layout;
    private Fragment fragment;
    private Factory factory;

    public NewsAdapter(Activity activity, Context context, List<? extends News> dtoList, LinearLayout empty, Integer status, Fragment fragment, Factory factory, RelativeLayout layout) {
        this.context = context;
        this.dtoList = dtoList;
        this.empty = empty;
        this.status = status;
        this.layout = layout;
        this.fragment = fragment;
        this.factory = factory;
        this.activity = activity;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, parent, false);
        return new ViewHolder(view, activity, context, dtoList, status, fragment, factory, layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        //TODO PROYECTO CARRITO DE COTIZACION -- PARA VARIAS POLIZAS.
        //TODO COTIZAR AUTOS EN TODAS LAS COMPA??IAS.

        //TODO DIAGRAMA DE FLUJO PARA LOS SEGUROS. -AUTOMATIZAR

        /*switch (status) {
            case 0:
                holder.param1.setTextColor(context.getColor(R.color.colorPrimaryLight));
                break;
            case 1:
                holder.param1.setTextColor(context.getColor(R.color.colorOpportunity));
                break;
            case 2:
                holder.param1.setTextColor(context.getColor(R.color.colorInteresting));
                break;
            case 3:
                holder.param1.setTextColor(context.getColor(R.color.colorRisk));
                break;
        }*/

        holder.param1.setTextColor(context.getColor(R.color.colorPrimaryLight));

        String param1 = dtoList.get(position).getTitle();
        String param2 = dtoList.get(position).getSnippet();
        //String param0 = dtoList.get(position).getUrlToImage();
        String param0 = dtoList.get(position).getSrc();
        Log.e("IMG", param0 + "");
        Drawable drawable = context.getDrawable(R.drawable.ic_gris);

        setImage(context, holder, drawable, param0);
        holder.param1.setText(param1);
        holder.param2.setText(param2);

    }

    private void setImage(Context context, NewsAdapter.ViewHolder holder, Drawable drawable, String param0) {
        if (param0 == null) {
            //Utilities.getBitmap(context, holder.param0);
            Glide.with(context.getApplicationContext())
                    .applyDefaultRequestOptions(RequestOptions.placeholderOf(drawable).error(drawable))
                    .load(drawable)
                    .into(holder.param0);
        } else {

            String[] parts = param0.split(":");
            String part1 = parts[1];
            Log.e("part", part1);
            String[] subParts = part1.split("'");
            String subPart1 = subParts[0];
            Log.e("SubPart", subPart1);
            String paramImg = "https:" + subPart1;
            Log.e("paramImg", paramImg + "-->");
            Glide.with(context.getApplicationContext())
                    .applyDefaultRequestOptions(RequestOptions.placeholderOf(drawable).error(drawable))
                    .load(paramImg)
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private Context context;
        private Activity activity;
        private List<? extends News> dtoList;
        private TextView param1, param2, param3, param4;
        private ImageView param0;
        private Integer status;
        private Fragment fragment;
        private Factory factory;
        private RelativeLayout layout;

        public ViewHolder(@NonNull View itemView, Activity activity, Context context, List<? extends News> dtoList, Integer status, Fragment fragment, Factory factory, RelativeLayout layout) {
            super(itemView);
            this.status = status;
            this.context = context;
            this.dtoList = dtoList;
            this.layout = layout;
            this.fragment = fragment;
            this.factory = factory;
            this.activity = activity;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            param0 = itemView.findViewById(R.id.pic);
            param1 = itemView.findViewById(R.id.row_title);
            param2 = itemView.findViewById(R.id.row_body);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            News dto = this.dtoList.get(position);
            Intent intent = new Intent(context, NewsDetailActivity.class);
            intent.putExtra(Constants.EXTRA_NEWS, new Gson().toJson(dto));
            context.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            News dto = this.dtoList.get(position);
            DialogSelection.showDialog(activity, status, dto, fragment, factory, layout);
            return true;
        }
    }
}
