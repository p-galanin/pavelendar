package com.example.pavelendar.db;

import android.content.Context;

import com.example.pavelendar.dao.CalendarDao;
import com.example.pavelendar.entity.Category;
import com.example.pavelendar.entity.Entry;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Category.class, Entry.class}, exportSchema = false, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "pavelendar.db";

    private static AppDatabase instance;

    public abstract CalendarDao calendarDao();

    public static AppDatabase getInstance(final Context context) {

        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return instance;
    }
}
