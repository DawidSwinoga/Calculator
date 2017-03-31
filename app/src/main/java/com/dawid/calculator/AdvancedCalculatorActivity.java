package com.dawid.calculator;

import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdvancedCalculatorActivity extends SimpleCalculatorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advanced_calculator;
    }

    @OnClick(R.id.sin)
    void onSinClicked() {
        Toast.makeText(this, "sin", Toast.LENGTH_LONG).show();
    }
}
