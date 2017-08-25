package com.yeontaekj.chorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Chore[] tempList = {new Chore("Test1"), new Chore("Test2")
                , new Chore("Test2"), new Chore("Test2"), new Chore("Test2"), new Chore("Test2")
                , new Chore("Test2")
                , new Chore("Test2")
                , new Chore("Test2")};
        ChoreAdapter choreAdapter = new ChoreAdapter(tempList);

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
            case R.id.new_task:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
