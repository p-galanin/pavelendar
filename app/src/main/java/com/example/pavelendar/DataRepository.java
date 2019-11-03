package com.example.pavelendar;

import com.example.pavelendar.db.AppDatabase;
import com.example.pavelendar.entity.Category;

import java.util.List;

import androidx.lifecycle.LiveData;

public class DataRepository {

    private static DataRepository instance;

    private final AppDatabase database;

    private DataRepository(final AppDatabase database) {
        this.database = database;
    }

    public static DataRepository getInstance(final AppDatabase database) {

        if (instance == null) {
            synchronized (DataRepository.class) {
                if (instance == null) {
                    instance = new DataRepository(database);
                }
            }
        }

        return instance;
    }

//    public LiveData<List<Category>> loadCategories() {
//        return this.database.calendarDao().getAllCategories();
//    }
}
