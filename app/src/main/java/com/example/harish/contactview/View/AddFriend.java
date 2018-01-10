package com.example.harish.contactview.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.harish.contactview.Controller.FriendAdapter;
import com.example.harish.contactview.Model.Friend;
import com.example.harish.contactview.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/**
 * The Addfriend class displays the Home screen and button.
 * Friends can be added from contacts.
 *
 * @author Harish Narayanasamy
 * @version 1.0
 * @since 2017-08-20
 */
public class AddFriend extends Activity {


    private static AddFriend instance;

    public static AddFriend getInstance() {
        if (instance == null) {
            instance = new AddFriend();
        }
        return instance;
    }

    private Button addfriends;
    private Button friendlist;
    private Button schedulemeeting;
    private ListView listingContacts;

    public ArrayList<String> name = new ArrayList<String>();


    ArrayList<Friend> arrayOfUsers = new ArrayList<Friend>();


    FriendAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriend);

        addfriends = (Button) findViewById(R.id.af);
        friendlist = (Button) findViewById(R.id.fl);

        schedulemeeting = (Button) findViewById(R.id.sm);
        itemsAdapter = new FriendAdapter(this, arrayOfUsers);
        listingContacts = (ListView) findViewById(R.id.ListingContacts);
        listingContacts.setAdapter(itemsAdapter);


        OnClickListener aflistener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                        null, null, null, null);
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                        if (hasPhoneNumber > 0) {
                            Cursor cursor2 = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                                    new String[]{id}, null);
                            while (cursor2.moveToNext()) {
                                String email = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));


                                String uniqueID = UUID.randomUUID().toString();
                                Friend newFriend = new Friend(name, email, uniqueID, null);
                                if (arrayOfUsers.isEmpty()) {
                                    itemsAdapter.add(newFriend);

                                } else {

                                    boolean isExists = false;
                                    for (int i = 0; i < arrayOfUsers.size(); i++) {
                                        if (arrayOfUsers.get(i).name.equalsIgnoreCase(name)) {
                                            isExists = true;
                                            break;
                                        }
                                    }

                                    if (!isExists) {
                                        itemsAdapter.add(newFriend);
                                    }


                                }


                            }
                            cursor2.close();
                        }
                    }
                }
                cursor.close();


            }
        };

        listingContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            // setting onItemLongClickListener and passing the position to the function
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                longclickmethod(position);

                return true;
            }
        });
//Activity for Friend List
        OnClickListener fllistener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFriend.this, FriendList.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("arrayOfUsers", arrayOfUsers);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        };

        //Activity for Schedule Meeting

        OnClickListener smlistener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFriend.this, ScheduleMeeting.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("arrayOfUsers", arrayOfUsers);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        };
        addfriends.setOnClickListener(aflistener);
        friendlist.setOnClickListener(fllistener);
        schedulemeeting.setOnClickListener(smlistener);
    }


    ;

    //long item press action
    protected void longclickmethod(int position) {
        final int deletePosition = position;

        AlertDialog.Builder alert = new AlertDialog.Builder(
                AddFriend.this);


        alert.setTitle("Select your choice");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddFriend.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Delete");
        arrayAdapter.add("Edit");


        alert.setNegativeButton("CANCEL", new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });

//Delete friend from list
        alert.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                if (strName.equalsIgnoreCase("Delete")) {
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(AddFriend.this);
                    //builderInner.setMessage(strName);
                    builderInner.setTitle("Delete Contact  ?");
                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            arrayOfUsers.remove(deletePosition);
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
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(AddFriend.this);
                    builderInner.setTitle("ADD BIRTHDAY FOR " + arrayOfUsers.get(deletePosition).getName());
                    builderInner.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final Calendar calendar = Calendar.getInstance();
                            int yy = calendar.get(Calendar.YEAR);
                            int mm = calendar.get(Calendar.MONTH);
                            int dd = calendar.get(Calendar.DAY_OF_MONTH);
                            DatePickerDialog datePicker = new DatePickerDialog(AddFriend.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    String date = "Birthday:" + String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)
                                            + "-" + String.valueOf(dayOfMonth);

                                    arrayOfUsers.get(deletePosition).setBday(date);
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


