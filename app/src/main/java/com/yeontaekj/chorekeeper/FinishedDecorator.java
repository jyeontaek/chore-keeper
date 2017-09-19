package com.yeontaekj.chorekeeper;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by yeontaekj on 9/18/2017.
 */

public class FinishedDecorator implements DayViewDecorator {

    private Context mContext;
    private final HashSet<CalendarDay> dates;

    public FinishedDecorator(Context context, Collection<CalendarDay> dates) {
        mContext = context;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.circle_red));
    }
}
