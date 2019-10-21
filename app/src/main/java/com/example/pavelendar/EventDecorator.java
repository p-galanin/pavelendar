package com.example.pavelendar;

import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

public class EventDecorator implements DayViewDecorator {

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.getDay() % 2 == 0;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(10, Color.BLUE));
        view.addSpan(new DotSpan(5, Color.RED));
    }
}
