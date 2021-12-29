package com.example.viewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ViewPager2 VP2;
    RecyclerView.Adapter fragStateAdapter;
    int NUM_ITEMS = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VP2 = findViewById(R.id.container);
        fragStateAdapter = new MyFragmentStateAdapter(this);
        VP2.setAdapter(fragStateAdapter);
    }

    private class MyFragmentStateAdapter extends FragmentStateAdapter {

        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return MainFragment.newInstance(VP2, position);
        }

        @Override
        public int getItemCount() {
            return NUM_ITEMS;
        }
    }
}