package com.example.tecban.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.tecban.R;
import com.example.tecban.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ExtractFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_extract, container, false);

        ViewPager viewPager = rootView.findViewById(R.id.viewpager);
        TabLayout tabLayout = rootView.findViewById(R.id.tablayout);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());

        adapter.addFrag(new JanuaryFragment(), "Janeiro");
        adapter.addFrag(new FebruaryFragment(), "Fevereiro");
        adapter.addFrag(new MarchFragment(), "Março");
        adapter.addFrag(new MayFragment(), "Maio");
        adapter.addFrag(new JuneFragment(), "Junho");
        adapter.addFrag(new JulyFragment(), "Julho");
        adapter.addFrag(new AugustFragment(), "Agosto");
        adapter.addFrag(new SeptemberFragment(), "Setembro");
        adapter.addFrag(new OctoberFragment(), "Outubro");
        adapter.addFrag(new NovemberFragment(), "Novembro");
        adapter.addFrag(new DecemberFragment(), "Dezembro");

        // set adapter on viewpager
        viewPager.setAdapter(adapter);

        return rootView;
    }
}
