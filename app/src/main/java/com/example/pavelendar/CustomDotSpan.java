package com.example.pavelendar;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import com.prolificinteractive.materialcalendarview.spans.DotSpan;

public class CustomDotSpan implements LineBackgroundSpan {

    private final float radius = 6;
    private final int color;
    private final int position;


    CustomDotSpan(int color, int position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public void drawBackground(Canvas canvas, Paint paint, int left, int right, int top, int baseline, int bottom, CharSequence charSequence, int start, int end, int lineNum) {

        // Based on com.prolificinteractive.materialcalendarview.spans.DotSpan#drqwBackground
        int oldColor = paint.getColor();
        if (this.color != 0) {
            paint.setColor(this.color);
        }

        canvas.drawCircle(left + this.radius + this.position * this.radius * 4, bottom + this.radius, this.radius, paint);
        paint.setColor(oldColor);

    }
}
