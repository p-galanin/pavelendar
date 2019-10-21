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

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

            this.calendarViewCustom.addDecorator(new EventDecorator());

            this.calendarViewCustom.setOnDateChangedListener(new OnDateSelectedListener() {

                @Override
                public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                    final int year = date.getYear();
                    final int month = date.getMonth();
                    final int dayOfMonth = date.getDay();

                    final View fragmentView = MainActivity.this.oneDayFragment.getView();

                    if (fragmentView != null) {
                        final TextView tvFragment = findViewById(R.id.tv_in_fragment);
                        tvFragment.setText(year + " " + month + " " + dayOfMonth);
                    }

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

}
