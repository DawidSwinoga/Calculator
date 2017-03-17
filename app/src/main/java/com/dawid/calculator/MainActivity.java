package com.dawid.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(value = R.id.simple_activity_button)
    void onSimpleActivityClicked() {
        Intent intent = new Intent(this, SimpleCalculatorActivity.class);
        startActivity(intent);
    }

    @OnClick(value = R.id.advanced_activity_button)
    void onAdvancedActivityClicked() {
        Intent intent = new Intent(this, AdvancedCalculatorActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.about_activity_button)
    void onAboutActivityClicked() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.exit_button)
    void onExitClicked() {
        finish();
    }
}
