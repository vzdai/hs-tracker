package com.mystrel.hstracker.AddGame;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mystrel.hstracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vivian on 2016-07-16.
 */
public class AddDeckActivity extends AppCompatActivity {

    private List<AddDeckClassIcon> classIcons;
    private AddDeckClassIcon selectedItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_deck);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        classIcons = new ArrayList<>();

        LinearLayout row1 = (LinearLayout) findViewById(R.id.classRow1);
        LinearLayout row2 = (LinearLayout) findViewById(R.id.classRow2);

        populateClassChoices(row1);
        populateClassChoices(row2);

        setAddDeckButton();
    }

    private void populateClassChoices(LinearLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View view = layout.getChildAt(i);
            if(view instanceof AddDeckClassIcon) {
                AddDeckClassIcon item = (AddDeckClassIcon) view;
                classIcons.add(item);

                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(selectedItem != null) {
                            selectedItem.setBackgroundColor(Color.TRANSPARENT);
                        }
                        v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selector_selected));
                        selectedItem = (AddDeckClassIcon) v;
                    }
                });
            }
        }
    }

    private void setAddDeckButton() {
        Button button = (Button) findViewById(R.id.addDeckButton);
        if(button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText editText = (EditText) findViewById(R.id.deckName);
                    if(editText != null) {
                        String deckName = editText.getText().toString();
                        if(deckName.length() == 0) {
                            Toast.makeText(AddDeckActivity.this, R.string.invalid_deck_name, Toast.LENGTH_SHORT).show();
                        } else if(selectedItem == null) {
                            Toast.makeText(AddDeckActivity.this, R.string.invalid_select_class, Toast.LENGTH_SHORT).show();
                        } else {
                            addDeckToJson(deckName, selectedItem.getClassName());
                        }
                    }
                }
            });
        }
    }

    private void addDeckToJson(String deckName, String className) {

    }



}
