package apk.karmak.retrofitapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideViewPagerAdapter extends PagerAdapter {

    Context ctx;

    public SlideViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater= (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_screen,container,false);


        ImageView picture=view.findViewById(R.id.picture);

        ImageView ind1=view.findViewById(R.id.ind1);
        ImageView ind2=view.findViewById(R.id.ind2);
        ImageView ind3=view.findViewById(R.id.ind3);


        TextView title=view.findViewById(R.id.title);
        TextView desc=view.findViewById(R.id.desc);

        TextView skip=view.findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OnBoard.viewPager.setCurrentItem(position+1);
//            }
//        });
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OnBoard.viewPager.setCurrentItem(position-1);
//            }
//        });

        switch (position)
        {
            case 0:
                picture.setImageResource(R.drawable.illus_one);

                ind1.setImageResource(R.drawable.btn_status);
                ind2.setImageResource(R.drawable.btn_yandex);
                ind3.setImageResource(R.drawable.btn_yandex);

                title.setText("Анализы");
                desc.setText("Экспресс сбор и получение проб");

                skip.setText("Пропустить");

                break;
            case 1:
                picture.setImageResource(R.drawable.illus_two);
                ind1.setImageResource(R.drawable.btn_yandex);
                ind2.setImageResource(R.drawable.btn_status);
                ind3.setImageResource(R.drawable.btn_yandex);

                title.setText("Уведомления");
                desc.setText("Вы быстро узнаете о результатах");

                skip.setText("Пропустить");
                break;
            case 2:
                picture.setImageResource(R.drawable.illus_three);
                ind1.setImageResource(R.drawable.btn_yandex);
                ind2.setImageResource(R.drawable.btn_yandex);
                ind3.setImageResource(R.drawable.btn_status);

                title.setText("Мониторинг");
                desc.setText("Наши врачи всегда наблюдают \n" +
                        "за вашими показателями здоровья");

                skip.setText("Завершить");

                break;






        }


        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
