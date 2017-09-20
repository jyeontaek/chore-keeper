package com.yeontaekj.chorekeeper;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class CalendarActivity extends AppCompatActivity implements OnDateSelectedListener,
        View.OnClickListener {

    public static final String TAG = "CalendarActivity";
    public static final int RESULT_OK = 2;

    private MaterialCalendarView mCalendarView;
    private Chore chore;
    private Button mButton;
    private Collection<CalendarDay> dateList;
    private boolean contains_date;
    private CalendarDay currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendar_view);
        chore = (Chore) getIntent().getSerializableExtra("chore");

        loadData();

        mButton = (Button) findViewById(R.id.button_setFinished);
        mButton.setOnClickListener(this);

        mCalendarView.setOnDateChangedListener(this);

        DateTime current = new DateTime();
        currentDate = CalendarDay.from(current.toDate()) ;
        mCalendarView.setDateSelected(currentDate, true);
        onDateSelected(mCalendarView, currentDate, true);
    }

    //Colors the correct calendar dates when the chore was accomplished.
    private void loadData() {
        dateList = new HashSet<>();
        Log.i(TAG, Integer.toString(chore.getDates().size()));
        for (DateTime date : chore.getDates()) {
            Log.i(TAG, "Added date to dateList");
            dateList.add(CalendarDay.from(date.toDate()));
        }
        mCalendarView.addDecorator(new FinishedDecorator(this, dateList));
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        currentDate = date;
        if (dateList.contains(date)) {
            mButton.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_button_delete));
            contains_date = true;
        } else {
            mButton.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_button_add));
            contains_date = false;
        }
    }

    @Override
    public void onClick(View v) {
        DateTime date = new DateTime(currentDate.getDate());
        if (contains_date) {
            chore.getDates().remove(date);
            dateList.remove(currentDate);
            changeColor();
        } else {
            Log.i(TAG, "Added date to chore list");
            chore.addDate(date);
            dateList.add(currentDate);
            changeColor();
        }
    }

    private void changeColor() {
        if (contains_date) {
            mButton.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_button_add));
            contains_date = false;
        } else {
            mButton.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_button_delete));
            contains_date = true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("chore", chore);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() called");
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() called");
    }


}
