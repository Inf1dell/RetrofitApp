package apk.karmak.retrofitapp.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import apk.karmak.retrofitapp.R;
import apk.karmak.retrofitapp.main.Analyzes.AnalyzesFragment;
import apk.karmak.retrofitapp.main.Profile.ProfileFragment;
import apk.karmak.retrofitapp.main.Results.ResultsFragment;
import apk.karmak.retrofitapp.main.Support.SupportFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        openFragment(new AnalyzesFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.analyzes:
                                openFragment(new AnalyzesFragment());
                                return true;
                            case R.id.results:
                                openFragment(new ResultsFragment());
                                return true;
                            case R.id.support:
                                openFragment(new SupportFragment());
                                return true;
                            case R.id.profile:
                                openFragment(new ProfileFragment());
                                return true;
                        }
                        return false;
                    }
                });
    }
    void openFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}