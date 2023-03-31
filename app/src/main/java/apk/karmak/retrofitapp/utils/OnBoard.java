package apk.karmak.retrofitapp.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import apk.karmak.retrofitapp.R;

public class OnBoard extends AppCompatActivity {

    public static ViewPager viewPager;
    SlideViewPagerAdapter adapter;


    ImageView ind1,ind2,ind3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);


        ind1=findViewById(R.id.ind1);
        ind2=findViewById(R.id.ind2);
        ind3=findViewById(R.id.ind3);

        viewPager=findViewById(R.id.viewpager);
        adapter=new SlideViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        viewPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                Log.e("",viewPager.getCurrentItem()+"");
                switch (viewPager.getCurrentItem())
                {
                    case 0:
                        ind1.setImageResource(R.drawable.btn_status);
                        ind2.setImageResource(R.drawable.btn_yandex);
                        ind3.setImageResource(R.drawable.btn_yandex);

                        break;
                    case 1:
                        ind1.setImageResource(R.drawable.btn_yandex);
                        ind2.setImageResource(R.drawable.btn_status);
                        ind3.setImageResource(R.drawable.btn_yandex);

                        break;
                    case 2:
                        ind1.setImageResource(R.drawable.btn_yandex);
                        ind2.setImageResource(R.drawable.btn_yandex);
                        ind3.setImageResource(R.drawable.btn_status);

                        break;
                }

            }
        });

        Log.e("",adapter.getItemPosition(viewPager)+"");
        Log.e("",adapter.getCount()+"");
        Log.e("",viewPager.getCurrentItem()+"");


        if (isOpenAlread())
        {
//            Intent intent=new Intent(OnBoard.this, SendCodeActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
        }
        else
        {
            SharedPreferences.Editor editor=getSharedPreferences("board_pref",MODE_PRIVATE).edit();
            editor.putBoolean("board",true);
            editor.commit();
        }

    }

    private boolean isOpenAlread() {
        SharedPreferences sharedPreferences=getSharedPreferences("board_pref",MODE_PRIVATE);
        return sharedPreferences.getBoolean("board",false);

    }
}