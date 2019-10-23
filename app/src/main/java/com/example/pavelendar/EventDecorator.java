package com.example.pavelendar;

import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class EventDecorator implements DayViewDecorator {

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.getDay() % 2 == 0;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new CustomDotSpan(Color.BLUE, 0));
        view.addSpan(new CustomDotSpan(Color.RED, 1));
        view.addSpan(new CustomDotSpan(Color.GREEN, 2));
        view.addSpan(new CustomDotSpan(Color.BLACK, 3));
        view.addSpan(new CustomDotSpan(Color.CYAN, 4));
    }
}
