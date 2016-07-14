package com.mystrel.hstracker.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-13.
 */
public class InputCardsRow extends TableLayout {

    private String rowLabel;

    public InputCardsRow(Context context) {
        super(context);
        inflateView(context);
        setUpButtons();
    }

    public InputCardsRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);
        setUpButtons();

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.InputCardsRow);
        CharSequence row_label = arr.getString(R.styleable.InputCardsRow_row_label);
        if (row_label != null) {
           setRowLabel(row_label.toString());
        }
        arr.recycle();
    }

    public void setRowLabel(String label) {
        this.rowLabel = label;

        TextView textView = (TextView) findViewById(R.id.label);
        textView.setText(label);
    }

    public String getRowLabel() {
        return rowLabel;
    }

    public int getRegularCount() {
        TextView regularCount = (TextView) findViewById(R.id.regularText);
        String count = regularCount.getText().toString();
        return Integer.parseInt(count);
    }

    public int getGoldenCount() {
        TextView goldenCount = (TextView) findViewById(R.id.goldenText);
        String count = goldenCount.getText().toString();
        return Integer.parseInt(count);
    }

    private void inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.add_pack_card_row, this);
    }

    private void setUpButtons() {
        TextView regularCount = (TextView) findViewById(R.id.regularText);
        TextView goldenCount = (TextView) findViewById(R.id.goldenText);

        Button regularIncrement = (Button) findViewById(R.id.regularIncrement);
        Button regularDecrement = (Button) findViewById(R.id.regularDecrement);
        Button goldenIncrement = (Button) findViewById(R.id.goldenIncrement);
        Button goldenDecrement = (Button) findViewById(R.id.goldenDecrement);

        setIncrementer(regularIncrement, regularCount);
        setIncrementer(goldenIncrement, goldenCount);
        setDecrementer(regularDecrement, regularCount);
        setDecrementer(goldenDecrement, goldenCount);
    }

    private void setIncrementer(Button button, TextView textView) {
        final TextView text = textView;

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = text.getText().toString();
                int currentValue = Integer.parseInt(currentText);
                currentValue++;
                text.setText(String.valueOf(currentValue));
            }
        });
    }

    private void setDecrementer(Button button, TextView textView) {
        final TextView text = textView;

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = text.getText().toString();
                int currentValue = Integer.parseInt(currentText);
                if(currentValue > 0) {
                    currentValue--;
                }
                text.setText(String.valueOf(currentValue));
            }
        });
    }


}
