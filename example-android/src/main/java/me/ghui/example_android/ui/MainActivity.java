package me.ghui.example_android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ghui.example_android.R;
import me.ghui.example_android.ui.DailyHotFragment;
import me.ghui.example_android.ui.NewsFragment;
import me.ghui.example_android.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private List<Fragment> fragments = new ArrayList<>(2);
    private String[] titles = {"最新(html)", "最热(json)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragments.add(NewsFragment.newInstance());
        fragments.add(DailyHotFragment.newInstance());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.full_version).setOnMenuItemClickListener(item -> {
            Utils.openStorePage("me.ghui.v2er");
            return false;
        });

        menu.findItem(R.id.source_code).setOnMenuItemClickListener(item -> {
            Utils.openInBrowser("https://github.com/ghuiii/Fruit/tree/master/example-android", MainActivity.this);
            return false;
        });
        return true;
    }
}
