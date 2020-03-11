package com.allyants.chipview;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jbonk on 1/13/2018.
 */

public class SimpleChipAdapter extends ChipAdapter{

    ArrayList<BaseChipItem>search_data = new ArrayList<>();
    ArrayList<BaseChipItem>chips = new ArrayList<>();

    public SimpleChipAdapter(ArrayList<BaseChipItem>search_data){
        this.search_data = search_data;
        this.data = search_data;
    }

    public ArrayList<BaseChipItem> getChips() {
        return chips;
    }

    public void setChips(ArrayList<BaseChipItem> chips) {
        this.chips = chips;
        refresh();
    }

    @Override
    public BaseChipItem getItem(int pos) {
        return search_data.get(pos);
    }

    @Override
    public boolean isSelected(int pos) {
        if(chips.contains(search_data.get(pos))) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public View createSearchView(Context context, boolean is_checked, final int pos) {
        View view = View.inflate(context,R.layout.search,null);
        CheckBox cbCheck = view.findViewById(R.id.cbCheck);
        cbCheck.setText(search_data.get(pos).toString());
        cbCheck.setChecked(is_checked);
        cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chips.add(search_data.get(pos));
                    refresh();
                }else{
                    chips.remove(search_data.get(pos));
                    refresh();
                }
            }
        });
        return view;
    }

    @Override
    public View createChip(Context context, final int pos) {
        View view = View.inflate(context,R.layout.chip,null);
        TextView tvChip = view.findViewById(R.id.tvChip);
        tvChip.setText(search_data.get(pos).toString());
        ImageView ivClose = view.findViewById(R.id.ivClose);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chips.remove(search_data.get(pos));
                refresh();
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return search_data.size();
    }

}
