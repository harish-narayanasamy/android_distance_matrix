package com.example.harish.contactview.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.harish.contactview.Controller.FriendAdapter;
import com.example.harish.contactview.Model.Friend;
import com.example.harish.contactview.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * The FriendList class displays the FriendList.
 * Friends birthdate can be added from contacts and details can be edited.
 *
 * @author Harish Narayanasamy
 * @version 1.0
 * @since 2017-08-20
 */
public class FriendList extends AppCompatActivity {
    private ListView listingContacts;
    ArrayList<Friend> myList;

    FriendAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendlist);

        Bundle bo = getIntent().getExtras();
        myList = (ArrayList<Friend>) bo.getSerializable("arrayOfUsers");
        itemsAdapter = new FriendAdapter(this, myList);
        listingContacts = (ListView) findViewById(R.id.ListingContacts);
        listingContacts.setAdapter(itemsAdapter);

        listingContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            // setting onItemLongClickListener and passing the position to the function
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                longclickmethod(position);

                return true;
            }
        });


    }
//longitemclick for edit
    protected void longclickmethod(int position) {
        final int deletePosition = position;

        AlertDialog.Builder alert = new AlertDialog.Builder(
                FriendList.this);


        alert.setTitle("Select your choice");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FriendList.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Delete");
        arrayAdapter.add("Edit");


        alert.setNegativeButton("CANCEL", new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });

//Delete friend from List
        alert.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                if (strName.equalsIgnoreCase("Delete")) {
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(FriendList.this);
                    //builderInner.setMessage(strName);
                    builderInner.setTitle("Delete Contact  ?");
                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            myList.remove(deletePosition);
                            itemsAdapter.notifyDataSetChanged();
                            itemsAdapter.notifyDataSetInvalidated();

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
//Add birthday for friend
                if (strName.equalsIgnoreCase("Edit")) {
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(FriendList.this);
                    String qname = myList.get(deletePosition).getName();
                    // builderInner.setMessage(qname);
                    builderInner.setTitle("ADD BIRTHDAY FOR " + myList.get(deletePosition).getName());
                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final Calendar calendar = Calendar.getInstance();
                            int yy = calendar.get(Calendar.YEAR);
                            int mm = calendar.get(Calendar.MONTH);
                            int dd = calendar.get(Calendar.DAY_OF_MONTH);
                            DatePickerDialog datePicker = new DatePickerDialog(FriendList.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    String date = "Birthday:" + String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)
                                            + "-" + String.valueOf(dayOfMonth);

                                    myList.get(deletePosition).setBday(date);
                                    itemsAdapter.notifyDataSetChanged();
                                    itemsAdapter.notifyDataSetInvalidated();
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


            }
        });

        alert.show();

    }

}
