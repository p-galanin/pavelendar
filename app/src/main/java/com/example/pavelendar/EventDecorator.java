package com.example.pavelendar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;
import java.util.Set;

public class EventDecorator implements DayViewDecorator {

    private final CustomDotSpan dotSpan;

    private final Set<CalendarDay> dates = new HashSet<>();

    EventDecorator(final int color, final int position) {

        this.dotSpan = new CustomDotSpan(color, position);

        this.dates.add(CalendarDay.from(2019, 10, 1 + position));
        this.dates.add(CalendarDay.from(2019, 10, 10 + position));
        this.dates.add(CalendarDay.from(2019, 10, 20));

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return this.dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(this.dotSpan);
    }


}
