package com.example.pavelendar.viewmodel;

import android.app.Application;

import com.example.pavelendar.dao.CalendarDao;
import com.example.pavelendar.db.AppDatabase;
import com.example.pavelendar.entity.Entry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SomeNameViewModel extends AndroidViewModel {

    private final LiveData<List<Entry>> entries;

    public SomeNameViewModel(@NonNull final Application application) {
        super(application);

        final CalendarDao calendarDao = CalendarDao.Holder.get(application.getBaseContext());
        this.entries = calendarDao.getAllEntries();
    }

    public LiveData<List<Entry>> getEntries() {
        return this.entries;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;

        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {

            @SuppressWarnings("unchecked")
            final T viewModel = (T) new SomeNameViewModel(this.application);

            return viewModel;
        }
    }

}