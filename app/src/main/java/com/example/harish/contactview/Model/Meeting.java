package com.example.harish.contactview.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Meeting class has the data structure for creating the Meeting List.
 * Meeting details are specified in the model.
 *
 * @author Harish Narayanasamy
 * @version 1.0
 * @since 2017-08-20
 */


public class Meeting implements Serializable {


    public String mid;
    //unique meeting id generated when scheduling.
    public String title;
    //Title of meeting.
    public String startdate;
    //Startdate of the meeting.
    public String starttime;
    //Start Time of the meeting.
    public String enddate;
    //End date of the meeting.
    public String endtime;
    //End time of the meeting.
    public ArrayList<String> al = new ArrayList<String>();
    //Arraylist for friends.
    public String location;

    //Location of meeting.
    public ArrayList<String> getAl() {
        return al;
    }

    public void setAl(ArrayList<String> al) {
        for (int i = 0; i < al.size(); i++) {
            String item = al.get(i).toString();
            this.al.add(item);
        }
        ;
    }

    public void rmAl(ArrayList<String> al) {
        for (int i = 0; i < al.size(); i++) {
            String item = al.get(i).toString();
            this.al.remove(item);
        }

    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }


    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }


    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }


    public Meeting(String mid, String title, String startdate, String starttime, String enddate, String endtime, ArrayList al, String
            location) {

        this.mid = mid;
        this.title = title;
        this.starttime = starttime;
        this.endtime = endtime;
        this.startdate = startdate;
        this.enddate = enddate;
        this.location = location;

        for (int i = 0; i < al.size(); i++) {
            String item = al.get(i).toString();
            this.al.add(item);
        }

    }

}
