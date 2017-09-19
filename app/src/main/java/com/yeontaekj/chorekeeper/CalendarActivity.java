package com.yeontaekj.chorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private MaterialCalendarView mCalendarView;
    private Chore chore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendar_view);
        chore = (Chore) getIntent().getSerializableExtra("chore");

        loadData();
    }

    //Colors the correct calendar dates when the chore was accomplished.
    private void loadData() {
        Collection<CalendarDay> dateList = new ArrayList<>();

        for (DateTime date : chore.getDates()) {
            dateList.add(CalendarDay.from(date.toDate()));
        }
        mCalendarView.addDecorator(new FinishedDecorator(this, dateList));
    }

}
