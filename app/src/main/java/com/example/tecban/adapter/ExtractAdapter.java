package com.example.tecban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tecban.R;
import com.example.tecban.model.Extract;

import java.util.ArrayList;

public class ExtractAdapter extends ArrayAdapter<Extract> implements View.OnClickListener {

    private ArrayList<Extract> dataSet;
    Context mContext;

    @Override
    public void onClick(View view) {

    }

    // View lookup cache
    private static class ViewHolder {
        TextView tvType;
        TextView tvExpiration;
        TextView tvBank;
        TextView tvValue;
    }

    public ExtractAdapter(ArrayList<Extract> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }
    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Extract extract = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.tvType = convertView.findViewById(R.id.tv_name);
            viewHolder.tvExpiration = convertView.findViewById(R.id.tv_expiration);
            viewHolder.tvBank = convertView.findViewById(R.id.tv_bank);
            viewHolder.tvValue = convertView.findViewById(R.id.tv_value);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        viewHolder.tvType.setText(extract.getType());
        viewHolder.tvExpiration.setText(extract.getExpiration());
        viewHolder.tvBank.setText(extract.getBank());
        viewHolder.tvValue.setText(String.valueOf(extract.getValue()));

        return convertView;
    }

}
