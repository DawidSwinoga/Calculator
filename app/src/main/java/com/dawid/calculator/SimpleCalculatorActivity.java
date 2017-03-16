package com.dawid.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SimpleCalculatorActivity extends AppCompatActivity {

    @BindView(R.id.number_pad_grid)
    GridLayout numberPadGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
        ButterKnife.bind(this);
        initNumberPadGrid();
    }

    private void initNumberPadGrid() {
        int[] numbers = {7,8,9,4,5,6,1,2,3};

        for(final int number : numbers) {
            Button numberPadButton = new Button(this);
            numberPadButton.setText(String.valueOf(number));
            numberPadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(number);
                }
            });
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            numberPadButton.setLayoutParams(params);
            numberPadGrid.addView(numberPadButton);
        }
    }

    @OnClick(R.id.bksp)
    void onBkspClicked() {

    }

    @OnClick(R.id.clear_display)
    void onClearDisplayClicked() {

    }

    @OnClick(R.id.change_sign)
    void onChangeSignClicked() {

    }

    @OnClick(R.id.division)
    void onDivisionClicked() {

    }

    @OnClick(R.id.multiplication)
    void onMultiplicationClicked() {

    }

    @OnClick(R.id.subtraction)
    void onSubtractionClicked() {

    }

    @OnClick(R.id.zero)
    void onZeroClicked() {

    }

    @OnClick(R.id.separated_button)
    void onSeparatedButtonClicked() {

    }

    @OnClick(R.id.equals)
    void onEqualsClicked() {

    }

    @OnClick(R.id.addition)
    void onAdditionClicked() {

    }
}
