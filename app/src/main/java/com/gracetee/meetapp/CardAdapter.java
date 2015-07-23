package com.gracetee.meetapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gracetee.meetapp.Model.Event;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grace on 23/7/2015.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<Event> mItems;

    public CardAdapter() {
        super();
        mItems = new ArrayList<Event>();
        Event event = new Event();
        event.setName("The Great Barrier Reef");
        event.setFromDate("2005-11-12");
        event.setToDate("2005-11-12");
        event.setStartTime("9:00");
        event.setEndTime("16:00");
        event.setDesc("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua.");
        event.setOrganizer(ParseUser.getCurrentUser());
        event.setVenue("Aurora Borealis");
        mItems.add(event);

        event = new Event();
        event.setName("Grand Canyon");
        event.setFromDate("2005-11-12");
        event.setToDate("2005-11-12");
        event.setStartTime("9:00");
        event.setEndTime("16:00");
        event.setDesc("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua.");
        event.setOrganizer(ParseUser.getCurrentUser());
        event.setVenue("Aurora Borealis");
        mItems.add(event);

        event = new Event();
        event.setName("Baltoro Glacier");
        event.setFromDate("2005-11-12");
        event.setToDate("2005-11-12");
        event.setStartTime("9:00");
        event.setEndTime("16:00");
        event.setDesc("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua.");
        event.setOrganizer(ParseUser.getCurrentUser());
        event.setVenue("Aurora Borealis");
        mItems.add(event);

        event = new Event();
        event.setName("Iguazu Falls");
        event.setFromDate("2005-11-12");
        event.setToDate("2005-11-12");
        event.setStartTime("9:00");
        event.setEndTime("16:00");
        event.setDesc("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua.");
        event.setOrganizer(ParseUser.getCurrentUser());
        event.setVenue("Aurora Borealis");
        mItems.add(event);


        event = new Event();
        event.setName("Aurora Borealis");
        event.setFromDate("2005-11-12");
        event.setToDate("2005-11-12");
        event.setStartTime("9:00");
        event.setEndTime("16:00");
        event.setDesc("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua.");
        event.setOrganizer(ParseUser.getCurrentUser());
        event.setVenue("Aurora Borealis");
        mItems.add(event);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.discover_event_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Event event = mItems.get(i);
        viewHolder.cdEvent.setText(event.getName());
        viewHolder.cdEventFromDate.setText(event.getFromDate());
        viewHolder.cdEventToDate.setText(event.getToDate());
        viewHolder.cdEventStartTime.setText(event.getStartTime());
        viewHolder.cdEventEndTime.setText(event.getEndTime());
        viewHolder.cdEventDesc.setText(event.getDesc());
        viewHolder.cdEventOrganizer.setText(event.getOrganizer());
        viewHolder.cdVenue.setText(event.getVenue());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView cdEvent;
        public TextView cdEventFromDate;
        public TextView cdEventToDate;
        public TextView cdEventStartTime;
        public TextView cdEventEndTime;
        public TextView cdEventDesc;
        public TextView cdEventOrganizer;
        public TextView cdVenue;

        public ViewHolder(View itemView) {
            super(itemView);
            cdEvent = (TextView)itemView.findViewById(R.id.cd_event);
            cdEventFromDate = (TextView)itemView.findViewById(R.id.cd_from_date);
            cdEventToDate = (TextView)itemView.findViewById(R.id.cd_to_date);
            cdEventStartTime = (TextView)itemView.findViewById(R.id.cd_start_time);
            cdEventEndTime = (TextView)itemView.findViewById(R.id.cd_end_time);
            cdEventDesc = (TextView)itemView.findViewById(R.id.cd_desc);
            cdEventOrganizer = (TextView)itemView.findViewById(R.id.cd_organizer);
            cdVenue = (TextView)itemView.findViewById(R.id.cd_venue);
        }
    }
}
