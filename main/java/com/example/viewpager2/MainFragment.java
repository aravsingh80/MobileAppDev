package com.example.viewpager2;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.zip.Inflater;

public class MainFragment extends Fragment {
    ViewPager2 VP2;
    int position;
    int[] colors = new int[6];
    public static Fragment newInstance(ViewPager2 VP2, int position)
    {
        MainFragment fragment = new MainFragment();
        fragment.VP2 = VP2;
        fragment.position = position;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = getActivity().findViewById(R.id.tablayout);
        new TabLayoutMediator(tabLayout, VP2, (tab, position) -> tab.setText("I am  " + (position + 1))).attach();
        Button button = view.findViewById(R.id.pressme);
        button.setText("Press " + position);
        colors[0] = Color.parseColor("#22a144");
        colors[1] = Color.parseColor("#FF00FF");
        colors[2] = Color.parseColor("#234ba8");
        colors[3] = Color.parseColor("#e8f00e");
        colors[4] = Color.parseColor("#3ea2a3");
        colors[5] = Color.parseColor("#de1010");
        view.setBackgroundColor((colors[position]));
    }
}
