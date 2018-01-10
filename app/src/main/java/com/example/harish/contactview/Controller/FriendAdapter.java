package com.example.harish.contactview.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.harish.contactview.Model.Friend;
import com.example.harish.contactview.R;

import java.util.ArrayList;


/**
 * The FriendAdapter class has array adapter structure to view items in Friendlist..
 * Friends details are specified in the model.
 *
 * @author Harish Narayanasamy
 * @version 1.0
 * @since 2017-08-20
 */

public class FriendAdapter extends ArrayAdapter<Friend> {

    public FriendAdapter(Context context, ArrayList<Friend> friends) {
        super(context, 0, friends);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        Friend friend = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_friend, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
        TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
        TextView tvdb = (TextView) convertView.findViewById(R.id.tvDb);

        tvName.setText(friend.name);
        tvEmail.setText(friend.email);
        tvId.setText(friend.id);
        tvdb.setText(friend.bday);


        return convertView;
    }


}