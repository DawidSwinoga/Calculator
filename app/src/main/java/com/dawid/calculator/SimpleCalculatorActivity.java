package com.dawid.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dawid.calculator.utils.DoubleParser;
import com.dawid.calculator.utils.EquationResolver;
import com.dawid.calculator.utils.OperationType;
import com.dawid.calculator.utils.UnknownSignException;
import com.dawid.calculator.utils.WrongAmountOfOperatorsException;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SimpleCalculatorActivity extends AppCompatActivity {
    private String lastSign = "";
    private String equation = "";
    @BindView(R.id.display)
    TextView display;

    @BindView(R.id.number_pad_grid)
    GridLayout numberPadGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initNumberPadGrid();
    }

    public void initNumberPadGrid() {
        int[] numbers = {7, 8, 9, 4, 5, 6, 1, 2, 3};

        for (final int number : numbers) {
            Button numberPadButton = new Button(this);
            numberPadButton.setText(String.valueOf(number));
            numberPadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numberPadGridHandleEvent(number);
                }
            });
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            numberPadButton.setLayoutParams(params);
            numberPadGrid.addView(numberPadButton);
        }
    }

    protected int getLayoutId() {
        return R.layout.activity_simple_calculator;
    }

    private void numberPadGridHandleEvent(int number) {
        if (DoubleParser.parse(lastSign) != null || lastSign.equals(OperationType.SEPARATED_SIGN)
                || "".equals(lastSign)) {
            lastSign += String.valueOf(number);
            equation += number;
        } else {
            lastSign = String.valueOf(number);
            equation += lastSign;
        }
        display.setText(equation);
    }

    @OnClick(R.id.bksp)
    void onBkspClicked() {
        String[] tmp = equation.split(" ");

        if (tmp.length > 0) {
            String last = tmp[tmp.length - 1];
            if (isNumeric(last)) {
                lastSign = last.substring(0, last.length() - 1);
                equation = replaceLast(equation, last, lastSign);
                equation = removeLastSignIfExist('-', equation);
            } else {
                equation = replaceLast(equation, " " + last, "");
                if (tmp.length > 1) {
                    lastSign = tmp[tmp.length - 2];
                }
            }
        }

        display.setText(equation);
    }

    @OnClick(R.id.clear_display)
    void onClearDisplayClicked() {
        equation = "";
        lastSign = "";
        display.setText(equation);
    }

    @OnClick(R.id.change_sign)
    void onChangeSignClicked() {
        if (isNumeric(lastSign)) {
            String number = String.valueOf(-DoubleParser.parse(lastSign));
            equation = replaceLast(equation, lastSign, number);
            lastSign = number;
            display.setText(equation);
        }
    }

    @OnClick(R.id.division)
    void onDivisionClicked() {
        addOperator(OperationType.DIVISION);
    }

    @OnClick(R.id.multiplication)
    void onMultiplicationClicked() {
        addOperator(OperationType.MULTIPLICATION);
    }

    @OnClick(R.id.subtraction)
    void onSubtractionClicked() {
        addOperator(OperationType.SUBTRACTION);
    }

    @OnClick(R.id.zero)
    void onZeroClicked() {
        numberPadGridHandleEvent(0);
    }

    @OnClick(R.id.separated_button)
    void onSeparatedButtonClicked() {
        if (isNumeric(lastSign) && isNumeric((String
                .valueOf(lastSign + OperationType.SEPARATED_SIGN)))) {
            String newSign = lastSign + OperationType.SEPARATED_SIGN;
            equation = replaceLast(equation, lastSign, newSign);
            lastSign = newSign;
            display.setText(equation);
        }
    }

    @OnClick(R.id.equals)
    void onEqualsClicked() {
        if (!lastSign.equals("") && equation.length() >= 5) {
            if (!isNumeric(lastSign)) {
//                equation = replaceLast(equation, " " + lastSign + " ", "");
            }
            try {
                equation = "" + EquationResolver.resolve(Arrays.asList(equation.split(" ")));
            } catch (UnknownSignException | WrongAmountOfOperatorsException e) {
                e.printStackTrace();
                Toast toast = Toast.makeText(this, R.string.invalid_equation, Toast.LENGTH_LONG);
                toast.show();
            }
            lastSign = equation;
            display.setText(equation);
        }
    }

    @OnClick(R.id.addition)
    void onAdditionClicked() {
        addOperator(OperationType.ADDITION);
    }


    private String removeLastSignIfExist(char sign, String equation) {
        if (equation.length() > 0 && equation.charAt(equation.length() - 1) == sign) {
            return replaceLast(equation, String.valueOf(sign), "");
        }
        return equation;
    }

    private void addOperator(String operator) {
        if (isNumeric(lastSign) || OperationType.SEPARATED_SIGN.equals(lastSign)) {
            lastSign = operator;
            equation = equation + " " + lastSign + " ";
        } else if (!"".equals(equation)) {
            equation = replaceLast(equation, lastSign, operator);
            lastSign = operator;
        }
        display.setText(equation);
    }

    private boolean isNumeric(String sign) {
        return DoubleParser.parse(sign) != null;
    }

    public static String replaceLast(String string, String toReplace, String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos)
                    + replacement
                    + string.substring(pos + toReplace.length(), string.length());
        } else {
            return string;
        }
    }
}
