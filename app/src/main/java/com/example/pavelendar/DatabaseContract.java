package com.example.pavelendar;

public class DatabaseContract {

    public static class Category {

        public static final String TABLE_NAME = "categories";

        public static final String _ID = "id";
        public static final String COLUMN_TITLE = "title";

    }

    public static class Entry {

        public static final String TABLE_NAME = "entries";

        public static final String _ID = "id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_CATEGORY = "category";

    }
}
