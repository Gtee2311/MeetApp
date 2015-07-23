package com.gracetee.meetapp.Model;

import java.util.UUID;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("Event")
public class Event extends ParseObject {
	
	public String getName() {
		return getString("Event Name");
	}
	
	public void setName(String eventName) {
		put("Event Name", eventName);
	}

    public String getDesc() {
        return getString("Event Description");
    }

    public void setDesc(String eventDesc) {
        put("Event Description", eventDesc);
    }

    public String getFromDate() { return getString("FromDate");}

    public void setFromDate(String fromEventDate) {
        put("FromDate", fromEventDate);
    }

    public String getToDate() { return getString("ToDate");}

    public void setToDate(String toEventDate) {
        put("ToDate", toEventDate);
    }

    public String getStartTime() {
        return getString("StartTime");
    }

    public void setStartTime(String eventStartTime) {
        put("StartTime", eventStartTime);
    }

    public String getEndTime() {
        return getString("EndTime");
    }

    public void setEndTime(String eventEndTime) {
        put("EndTime", eventEndTime);
    }

	public String getOrganizer() {
		return getString("Organizer");
	}
	
	public void setOrganizer(ParseUser currentUser) {
		put("Organizer", currentUser);
	}

    public String getVenue() {
        return getString("Venue");
    }

    public void setVenue(String eventVenue) {
        put("Venue", eventVenue);
    }

    public ParseFile getFloorMap() {
        return getParseFile("Floor Map");
    }

    public void setFloorMap(ParseFile venuePic) { put("Floor Map", venuePic);
    }

	public boolean isDraft() {
		return getBoolean("isDraft");
	}

	public void setDraft(boolean isDraft) {
		put("isDraft", isDraft);
	}
	
	public void setUuidString() {
	    UUID uuid = UUID.randomUUID();
	    put("uuid", uuid.toString());
	}
	
	public String getUuidString() {
		return getString("uuid");
	}
	
	public static ParseQuery<Event> getQuery() {
		return ParseQuery.getQuery(Event.class);
	}
}
