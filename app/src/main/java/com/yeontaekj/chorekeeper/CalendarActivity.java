package com.yeontaekj.chorekeeper;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CalendarActivity extends AppCompatActivity implements OnDateSelectedListener {

    private MaterialCalendarView mCalendarView;
    private Chore chore;
    private Button mButton;
    private Collection<CalendarDay> dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendar_view);
        mButton = (Button) findViewById(R.id.button_setFinished);
        chore = (Chore) getIntent().getSerializableExtra("chore");

        loadData();

        DateTime current = new DateTime();
        mCalendarView.setDateSelected(CalendarDay.from(current.toDate()), true);

        mCalendarView.setOnDateChangedListener(this);
    }

    //Colors the correct calendar dates when the chore was accomplished.
    private void loadData() {
        dateList = new HashSet<>();

        for (DateTime date : chore.getDates()) {
            dateList.add(CalendarDay.from(date.toDate()));
        }
        mCalendarView.addDecorator(new FinishedDecorator(this, dateList));
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        if (dateList.contains(date)) {
            mButton.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_button_delete));
        } else {
            mButton.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_button_add));
        }
    }
}
