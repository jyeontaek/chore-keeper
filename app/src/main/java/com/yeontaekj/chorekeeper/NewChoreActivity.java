package com.yeontaekj.chorekeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class NewChoreActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addButton;
    private EditText nameText, descText;
    private RecyclerView iconRecyclerView;
    private IconAdapter iconAdapter;

    public static final int RESULT_OK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chore);

        addButton = (Button) findViewById(R.id.button_add_chore);
        addButton.setOnClickListener(this);

        nameText = (EditText) findViewById(R.id.et_chore_name);
        descText = (EditText) findViewById(R.id.et_chore_description);

        iconAdapter = new IconAdapter(getApplicationContext(), loadIcons());

        iconRecyclerView = (RecyclerView) findViewById(R.id.rv_icons);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);;
        iconRecyclerView.setLayoutManager(gridLayoutManager);
        iconRecyclerView.setAdapter(iconAdapter);
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

    private List<Integer> loadIcons() {
        List<Integer> drawableIDList = new ArrayList<>();
        int drawableID = getResources().getIdentifier("icons_cat_80", "drawable", getPackageName());
        //Temp:
        drawableIDList.add(drawableID);

        return drawableIDList;
    }
}
