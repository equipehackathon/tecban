package com.example.tecban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tecban.R;
import com.example.tecban.model.Category;
import com.example.tecban.model.Extract;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> implements View.OnClickListener {

    private ArrayList<Category> dataSet;
    Context mContext;

    @Override
    public void onClick(View view) {

    }

    // View lookup cache
    private static class ViewHolder {
        TextView tvName;
        TextView tvValue;
    }

    public CategoryAdapter(ArrayList<Category> data, Context context) {
        super(context, R.layout.row_item_category, data);
        this.dataSet = data;
        this.mContext=context;

    }
    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category category = getItem(position);
        CategoryAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new CategoryAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_category, parent, false);
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            viewHolder.tvValue = convertView.findViewById(R.id.tv_value);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CategoryAdapter.ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        viewHolder.tvName.setText(category.getName());
        viewHolder.tvValue.setText(category.getValue());

        return convertView;
    }

}
