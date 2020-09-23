package co.mba.strat_risk.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import co.mba.strat_risk.R;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public String[] slide_title = {
            "Diferencia las señales del ruido con Strat - Risk - MBA",
    };

    public String[] slide_subTitle = {
            "Strat - Risk - MBA",
    };

    public int[] slide_image = {
            R.drawable.ic_slider,
    };

    public String[] slide_message = {
            "La app de noticias que te permite estar al día con las novedades de tu sector y gestionar en parte tu riesgos estratégico.",
    };


    @Override
    public int getCount() {
        return slide_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_slider, container, false);

        TextView textTitle = view.findViewById(R.id.slideTitle);
        TextView textSubTitle = view.findViewById(R.id.slideSubTitle);
        ImageView imageView = view.findViewById(R.id.slideImg);
        TextView textMessage = view.findViewById(R.id.slideMessage);

        imageView.setImageResource(slide_image[position]);
        textTitle.setText(slide_title[position]);
        textSubTitle.setText(slide_subTitle[position]);
        textMessage.setText(slide_message[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
