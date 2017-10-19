package me.ghui.example_android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class DailyHotFragment extends Fragment {

    public static DailyHotFragment newInstance() {

        Bundle args = new Bundle();

        DailyHotFragment fragment = new DailyHotFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
