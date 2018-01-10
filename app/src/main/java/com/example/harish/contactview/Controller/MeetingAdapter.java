package com.example.harish.contactview.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.harish.contactview.Model.Meeting;
import com.example.harish.contactview.R;

import java.util.ArrayList;

import static com.example.harish.contactview.View.ScheduleMeeting.floc;

/**
 * The MeetingAdapter class has array adapter structure to view items in MeetingList..
 * Meeting details are specified in the model.
 *
 * @author Harish Narayanasamy
 * @version 1.0
 * @since 2017-08-20
 */
public class MeetingAdapter extends ArrayAdapter<Meeting> {

    public MeetingAdapter(Context context, ArrayList<Meeting> meets) {
        super(context, 0, meets);
    }


    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        Meeting meet = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_meeting, parent, false);
        }
        TextView meid = (TextView) convertView.findViewById(R.id.meid);
        TextView metitle = (TextView) convertView.findViewById(R.id.metitle);
        TextView mesdt = (TextView) convertView.findViewById(R.id.mesdt);
        TextView meedt = (TextView) convertView.findViewById(R.id.meedt);
        TextView mest = (TextView) convertView.findViewById(R.id.mest);
        TextView meent = (TextView) convertView.findViewById(R.id.meent);
        TextView mefrd = (TextView) convertView.findViewById(R.id.mefrd);
        TextView meloc = (TextView) convertView.findViewById(R.id.meloc);
        TextView mefrloc = (TextView) convertView.findViewById(R.id.frloc);

        String friend = meet.getAl().toString();
        meid.setText(meet.mid);
        metitle.setText(meet.title);
        mesdt.setText(meet.startdate);
        meedt.setText(meet.enddate);
        mest.setText(meet.starttime);
        meent.setText(meet.endtime);
        mefrd.setText(friend);
        meloc.setText(meet.location);
        mefrloc.setText(floc);

        return convertView;
    }

}
