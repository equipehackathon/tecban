package com.example.tecban.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tecban.R;
import com.example.tecban.adapter.ExtractAdapter;
import com.example.tecban.model.Extract;

import java.util.ArrayList;
import java.util.Calendar;

public class MarchFragment extends Fragment {

    private ListView lstItems;
    private ExtractAdapter extractAdapter;
    private ArrayList<Extract> extractList;
    private TextView tvDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_march, container, false);

        extractList = new ArrayList<>();
        /*Extract extract = new Extract();
        extract.setType("Teste 1");
        extract.setExpiration("Teste 2");
        extract.setBank("Teste 3");
        extract.setValue("R$ 0");
        extractList.add(extract);*/

        lstItems = rootView.findViewById(R.id.lst_items);
        tvDay = rootView.findViewById(R.id.tv_day);

        extractAdapter = new ExtractAdapter(extractList, getContext());
        lstItems.setAdapter(extractAdapter);

        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        String dayOfMonthStr = String.valueOf(dayOfMonth);

        tvDay.setText(dayOfMonthStr);

        return rootView;
    }

}
