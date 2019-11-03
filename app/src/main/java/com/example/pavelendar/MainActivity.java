/*
 * - возможность изменять список чек-боксов
 * - отображение в календаре иконок (символов?) принятых
 * - задание цвета для вида принимаемого
 *
 * панели:
 * 1. календарь
 * 2. список принимаемого
 *
 * Активити Календарь:
 * -> по нажатию создаётся другая активити:
 * 	- кнопка добавления
 * 	- отображается список принятого (цвета)
 * 	- возможность удаления
 * 	- (возможность изменения порядка?)
 *
 * 	Кнопка добавления - активити:
 * 		- чек-бокс
 *
 *
 *
 *  Структура хранения
 * 1. Типы записей:
 * - id
 * - title
 * - comment
 *
 * 2. Записи:
 * - id
 * - дата
 * - ссылка на тип
 * - idx
 * - комментарий
 */
package com.example.pavelendar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pavelendar.dao.CalendarDao;
import com.example.pavelendar.entity.Category;
import com.example.pavelendar.entity.Entry;
import com.example.pavelendar.viewmodel.SomeNameViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private MaterialCalendarView calendarViewCustom;
    private Fragment oneDayFragment;
    private FloatingActionButton fabAddEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

//        final CalendarView calendarView = findViewById(R.id.calendarView);
//
//        calendarView.setDayBinder(new DayBinder<ViewContainer>() {
//
//            @Override
//            public ViewContainer create(View view) {
//                return new DayViewContainer(view);
//            }
//
//            @Override
//            public void bind(ViewContainer viewContainer, CalendarDay calendarDay) {
//                ((DayViewContainer) viewContainer).getTextView().setText(calendarDay.getDate().getDayOfMonth());
//            }
//
//        });

        // final SomeNameViewModel viewModel = ViewModelProviders.of(this, new SomeNameViewModel.Factory()).get(SomeNameViewModel.class);
        final SomeNameViewModel viewModel = ViewModelProviders.of(this).get(SomeNameViewModel.class);
        viewModel.getEntries().observe(this, new Observer<List<Entry>>() {

            @Override
            public void onChanged(final List<Entry> entries) {
                itWorksForEntries(entries);
            }

        });


        createTestData();

        this.oneDayFragment = new OneDayFragment();

        final FragmentTransaction fragmentTx = getSupportFragmentManager().beginTransaction();
        fragmentTx.add(R.id.oneDayFragment, this.oneDayFragment);
        fragmentTx.commit();

        this.fabAddEntry = findViewById(R.id.fab_add_entry);
        this.fabAddEntry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "GOOD", Toast.LENGTH_SHORT).show();
            }

        });

        this.calendarViewCustom = findViewById(R.id.calendarView);
        if (this.calendarViewCustom != null) {

            this.calendarViewCustom.addDecorator(new EventDecorator(Color.RED, 0));
            this.calendarViewCustom.addDecorator(new EventDecorator(Color.BLACK, 1));
            this.calendarViewCustom.addDecorator(new EventDecorator(Color.CYAN, 2));

            this.calendarViewCustom.setOnDateChangedListener(new OnDateSelectedListener() {

                @Override
                public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                    final int year = date.getYear();
                    final int month = date.getMonth();
                    final int dayOfMonth = date.getDay();

                    Toast.makeText(MainActivity.this, "khm", Toast.LENGTH_SHORT).show();

                }

            });
        }


        //this.calendarView = findViewById(R.id.calendarView);
//        if (this.calendarView != null) {
//            this.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//                @Override
//                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                    // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
//                    String msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year;
//                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
////
////                    Intent intent = new Intent(getApplicationContext(), OneDayActivity.class);
////                    startActivity(intent);
//                    final View fragmentView = MainActivity.this.oneDayFragment.getView();
//                    if (fragmentView != null) {
//                        final TextView tvFragment = findViewById(R.id.tv_in_fragment);
//                        tvFragment.setText(year + " " + month + " " + dayOfMonth);
//                    }
//
//                }
//            });
//        }

        // Начать активити с добавлением
//
//        final Button button = (Button) findViewById(R.id.button_add);
//        button.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), AddWordActivity.class);
//                startActivity(intent);
//            }
//
//        });



    }

    private void createTestData() {
        final CalendarDao dao = CalendarDao.Holder.get(getApplicationContext());
        final Category category2 = new Category("Pasha2" + LocalTime.now().toNanoOfDay());
        final long someCategoryId = dao.insertCategory(new Category("Pasha1" + LocalTime.now().toNanoOfDay()));
        final long category2Id = dao.insertCategory(category2);
        dao.insertEntry(new Entry(category2Id));

    }

    private void itWorksForEntries(final List<Entry> entries) {

        final View fragmentView = MainActivity.this.oneDayFragment.getView();

        if (fragmentView != null) {
            final TextView tvFragment = findViewById(R.id.tv_in_fragment);

            final StringBuilder sb = new StringBuilder();

            sb.append(LocalDateTime.now() + "\n\n");

            for (Entry entry : entries) {
                sb.append(entry.getCategoryId());
                sb.append("|");
                sb.append(entry.getDate());
                sb.append("\n");
            }

            tvFragment.setText(sb.toString());
        }

        Toast.makeText(MainActivity.this, "YUPI", Toast.LENGTH_SHORT).show();

    }

}
