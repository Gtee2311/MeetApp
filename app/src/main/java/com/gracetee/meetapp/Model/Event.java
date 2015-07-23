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

    public String getDate() {
        return getString("Date");
    }

    public void setDate(String eventDate) {
        put("Date", eventDate);
    }

    public String getTime() {
        return getString("Time");
    }

    public void setTime(String eventTime) {
        put("Time", eventTime);
    }
	
	public ParseUser getOrganizer() {
		return getParseUser("Organizer");
	}
	
	public void setOrganizer(ParseUser currentUser) {
		put("Organizer", currentUser);
	}

    public ParseFile getFloorMap() {
        return getParseFile("Floor Map");
    }

    public void setOrganizer(ParseFile venuePic) { put("Floor Map", venuePic);
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
