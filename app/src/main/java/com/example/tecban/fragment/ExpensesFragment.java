package com.example.tecban.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tecban.R;
import com.example.tecban.adapter.CategoryAdapter;
import com.example.tecban.model.Category;

import java.util.ArrayList;

public class ExpensesFragment extends Fragment {

    private ListView lstItems;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_expenses, container, false);

        categoryList = new ArrayList<>();
        /*Category category = new Category();
        category.setName("Teste 1");
        category.setValue("R$ 0");
        categoryList.add(category);*/

        lstItems = rootView.findViewById(R.id.lst_items);

        categoryAdapter = new CategoryAdapter(categoryList, getContext());
        lstItems.setAdapter(categoryAdapter);

        return rootView;
    }
}
