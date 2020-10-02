package com.raulcidlimon.conversordedivisas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CurrencyAdapter extends ArrayAdapter<CurrencyItems> {


    public CurrencyAdapter(Context context, ArrayList<CurrencyItems> currencyList) {
        super(context, 0, currencyList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.currency_item, viewGroup, false);
        }

        ImageView ivFlag = convertView.findViewById(R.id.ivFlag);
        TextView tvCurrencyText = convertView.findViewById(R.id.tvCurrencyText);

        CurrencyItems currentItems = getItem(position);

        if (currentItems != null) {
            ivFlag.setImageResource(currentItems.getFlagImage());
            tvCurrencyText.setText(currentItems.getCurrencyName());
        }

        return convertView;
    }
}
