package com.example.dell.pill_app;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;

public class AddPill extends AppCompatActivity {

    private Button setTime;
    private Button setDateStart;
    private Button setDateEnd;
    private  Calendar myCalendar;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Add Pill");
        setContentView(R.layout.activity_add_pill);

        setTime=(Button) findViewById(R.id.setTime);
        setDateStart=(Button) findViewById(R.id.setDateStart);
        setDateEnd=(Button) findViewById(R.id.setDateEnd);

        Button button=(Button) findViewById(R.id.nextBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Notification.class));
            }
        });
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    Calendar mcurrentTime=Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(AddPill.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            setTime.setText( selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }else{
                    Toast.makeText(getBaseContext(),"Android 7 req",Toast.LENGTH_LONG).show();
                }

            }
        });

        setDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Date

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    myCalendar = Calendar.getInstance();
                    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                                   myCalendar.set(Calendar.YEAR, year);
                                   myCalendar.set(Calendar.MONTH, monthOfYear);
                                   myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                updateLabel(setDateStart);
                            }

                        }

                    };
                    new DatePickerDialog(AddPill.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }else{
                    Toast.makeText(getBaseContext(),"Android 7 req",Toast.LENGTH_LONG).show();
                }


            }



        });

        setDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    myCalendar = Calendar.getInstance();
                    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                                myCalendar.set(Calendar.YEAR, year);
                                myCalendar.set(Calendar.MONTH, monthOfYear);
                                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                updateLabel(setDateEnd);
                            }
                        }
                    };
                    new DatePickerDialog(AddPill.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }else{
                    Toast.makeText(getBaseContext(),"Android 7 req",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

       private void updateLabel(Button button) {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat(myFormat, Locale.US);

            button.setText(sdf.format(myCalendar.getTime()));

        }
    }

}



