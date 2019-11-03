package com.example.pavelendar.entity;

import java.time.LocalDate;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id"),
        indices = @Index({"category_id"})
)
public class Entry {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private LocalDate date;

    @ColumnInfo(name = "category_id")
    private long categoryId;

    public Entry() { }

    public Entry(final long categoryId) {
        this.date = LocalDate.now();
        this.categoryId = categoryId;
    }

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(final long categoryId) {
        this.categoryId = categoryId;
    }
}
