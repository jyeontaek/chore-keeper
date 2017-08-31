package com.yeontaekj.chorekeeper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ChoreAdapter choreAdapter;
    private List<Chore> tempList;
    private ChoreDbHelper dbHelper;
    private SQLiteDatabase db;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Reached onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new ChoreDbHelper(this);
        db = dbHelper.getReadableDatabase();

        tempList = new ArrayList<>();
        choreAdapter = new ChoreAdapter(this, tempList);

        loadData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(choreAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.new_chore:
                startAddNewChoreActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startAddNewChoreActivity() {
        Intent intent = new Intent(this, NewChoreActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == NewChoreActivity.RESULT_OK) {
                String name = data.getStringExtra("name");
                String description = data.getStringExtra("description");
                tempList.add(new Chore(name));
                choreAdapter.notifyItemInserted(tempList.size() - 1);

                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("description", description);
                db.insert(ChoreContract.TABLE_NAME, null, values);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_calendar) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        Log.i(TAG, "Reached onDestroy");
    }

    private void loadData() {
        Cursor cursor = db.query(ChoreContract.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex(
                    ChoreContract.ChoreEntry.COLUMN_CHORE_NAME));
            tempList.add(new Chore(name));
            cursor.moveToNext();
        }
        cursor.close();
        choreAdapter = new ChoreAdapter(this, tempList);
    }
}
