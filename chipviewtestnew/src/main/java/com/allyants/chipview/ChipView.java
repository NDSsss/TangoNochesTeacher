package com.allyants.chipview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.flexbox.FlexboxLayout;

/**
 * Created by jbonk on 1/13/2018.
 */

public class ChipView extends RelativeLayout {

    private FlexboxLayout flChips;
    private EditText etSearch;
    private ListView lvList;
    private Button btnListVisibility;
    private ChipAdapter adapter;
    private SimpleSearchAdapter simpleSearchAdapter;

    private boolean isListVisible = false;

    public ChipView(Context context) {
        super(context);
        init(null);
    }

    public ChipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public void showList() {
        isListVisible = true;
        if (lvList != null) {
            lvList.setVisibility(VISIBLE);
        }
        if (etSearch != null) {
            etSearch.setVisibility(VISIBLE);
        }
    }

    public void hideList() {
        isListVisible = false;
        if (lvList != null) {
            lvList.setVisibility(GONE);
        }
        if (etSearch != null) {
            etSearch.setVisibility(GONE);
        }
    }

    private void init(AttributeSet attrs) {
        View view = inflate(getContext(), R.layout.chip_view, this);
        flChips = view.findViewById(R.id.flChips);
        etSearch = view.findViewById(R.id.etSearch);
        etSearch.setVisibility(isListVisible ? VISIBLE : GONE);
        lvList = view.findViewById(R.id.lvList);
        btnListVisibility = view.findViewById(R.id.btnListVisibility);
        btnListVisibility.setOnClickListener(btn -> toggleListVisibility());
        lvList.setVisibility(isListVisible ? VISIBLE : GONE);
    }

    private void toggleListVisibility(){
        if(isListVisible){
            hideList();
        } else {
            showList();
        }
    }

    public void setAdapter(ChipAdapter adapter) {
        this.adapter = adapter;
        adapter.setChipView(this);
        simpleSearchAdapter = new SimpleSearchAdapter(getContext(), adapter);
        lvList.setAdapter(simpleSearchAdapter);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                simpleSearchAdapter.getFilter().filter(editable.toString());
            }
        });
    }

    public void notifyDataSetChanged() {
        refreshFlexbox();
        simpleSearchAdapter.notifyDataSetChanged();
    }

    private void refreshFlexbox() {
        if(etSearch != null){
            etSearch.setText("");
        }
        for (int i = flChips.getChildCount() - 1; i >= 0; i--) {
            if (flChips.indexOfChild(etSearch) != i) {
                flChips.removeViewAt(i);
            }
        }
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.isSelected(i)) {
                View v = adapter.createChip(getContext(), i);
                flChips.addView(v, 0);
            }
        }
    }
}
