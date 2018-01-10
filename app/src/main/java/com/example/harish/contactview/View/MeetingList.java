package com.example.harish.contactview.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

import com.example.harish.contactview.Controller.MeetingAdapter;
import com.example.harish.contactview.Model.Friend;
import com.example.harish.contactview.Model.Meeting;
import com.example.harish.contactview.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * The MeetingList class displays the List of Meetings.
 * Meeting details can be edited and friends can be added/removed.
 *
 * @author Harish Narayanasamy
 * @version 1.0
 * @since 2017-08-20
 */
public class MeetingList extends AppCompatActivity {
    private ListView listingMeeting;
    ArrayList<Meeting> mymList;
    ArrayList<Friend> myfList;

    MeetingAdapter mitemsAdapter;
    final ArrayList<String> selectedItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetinglist);

        Bundle bo = getIntent().getExtras();
        mymList = (ArrayList<Meeting>) bo.getSerializable("arrayOfMeeting");
        myfList = (ArrayList<Friend>) bo.getSerializable("arrayOfname");

        mitemsAdapter = new MeetingAdapter(this, mymList);
        listingMeeting = (ListView) findViewById(R.id.ListingMeetings);
        listingMeeting.setAdapter(mitemsAdapter);

        listingMeeting.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            // setting onItemLongClickListener and passing the position to the function
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                longclickmethod(position);

                return true;
            }
        });


    }

    protected void longclickmethod(int position) {
        final int deletePosition = position;

        AlertDialog.Builder alert = new AlertDialog.Builder(
                MeetingList.this);

//menu options for editing
        alert.setTitle("Select your choice");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MeetingList.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Delete");
        arrayAdapter.add("Edit title");
        arrayAdapter.add("Remove friends");
        arrayAdapter.add("Add friends");
        arrayAdapter.add("Edit start date");
        arrayAdapter.add("Edit end date");
        arrayAdapter.add("Edit start time");
        arrayAdapter.add("Edit end time");
        alert.setNegativeButton("CANCEL", new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });

//Delete meeting

        alert.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                if (strName.equalsIgnoreCase("Delete")) {
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(MeetingList.this);
                    //builderInner.setMessage(strName);
                    builderInner.setTitle("Delete Contact  ?");
                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            mymList.remove(deletePosition);
                            mitemsAdapter.notifyDataSetChanged();
                            mitemsAdapter.notifyDataSetInvalidated();

                        }
                    });
                    builderInner.setNegativeButton("CANCEL", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();


                        }
                    });
                    builderInner.show();
                }
//edit title
                if (strName.equalsIgnoreCase("Edit title")) {
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(MeetingList.this);
                    builderInner.setTitle("Add title ?");
                    final EditText input = new EditText(MeetingList.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setLayoutParams(lp);
                    builderInner.setView(input);
                    // builderInner.setIcon(R.drawable.key);
                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String password = input.getText().toString();

                            mymList.get(deletePosition).setTitle(password);
                            mitemsAdapter.notifyDataSetChanged();
                            mitemsAdapter.notifyDataSetInvalidated();
                        }
                    });
                    builderInner.setNegativeButton("CANCEL", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();


                        }
                    });
                    builderInner.show();
                }
                //edit start date
                if (strName.equalsIgnoreCase("Edit start date")) {
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(MeetingList.this);
                    //builderInner.setMessage(strName);
                    builderInner.setTitle("Enter start date ");
                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            final Calendar calendar = Calendar.getInstance();
                            int yy = calendar.get(Calendar.YEAR);
                            int mm = calendar.get(Calendar.MONTH);
                            int dd = calendar.get(Calendar.DAY_OF_MONTH);
                            DatePickerDialog datePicker = new DatePickerDialog(MeetingList.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    String date = "Start date:" + String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)
                                            + "-" + String.valueOf(dayOfMonth);
                                    mymList.get(deletePosition).setStartdate(date);
                                    mitemsAdapter.notifyDataSetChanged();
                                    mitemsAdapter.notifyDataSetInvalidated();

                                }
                            }, yy, mm, dd);
                            datePicker.show();

                        }
                    });
                    builderInner.setNegativeButton("CANCEL", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();


                        }
                    });
                    builderInner.show();
                }
