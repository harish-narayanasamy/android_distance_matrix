package com.example.harish.contactview.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.harish.contactview.Controller.DummyLocationService;
import com.example.harish.contactview.Controller.FriendAdapter;
import com.example.harish.contactview.Controller.MeetingAdapter;
import com.example.harish.contactview.Model.Friend;
import com.example.harish.contactview.Model.Meeting;
import com.example.harish.contactview.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
/**
 * The ScheduleMeeting class allows the user to enter meeting details.
 * it also has the button for viewing meeting details.
 *
 * @author  Harish Narayanasamy
 * @version 1.0
 * @since   2017-08-20
 */
public class ScheduleMeeting extends AppCompatActivity {

    private EditText meetingtitle;
    private EditText meetingstartdate;
    private EditText meetingstarttime;
    private EditText meetingenddate;
    private EditText meetingendtime;
    private EditText meetingaddfriends;
    private EditText meetingaddlocation;
    private EditText friendlocation;
    int daychecker;
    int monthchecker;
    int yearchecker;
    int hourchecker;
    int minutechecker;
    Boolean DateCheckerFlag = false;
    Boolean TimeCheckerFlag = false;
    Boolean SameDayFlag = false;
    public static String floc;
    private Button sem;
    ArrayList<Friend> myList;
    ArrayList<Meeting> arrayOfMeeting = new ArrayList<Meeting>();
    MeetingAdapter mitemsAdapter;
    FriendAdapter itemsAdapter;
    private Button ml;
    final ArrayList<String> selectedItems = new ArrayList<String>();
    private static final String LOG_TAG = DummyLocationService.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedulemeeting);
        sem = (Button) findViewById(R.id.sem);
        Bundle bo = getIntent().getExtras();
        myList = (ArrayList<Friend>) bo.getSerializable("arrayOfUsers");
        itemsAdapter = new FriendAdapter(this, myList);

        mitemsAdapter = new MeetingAdapter(this, arrayOfMeeting);

        ml = (Button) findViewById(R.id.ml);
        meetingtitle = (EditText) findViewById(R.id.editText);
        meetingstartdate = (EditText) findViewById(R.id.editText2);
        meetingstarttime = (EditText) findViewById(R.id.editText3);
        meetingenddate = (EditText) findViewById(R.id.editText4);
        meetingendtime = (EditText) findViewById(R.id.editText5);
        meetingaddfriends = (EditText) findViewById(R.id.editText6);
        meetingaddlocation = (EditText) findViewById(R.id.editText7);
        friendlocation = (EditText) findViewById(R.id.editText8);

        View.OnClickListener titlelistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ScheduleMeeting.this);

                final EditText input = new EditText(ScheduleMeeting.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alert.setView(input);

                alert.setTitle("Enter the title");

                alert.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String inp = input.getText().toString();
                                meetingtitle.setText(inp);
                            }
                        });
                // Setting Negative "NO" Button
                alert.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });
                alert.show();


            }
        };
        View.OnClickListener sdatelistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ScheduleMeeting.this);


                alert.setTitle("Enter the start date");
                alert.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                        final Calendar calendar = Calendar.getInstance();
                        int yy = calendar.get(Calendar.YEAR);
                        int mm = calendar.get(Calendar.MONTH);
                        int dd = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePicker = new DatePickerDialog(ScheduleMeeting.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String date = "Start date:" + String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)
                                        + "-" + String.valueOf(dayOfMonth);
                                meetingstartdate.setText(date);
                                daychecker = dayOfMonth;
                                monthchecker = monthOfYear;
                                yearchecker = year;

                            }
                        }, yy, mm, dd);
                        datePicker.show();


                    }
                });
                // Setting Negative "NO" Button
                alert.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });
                alert.show();


            }
        };
        View.OnClickListener stimelistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ScheduleMeeting.this);


                alert.setTitle("Enter the start time");
                alert.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(ScheduleMeeting.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                meetingstarttime.setText(selectedHour + ":" + selectedMinute);
                                hourchecker = selectedHour;
                                minutechecker = selectedMinute;
                            }
                        }, hour, minute, true);//Yes 24 hour time
                        mTimePicker.show();


                    }
                });
                // Setting Negative "NO" Button
                alert.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });
                alert.show();


            }
        };
        View.OnClickListener edatelistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ScheduleMeeting.this);


                alert.setTitle("Enter the End date");
                alert.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Calendar calendar = Calendar.getInstance();
                        int yy = calendar.get(Calendar.YEAR);
                        int mm = calendar.get(Calendar.MONTH);
                        int dd = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePicker = new DatePickerDialog(ScheduleMeeting.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String date = "End date:" + String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)
                                        + "-" + String.valueOf(dayOfMonth);
                                DateCheckerFlag = false;
                                SameDayFlag = false;
                                if (yearchecker <= year) {
                                    if (monthchecker <= monthOfYear) {
                                        if (daychecker <= dayOfMonth) {
                                            DateCheckerFlag = true;
                                        }
                                    }

                                }
                                if (daychecker == dayOfMonth) {
                                    SameDayFlag = true;
                                }
                                if (DateCheckerFlag) {
                                    meetingenddate.setText(date);
                                } else {
                                    AlertDialog alertDialog = new AlertDialog.Builder(ScheduleMeeting.this).create();
                                    alertDialog.setTitle("Alert");
                                    alertDialog.setMessage("Please select appropriate date");
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
                                }


                            }
                        }, yy, mm, dd);
                        datePicker.show();


                    }
                });
                // Setting Negative "NO" Button
                alert.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });
                alert.show();


            }
        };
        View.OnClickListener etimelistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ScheduleMeeting.this);


                alert.setTitle("Enter the End time");
                alert.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(ScheduleMeeting.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                TimeCheckerFlag = false;

                                if (SameDayFlag) {
                                    if (hourchecker <= selectedHour) {
                                        if (minutechecker <= selectedMinute) {
                                            TimeCheckerFlag = true;
                                        }


                                    }
                                } else {
                                    TimeCheckerFlag = true;
                                }
                                if (TimeCheckerFlag) {
                                    meetingendtime.setText(selectedHour + ":" + selectedMinute);
                                } else {
                                    AlertDialog alertDialog = new AlertDialog.Builder(ScheduleMeeting.this).create();
                                    alertDialog.setTitle("Alert");
                                    alertDialog.setMessage("Please select appropriate time");
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
                                }
                            }
                        }, hour, minute, true);//Yes 24 hour time
                        mTimePicker.show();


                    }
                });
                // Setting Negative "NO" Button
                alert.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });
                alert.show();


            }
        };
        View.OnClickListener smlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uniqueID = UUID.randomUUID().toString();
                String content1 = meetingstartdate.getText().toString();
                String content2 = meetingstarttime.getText().toString();
                String content3 = meetingenddate.getText().toString();
                String content4 = meetingendtime.getText().toString();
                String content5 = meetingtitle.getText().toString();
                String content6 = meetingaddlocation.getText().toString();

                Meeting m = new Meeting(uniqueID, content5, content1, content2, content3, content4, selectedItems, content6);
                selectedItems.clear();
                mitemsAdapter.add(m);


                Toast.makeText(getApplicationContext(), "Meeting" + m.title + m.mid + "scheduled on" + m.starttime, Toast.LENGTH_LONG).show();
            }
        };

        View.OnClickListener mllistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleMeeting.this, MeetingList.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("arrayOfMeeting", arrayOfMeeting);
                bundle.putSerializable("arrayOfname", myList);

                intent.putExtras(bundle);
                startActivity(intent);


            }
        };
        View.OnClickListener addfllistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        ScheduleMeeting.this);
                ArrayList<String> listfn = new ArrayList<String>();
                for (int i = 0; i < myList.size(); i++) {
                    listfn.add(myList.get(i).getName());
                }
                final CharSequence[] cs = listfn.toArray(new CharSequence[listfn.size()]);

                builder.setTitle("Add your friends ");
                builder.setMultiChoiceItems(cs, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        if (isChecked) {

                            selectedItems.add(cs[which].toString());
                            String check = cs[which].toString();
                            Toast.makeText(getApplicationContext(),
                                    check, Toast.LENGTH_SHORT)
                                    .show();
                            meetingaddfriends.setText(cs[which].toString());
                        } else {
                            AlertDialog alertDialog = new AlertDialog.Builder(ScheduleMeeting.this).create();
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("Please select appropriate date");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }


                    }
                });
                // Setting Negative "NO" Button


                AlertDialog alert = builder.create();
                alert.show();

            }
        };
        View.OnClickListener addloclistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ScheduleMeeting.this);

                final EditText input = new EditText(ScheduleMeeting.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alert.setView(input);

                alert.setTitle("Enter the Location");

                alert.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String inp = input.getText().toString();
                                meetingaddlocation.setText(inp);
                            }
                        });
                // Setting Negative "NO" Button
                alert.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });
                alert.show();


            }
        };
        View.OnClickListener floclistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ScheduleMeeting.this);



                alert.setTitle("Get the Location");

                alert.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DummyLocationService dummyLocationService=DummyLocationService.getSingletonInstance(getApplicationContext());

                                Log.i(LOG_TAG, "File Contents:");
                                dummyLocationService.logAll();
                                List<DummyLocationService.FriendLocation> matched = null;
                                double lat=0,lon =0 ;
                                String testtime = String.valueOf(hourchecker)+ ":"+String.valueOf(minutechecker)+":"+"00"+" "+"AM";
                                try
                                {
                                    // 2 mins either side of 9:46:30 AM
                                    matched = dummyLocationService.getFriendLocationsForTime(DateFormat.getTimeInstance(
                                            DateFormat.MEDIUM).parse(testtime), 2, 0);
                                    Toast.makeText(getApplicationContext(), LOG_TAG, Toast.LENGTH_LONG).show();
                                    for(int i=0;i<matched.size();i++){
                                    lat=matched.get(i).latitude;
                                    lon=matched.get(i).longitude;
                                    }

                                    friendlocation.setText(String.valueOf(lat)+ " "+String.valueOf(lon));
                                    floc = String.valueOf(lat)+ " "+String.valueOf(lon);
                                } catch (ParseException e)
                                {
                                    e.printStackTrace();

                                }
                                Log.i(LOG_TAG, "Matched Query:");
                                dummyLocationService.log(matched);
                            }
                        });
                // Setting Negative "NO" Button
                alert.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });
                alert.show();




            }
        };
        meetingtitle.setOnClickListener(titlelistener);
        meetingstartdate.setOnClickListener(sdatelistener);
        meetingstarttime.setOnClickListener(stimelistener);
        meetingenddate.setOnClickListener(edatelistener);
        meetingendtime.setOnClickListener(etimelistener);
        meetingaddfriends.setOnClickListener(addfllistener);
        meetingaddlocation.setOnClickListener(addloclistener);
        friendlocation.setOnClickListener(floclistener);

        sem.setOnClickListener(smlistener);
        ml.setOnClickListener(mllistener);

    }

}
