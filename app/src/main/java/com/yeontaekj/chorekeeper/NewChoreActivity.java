package com.yeontaekj.chorekeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewChoreActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addButton;
    private EditText nameText, descText;

    public static final int RESULT_OK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chore);

        addButton = (Button) findViewById(R.id.button_add_chore);
        addButton.setOnClickListener(this);

        nameText = (EditText) findViewById(R.id.et_chore_name);
        descText = (EditText) findViewById(R.id.et_chore_description);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_add_chore) {
            String name = nameText.getText().toString();
            String description = descText.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("description", description);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
