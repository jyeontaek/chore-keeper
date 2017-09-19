package com.yeontaekj.chorekeeper;

import android.content.ContentValues;
import android.content.Context;
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

import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
        choreAdapter = new ChoreAdapter(this, tempList, new OnDeleteRequestListener() {
            @Override
            public void deleteData(int position) {
                db.delete(ChoreContract.TABLE_NAME,
                        "KEY_NAME = ?",
                        new String[] {choreAdapter.getChoreList().get(position).getUUID()}
                );

                choreAdapter.getChoreList().remove(position);
                choreAdapter.notifyDataSetChanged();
            }
        });
        choreAdapter.setStartCalendarRequestListener(new OnStartCalendarRequestListener() {
            @Override
            public void startCalendarActivity(int position) {
                Intent startCalendarActivityIntent =
                        new Intent(MainActivity.this, CalendarActivity.class);
                startCalendarActivityIntent.putExtra(
                        "chore", choreAdapter.getChoreList().get(position));
                MainActivity.this.startActivity(startCalendarActivityIntent);
            }
        });

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
                Chore chore = new Chore(name, description);
                tempList.add(chore);
                choreAdapter.notifyItemInserted(tempList.size() - 1);

                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("description", description);
                values.put("KEY_NAME", chore.getUUID());
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
        saveChoreDates();
        Log.i(TAG, "Reached onDestroy");
    }

    private void loadData() {
        Log.i(TAG, "loadData");
        Cursor cursor = db.query(ChoreContract.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        tempList = choreAdapter.getChoreList();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i(TAG, "loadData");
            String name = cursor.getString(cursor.getColumnIndex(
                    ChoreContract.ChoreEntry.COLUMN_CHORE_NAME));
            String description = cursor.getString(cursor.getColumnIndex(
                    ChoreContract.ChoreEntry.COLUMN_CHORE_DESCRIPTION
            ));
            String id = cursor.getString(cursor.getColumnIndex(
                    ChoreContract.ChoreEntry.COLUMN_CHORE_KEY_NAME
            ));
            Chore chore = new Chore(name, description, id);

            loadChoreDates(chore);

            tempList.add(chore);
            cursor.moveToNext();
        }
        cursor.close();
        choreAdapter.notifyDataSetChanged();
    }

    private void loadChoreDates(Chore chore) {
        try {
            String fileName = chore.getUUID();
            FileInputStream fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line = "";

            while (!line.equals("")) {
                line = br.readLine();
                chore.addDate(new DateTime(Long.parseLong(line)));
            }

            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void saveChoreDates() {
        List<Chore> choreList = choreAdapter.getChoreList();

        for (Chore chore : choreList) {
            String fileName = chore.getUUID();
            try {
                FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                for (DateTime date : chore.getDates()) {
                    bw.write(Long.toString(date.getMillis()));
                    bw.newLine();
                }
                bw.flush();
                bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public interface OnDeleteRequestListener {
        void deleteData(int position);
    }

    public interface OnStartCalendarRequestListener {
        void startCalendarActivity(int position);
    }
}
