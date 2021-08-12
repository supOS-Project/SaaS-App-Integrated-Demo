package com.bluetron.eco.sdk.dto;

public class ActionType {
    public final static String EVENT_CREATE = "CREATE";
    public final static String EVENT_UPDATE = "UPDATE";
    public final static String EVENT_DELETE = "DELETE";
    String encode;
    String event;

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
