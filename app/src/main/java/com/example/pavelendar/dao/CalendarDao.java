package com.example.pavelendar.dao;

import android.content.Context;

import com.example.pavelendar.db.AppDatabase;
import com.example.pavelendar.entity.Category;
import com.example.pavelendar.entity.Entry;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CalendarDao {

    @Query("SELECT * FROM category")
    List<Category> getAllCategories();

    @Query("SELECT * FROM entry")
    LiveData<List<Entry>> getAllEntries();

    @Insert
    long insertCategory(Category category);

    @Delete
    void deleteCategory(Category category);

    @Insert
    long insertEntry(Entry entry);

    @Delete
    void deleteEntry(Entry entry);

    class Holder {

        public static CalendarDao get(final Context context) {
            return AppDatabase.getInstance(context).calendarDao();
        }

    }
}