//edit end date
                if (strName.equalsIgnoreCase("Edit end date")) {
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(MeetingList.this);
                    //builderInner.setMessage(strName);
                    builderInner.setTitle("Enter end date ");
                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            final Calendar calendar = Calendar.getInstance();
                            int yy = calendar.get(Calendar.YEAR);
                            int mm = calendar.get(Calendar.MONTH);
                            int dd = calendar.get(Calendar.DAY_OF_MONTH);
                            DatePickerDialog datePicker = new DatePickerDialog(MeetingList.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    String date = "End date:" + String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)
                                            + "-" + String.valueOf(dayOfMonth);
                                    mymList.get(deletePosition).setEnddate(date);
                                    mitemsAdapter.notifyDataSetChanged();
                                    mitemsAdapter.notifyDataSetInvalidated();

                                }
                            }, yy, mm, dd);
                            datePicker.show();

                        }
                    });
                    builderInner.setNegativeButton("CANCEL", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();


                        }
                    });
                    builderInner.show();
                }
                //edit start time
                if (strName.equalsIgnoreCase("Edit start time")) {
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(MeetingList.this);
                    //builderInner.setMessage(strName);
                    builderInner.setTitle("Enter start time ");
                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Calendar mcurrentTime = Calendar.getInstance();
                            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                            int minute = mcurrentTime.get(Calendar.MINUTE);
                            TimePickerDialog mTimePicker;
                            mTimePicker = new TimePickerDialog(MeetingList.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                    //ed5.setText( selectedHour + ":" + selectedMinute);
                                    mymList.get(deletePosition).setStarttime(selectedHour + ":" + selectedMinute);
                                    mitemsAdapter.notifyDataSetChanged();
                                    mitemsAdapter.notifyDataSetInvalidated();
                                }
                            }, hour, minute, true);//Yes 24 hour time
                            mTimePicker.show();


                        }
                    });
                    builderInner.setNegativeButton("CANCEL", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();


                        }
                    });
                    builderInner.show();
                }
                //edit end time
                if (strName.equalsIgnoreCase("Edit end time")) {
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(MeetingList.this);
                    //builderInner.setMessage(strName);
                    builderInner.setTitle("Enter end time ");
                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Calendar mcurrentTime = Calendar.getInstance();
                            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                            int minute = mcurrentTime.get(Calendar.MINUTE);
                            TimePickerDialog mTimePicker;
                            mTimePicker = new TimePickerDialog(MeetingList.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                    //ed5.setText( selectedHour + ":" + selectedMinute);
                                    mymList.get(deletePosition).setEndtime(selectedHour + ":" + selectedMinute);
                                    mitemsAdapter.notifyDataSetChanged();
                                    mitemsAdapter.notifyDataSetInvalidated();
                                }
                            }, hour, minute, true);//Yes 24 hour time
                            mTimePicker.show();


                        }
                    });
                    builderInner.setNegativeButton("CANCEL", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();


                        }
                    });
                    builderInner.show();
                }
                //remove friends
                if (strName.equalsIgnoreCase("Remove friends")) {
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(MeetingList.this);
                    builderInner.setTitle("Remove friends");

                    // builderInner.setIcon(R.drawable.key);
                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    MeetingList.this);
                            ArrayList<String> listfn = new ArrayList<String>();
                            for (int i = 0; i < mymList.get(deletePosition).al.size(); i++) {
                                listfn.add(mymList.get(deletePosition).al.get(i));
                            }
                            final CharSequence[] cs = listfn.toArray(new CharSequence[listfn.size()]);
                            builder.setTitle("Remove your friend ");
                            builder.setMultiChoiceItems(cs, null, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                    if (isChecked) {

                                        selectedItems.add(cs[which].toString());
                                        mymList.get(deletePosition).rmAl(selectedItems);
                                        mitemsAdapter.notifyDataSetChanged();
                                        mitemsAdapter.notifyDataSetInvalidated();
                                    } else {

                                    }


                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    });
                    builderInner.setNegativeButton("CANCEL", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();


                        }
                    });
                    builderInner.show();
                }
              //  Add friends
                if (strName.equalsIgnoreCase("Add friends")) {
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(MeetingList.this);
                    builderInner.setTitle("Add friends");

                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    MeetingList.this);


                            ArrayList<String> listfn = new ArrayList<String>();

                            for (int i = 0; i < myfList.size(); i++) {
                                listfn.add(myfList.get(i).getName());
                            }
                            final CharSequence[] cs = listfn.toArray(new CharSequence[listfn.size()]);
                            builder.setTitle("Add your friend ");
                            builder.setMultiChoiceItems(cs, null, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                    if (isChecked) {

                                        selectedItems.add(cs[which].toString());
                                        mymList.get(deletePosition).setAl(selectedItems);
                                        mitemsAdapter.notifyDataSetChanged();
                                        mitemsAdapter.notifyDataSetInvalidated();
                                    } else {

                                    }


                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    });
                    builderInner.setNegativeButton("CANCEL", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();


                        }
                    });
                    builderInner.show();
                }
            }

        });

        alert.show();

    }
}
